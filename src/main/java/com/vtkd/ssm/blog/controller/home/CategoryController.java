package com.vtkd.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Category;
import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.enums.ArticleStatus;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.CategoryService;
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
 * 分类管理
 *
 * @author 君上
 * @date 2022-7-29
 */
@Api("分类管理")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    /**
     * 根据分类查询文章
     *
     * @param pageIndex 起始页
     * @param pageSize 每页数据量
     * @param categoryId 分类id
     * @return
     */
    @RequestMapping("/category/{categoryId}")
    public ModelAndView getArticleByCategoryId(@RequestParam(required = false, defaultValue = "1")Integer pageIndex,
                                               @RequestParam(required = false, defaultValue = "10")Integer pageSize,
                                               @PathVariable("categoryId")Integer categoryId){
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            modelAndView.setViewName("redirect:/404");
            return modelAndView;
        }
        modelAndView.addObject("category", category);

        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("categoryId", categoryId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        // 文章
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);
        modelAndView.addObject("pageInfo", articlePageInfo);

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
        modelAndView.addObject("pageUrlPrefix", "/category/"+categoryId+"?pageIndex");

        modelAndView.setViewName("Home/Page/articleListByCategory");
        return modelAndView;
    }

}
