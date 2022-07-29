package com.vtkd.ssm.blog.controller.home;

import com.alibaba.fastjson.JSON;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Comment;
import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 前台 文章控制
 */
@Api("前台 文章控制")
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    /**
     * 文章展示
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/{articleId}")
    public ModelAndView detailArticleView(@PathVariable("articleId") Integer articleId) {
        ModelAndView modelAndView = new ModelAndView();

        // 查询 文章 分类,标签
        Article article = articleService.getArticleById(articleId);
        if (article == null) {
            modelAndView.setViewName("redirect:/404");
            return modelAndView;
        }
        modelAndView.addObject(article);

        // 查询文章评论
        List<Comment> commentList = commentService.getCommentByArticleId(articleId);
        modelAndView.addObject("commentList", commentList);

        // 查询用户
        User user = userService.getUserById(article.getArticleUserId());
        article.setUser(user);

        // 相关文章
        List<Article> similarArticleList = articleService.listArticleByArticleId(articleId, 5);
        modelAndView.addObject("similarArticleList", similarArticleList);

        // 猜你喜欢
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
        modelAndView.addObject("mostViewArticleList", mostViewArticleList);

        // 获取下一篇
        Article afterArticle = articleService.getAfterArticle(articleId);
        modelAndView.addObject("afterArticle", afterArticle);

        // 获取上一篇
        Article preArticle = articleService.getPreArticle(articleId);
        modelAndView.addObject("preArticle", preArticle);
        // 获取随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        modelAndView.addObject("randomArticleList", randomArticleList);

        // 获取热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);


        // 侧边栏
        // 标签列表显示
        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("allTagList", tagList);

        modelAndView.setViewName("/Home/Page/articleDetail");
        return modelAndView;
    }

    /**
     * 文章浏览数增加
     *
     * @param articleId 文章id
     */
    @RequestMapping(value = "/view/{articleId}", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String articleViewIncrease(@PathVariable("articleId") Integer articleId) {
        articleService.articleViewIncrease(articleId);
        Integer articleViewCount = articleService.getArticleById(articleId).getArticleViewCount();
        return JSON.toJSONString(articleViewCount);
    }

    /**
     * 点赞+1
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/like/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public String articleLikeIncrease(@PathVariable("articleId")Integer articleId){
        articleService.articleLikeIncrease(articleId);
        Integer likeCount = articleService.getArticleById(articleId).getArticleLikeCount();
        return JSON.toJSONString(likeCount);
    }

}
