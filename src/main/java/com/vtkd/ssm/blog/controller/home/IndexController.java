package com.vtkd.ssm.blog.controller.home;


import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.*;
import com.vtkd.ssm.blog.enums.ArticleStatus;
import com.vtkd.ssm.blog.enums.LinkStatus;
import com.vtkd.ssm.blog.enums.NoticeStatus;
import com.vtkd.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * 页面管理
 * 一些页面的跳转 404...
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TagService tagService;

    /**
     * 前台首页展示
     * @param pageIndex 起始页
     * @param pageSize 每页多少
     * @return
     */
    @RequestMapping({"/", "/article"})
    public ModelAndView index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        // articleList
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex, pageSize, criteria);
        modelAndView.addObject("pageInfo", articleList);

        // noticeList
        List<Notice> noticeList = noticeService.listNotice(NoticeStatus.NORMAL.getValue());
        modelAndView.addObject("noticeList", noticeList);

        // pageInfo.list article category tag
        // linkList
        List<Link> linkList = linkService.listLink(LinkStatus.NORMAL.getValue());
        modelAndView.addObject("linkList", linkList);

        // 侧边栏显示
        //标签列表显示
        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("allTagList", tagList);

        // 最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(null, 10);
        modelAndView.addObject("recentCommentList", recentCommentList);

        modelAndView.addObject("pageUrlPrefix", "/article?pageIndex");
        modelAndView.setViewName("Home/index");
        return modelAndView;
    }

    
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(required = false, defaultValue = "1")Integer pageIndex,
                               @RequestParam(required = false, defaultValue = "10")Integer pageSize,
                               @RequestParam("keywords")String keywords){

        ModelAndView modelAndView = new ModelAndView();

        // 文章列表
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("keywords", keywords);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        PageInfo<Article> pageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        modelAndView.addObject("pageInfo", pageInfo);

        //侧边栏显示
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        modelAndView.addObject("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        modelAndView.addObject("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);
        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(null, 10);
        modelAndView.addObject("recentCommentList", recentCommentList);
        modelAndView.addObject("pageUrlPrefix", "/search?keywords=" + keywords + "&pageIndex");

        modelAndView.setViewName("Home/Page/search");
        return modelAndView;
    }
    
    @RequestMapping("/404")
    public ModelAndView notFound(@RequestParam(required = false) String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);

        modelAndView.setViewName("Home/Error/404");

        return modelAndView;
    }

    @RequestMapping("/500")
    public ModelAndView serverError(@RequestParam(required = false) String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);

        modelAndView.setViewName("Home/Error/500");

        return modelAndView;
    }

    @RequestMapping("/403")
    public ModelAndView page403(@RequestParam(required = false) String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);

        modelAndView.setViewName("Home/Error/403");

        return modelAndView;
    }

}
