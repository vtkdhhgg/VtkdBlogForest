package com.vtkd.ssm.blog.controller.home;

import com.alibaba.fastjson.JSONReader;
import com.vtkd.ssm.blog.dto.JsonResult;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Comment;
import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.enums.UserRole;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.CommentService;
import com.vtkd.ssm.blog.util.MyUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 文章评论管理
 */
@Api("文章评论管理")
@Controller
public class CommentController {

    @Autowired
    public CommentService commentService;

    @Autowired
    public ArticleService articleService;


    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult commentSubmit(Comment comment, HttpServletRequest request) {
        if (comment == null) {
            return new JsonResult().fail("评论数据错误!");
        }

        // 用户相关
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return new JsonResult().fail("用户数据异常!");
        }
        comment.setCommentUserId(user.getUserId());
        comment.setCommentAuthorName(user.getUserName());
        comment.setCommentAuthorUrl(user.getUserUrl());
        comment.setCommentAuthorEmail(user.getUserEmail());
        comment.setCommentAuthorAvatar(user.getUserAvatar());

        // 地址, 浏览器
        comment.setCommentIp(MyUtils.getIpAddress(request));
        comment.setCommentAgent(MyUtils.getUserAgent(request));

        comment.setCommentCreateTime(new Date());
        if (user.getUserRole().equals(UserRole.ADMIN.getValue())) {
            comment.setCommentRole(1);
        }else{
            comment.setCommentRole(0);
        }
        commentService.insertComment(comment);
        return new JsonResult().ok();
    }

}
