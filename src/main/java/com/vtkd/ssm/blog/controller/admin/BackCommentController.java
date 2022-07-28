package com.vtkd.ssm.blog.controller.admin;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Comment;
import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.enums.UserRole;
import com.vtkd.ssm.blog.mapper.CommentMapper;
import com.vtkd.ssm.blog.service.CommentService;
import com.vtkd.ssm.blog.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MailDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 评论 后台管理
 *
 * @author 君上
 * @date 2022-7-27
 */
@Controller
@RequestMapping("/admin/comment")
public class BackCommentController {


    @Autowired
    private CommentService commentService;

    /**
     * 评论 展示页面
     *
     * @param pageIndex 起始页
     * @param pageSize  页面多少数据
     * @param session
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              HttpSession session) {
        User user = (User) session.getAttribute("user");
        // 管理员查询所有, 用户查询自己的
        HashMap<String, Object> criteria = new HashMap<>();
        if (!user.getUserRole().equals(UserRole.ADMIN.getValue())) {
            criteria.put("userId", user.getUserId());
        }

        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Comment> pageInfo = commentService.pageComment(pageIndex, pageSize, criteria);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("pageUrlPrefix", "/admin/comment?pageIndex");

        modelAndView.setViewName("Admin/Comment/index");
        return modelAndView;
    }

    /**
     * 评论我的 页面展示
     *
     * @param pageIndex 起始页
     * @param pageSize  每页展示的数据
     * @param session
     * @return
     */
    @RequestMapping("/receive")
    public ModelAndView receive(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");

        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Comment> commentPageInfo = commentService.getReceiveCommentByPage(pageIndex, pageSize, user.getUserId());
        modelAndView.addObject("pageInfo", commentPageInfo);
        modelAndView.addObject("pageUrlPrefix", "/admin/comment?pageIndex");

        modelAndView.setViewName("Admin/Comment/index");
        return modelAndView;
    }

    /**
     * 展示 评论修改
     *
     * @param commentId id
     * @param session
     * @return
     */
    @RequestMapping("/edit/{commentId}")
    public ModelAndView editCommentView(@PathVariable("commentId") Integer commentId,
                                        HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");

        // 管理员才能修改
        if (!user.getUserRole().equals(UserRole.ADMIN.getValue())) {
            modelAndView.setViewName("redirect:/admin/comment");
            return modelAndView;
        }
        Comment comment = commentService.getCommentById(commentId);

        modelAndView.addObject("comment", comment);

        modelAndView.setViewName("Admin/Comment/edit");
        return modelAndView;
    }

    /**
     * 评论 修改提交
     *
     * @param comment 评论
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment) {

        commentService.updateComment(comment);

        return "redirect:/admin/comment";
    }

    /**
     * 删除评论及其子评论
     *
     * @param commentId id
     * @param session
     * @return
     */
    @RequestMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        // 只有管理员才能删除评论
        if (user.getUserRole().equals(UserRole.ADMIN.getValue())) {
            commentService.deleteCommentById(commentId);
        }
        return "redirect:/admin/comment";
    }

    /**
     * 回复评论 页面展示
     *
     * @param commentId
     * @return
     */
    @RequestMapping("/reply/{commentId}")
    public ModelAndView replyCommentView(@PathVariable("commentId") Integer commentId) {
        ModelAndView modelAndView = new ModelAndView();
        Comment comment = commentService.getCommentById(commentId);
        modelAndView.addObject("comment", comment);

        modelAndView.setViewName("Admin/Comment/reply");
        return modelAndView;
    }

    /**
     * 评论 回复提交
     *
     * @param comment 评论
     * @return
     */
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replyCommentSubmit(Comment comment, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String ipAddress = MyUtils.getIpAddress(request);

        // 评论角色 管理员 1 其它用户 0
        Integer commentRole = null;
        commentRole = user.getUserRole().equals(UserRole.ADMIN.getValue()) ? 1 : 0;

        comment.setCommentRole(commentRole);
        comment.setCommentIp(ipAddress);
        comment.setCommentUserId(user.getUserId());
        comment.setCommentAuthorName(user.getUserName());
        comment.setCommentAuthorAvatar(user.getUserAvatar());
        comment.setCommentAuthorEmail(user.getUserEmail());
        comment.setCommentAuthorUrl(user.getUserUrl());
        comment.setCommentCreateTime(new Date());
        comment.setCommentAgent(MyUtils.getUserAgent(request));
        commentService.insertComment(comment);
        return "redirect:/admin/comment";
    }


}
