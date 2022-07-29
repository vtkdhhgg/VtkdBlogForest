package com.vtkd.ssm.blog.controller.home;

import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Category;
import com.vtkd.ssm.blog.entity.Page;
import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.CategoryService;
import com.vtkd.ssm.blog.service.PageService;
import com.vtkd.ssm.blog.service.TagService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 页面管理
 */
@Api("页面管理, 归档,站点地图,留言板..")
@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 留言板
     *
     * @return
     */
    @RequestMapping("/message")
    public ModelAndView message(){
        ModelAndView modelAndView = new ModelAndView();

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);

        modelAndView.setViewName("Home/Page/message");
        return modelAndView;
    }

    /**
     * 站点地图显示
     *
     * @return
     */
    @RequestMapping("/map")
    public ModelAndView map(){
        ModelAndView modelAndView = new ModelAndView();
        // 文章显示
        List<Article> articles = articleService.listAllNotWithContent();
        modelAndView.addObject("articleList", articles);

        //分类显示
        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);

        //标签显示
        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("tagList", tagList);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);

        modelAndView.setViewName("Home/Page/siteMap");
        return modelAndView;
    }

    /**
     * 文章归档 页面
     *
     * @return
     */
    @RequestMapping("/articleFile")
    public ModelAndView articleFile(){
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = articleService.listAllNotWithContent();
        modelAndView.addObject("articleList", articles);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);

        modelAndView.setViewName("Home/Page/articleFile");
        return modelAndView;
    }

    /**
     * 页面详情页面
     * @param key 页面别名
     * @return
     */
    @RequestMapping("/{key}")
    public ModelAndView pageDetail(@PathVariable("key") String key){
        ModelAndView modelAndView = new ModelAndView();
        Page page = pageService.getPageByKey(1, key);
        if (page == null) {
            modelAndView.setViewName("redirect:/404");
            return modelAndView;
        }
        modelAndView.addObject("page", page);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);

        modelAndView.setViewName("Home/Page/page");
        return modelAndView;
    }






}
