package com.vtkd.ssm.blog.controller.home;

import cn.hutool.crypto.digest.MD5;
import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.enums.ArticleStatus;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * 标签 管理
 */
@Api("标签管理")
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/tag/{tagId}")
    public ModelAndView tagArticle(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                   @PathVariable("tagId") Integer tagId) {
        ModelAndView modelAndView = new ModelAndView();
        Tag tag = tagService.getTagById(tagId);
        if (tag == null) {
            modelAndView.setViewName("redirect:/4004");
            return modelAndView;
        }
        modelAndView.addObject("tag", tag);

        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("tagId", tagId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        // 获取所有包含 tagId的文章
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex, pageSize,criteria);
        modelAndView.addObject("pageInfo", articleList);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag();
        modelAndView.addObject("allTagList", allTagList);
        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        modelAndView.addObject("randomArticleList", randomArticleList);
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);
        modelAndView.addObject("pageUrlPrefix", "/tag/"+tagId+"?pageIndex");

        modelAndView.setViewName("Home/Page/articleListByTag");
        return modelAndView;
    }


}
