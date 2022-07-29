package com.vtkd.ssm.blog.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.dto.ArticleParam;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Category;
import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.enums.UserRole;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.CategoryService;
import com.vtkd.ssm.blog.service.TagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 文章 后台管理
 *
 * @author 君上
 * @date 2022-7-26
 * */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * 文章首页展示
     * @param pageIndex 第几页
     * @param pageSize 每页多少
     * @param status 状态
     * @param session session
     * @return 展示的文章数据
     */
    @RequestMapping("")
    public ModelAndView index(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              @RequestParam(required = false) Integer status, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        HashMap<String, Object> criteria = new HashMap<>();
        if (status == null){
            modelAndView.addObject("pageUrlPrefix", "/admin/article?pageIndex");
        }else{
            criteria.put("status", status);
            modelAndView.addObject("pageUrlPrefix", "/admin/article?status=" + status + "&pageIndex");
        }
        // 普通用户查询自己的文章，管理员获取所有文章。
         User user = (User) session.getAttribute("user");
        if (!UserRole.ADMIN.getValue().equals(user.getUserRole())) {
            criteria.put("userId", user.getUserId());
        }

        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex, pageSize, criteria);

        modelAndView.addObject("pageInfo", articlePageInfo);
        modelAndView.setViewName("Admin/Article/index");
        return modelAndView;
    }

    /**
     * 文章编辑页面展示
     * @param articleId 文章id
     * @return
     */
    @RequestMapping("/edit/{articleId}")
    public ModelAndView editArticleView(@PathVariable("articleId")Integer articleId, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        // 查询文章详细信息
        Article article = articleService.getArticleById(articleId);
        if (article == null){
            modelAndView.setViewName("redirect:/404");
            return modelAndView;
        }

        User user = (User) session.getAttribute("user");
        // 如果不是管理员,访问其他用户的数据,则跳转403 权限不足
        if (!article.getArticleUserId().equals(user.getUserId()) && !user.getUserRole().equals(UserRole.ADMIN.getValue())){
            modelAndView.setViewName("redirect:/403");
            return modelAndView;
        }

        modelAndView.addObject("article",article);

        // 查询 所有分类
        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);
        // 查询 所有标签
        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("tagList", tagList);

        modelAndView.setViewName("Admin/Article/edit");
        return modelAndView;
    }

    /**
     * 修改 文章
     * @param articleParam 文章
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam, HttpSession session){

        Article dbArticle = articleService.getArticleById(articleParam.getArticleId());
        if (dbArticle == null){
            return "redirect:/404";
        }

        User user = (User) session.getAttribute("user");
        if (!dbArticle.getArticleUserId().equals(user.getUserId()) && !user.getUserRole().equals(UserRole.ADMIN.getValue())){
            return "redirect:/403";
        }
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleOrder(articleParam.getArticleOrder());
        article.setArticleStatus(articleParam.getArticleStatus());
        article.setArticleThumbnail(articleParam.getArticleThumbnail());

        // 文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if (summaryText.length() > summaryLength){
            summaryText = summaryText.substring(0, 150);
            article.setArticleSummary(summaryText);
        }else{
            article.setArticleSummary(summaryText);
        }

        // 填充分类
        ArrayList<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleChildCategoryId() != null){
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null){
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        // 填充标签
        ArrayList<Tag> tagList = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null){
            for (int i = 0; i < articleParam.getArticleTagIds().size(); i++) {
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.updateArticleDetail(article);
        return "redirect:/admin/article";
    }


    /**
     * 写文章 页面展示
     * @return
     */
    @RequestMapping("/insert")
    public ModelAndView insertArticleView(){
        ModelAndView modelAndView = new ModelAndView();
        // 将分类和标签信息 返回
        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList", categoryList);
        List<Tag> tagList = tagService.listTag();
        modelAndView.addObject("tagList", tagList);

        modelAndView.setViewName("Admin/Article/insert");
        return modelAndView;
    }

    /**
     * 添加文章提交
     * @param articleParam 添加文章
     * @param session session 用来获取用户
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertArticleSubmit(ArticleParam articleParam, HttpSession session){

        if (articleParam == null){
            return "redirect:/admin/article";
        }
        User user = (User) session.getAttribute("user");
        Article article = new Article();
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        article.setArticleThumbnail(articleParam.getArticleThumbnail());
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(article.getArticleCreateTime());
        article.setArticleOrder(1);
        article.setArticleViewCount(0);
        article.setArticleIsComment(1);
        article.setArticleLikeCount(0);
        article.setArticleViewCount(0);
        article.setArticleUserId(user.getUserId());

        // 获取缩略图
        String summaryContent = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (Objects.requireNonNull(summaryContent).length() > 150){
            summaryContent = summaryContent.substring(0, 150);
        }
        article.setArticleSummary(summaryContent);

        // 获取分类
        ArrayList<Category> categories = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId() != null) {
            categories.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categories.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categories);

        // 获取标签
        ArrayList<Tag> tags = new ArrayList<>();
        if (articleParam.getArticleTagIds() != null && articleParam.getArticleTagIds().size() != 0){
            for (Integer tagId : articleParam.getArticleTagIds()) {
                tags.add(new Tag(tagId));
            }
        }
        article.setTagList(tags);

        articleService.insertArticle(article);
        return "redirect:/admin/article";
    }

    /**
     * 删除文章
     * @param articleId
     * @param session
     * @return
     */
    @RequestMapping("/delete/{articleId}")
    public String deleteArticle(@PathVariable("articleId")Integer articleId, HttpSession session){
        String returnView = "redirect:/admin/article";
        Article dbArticle = articleService.getArticleById(articleId);
        if (dbArticle == null){
            return returnView;
        }

        User user = (User) session.getAttribute("user");
        // 非管理员, 不能操作其他用户的数据
        if (!Objects.equals(dbArticle.getArticleId(), articleId) && !user.getUserRole().equals(UserRole.ADMIN.getValue())){
            return returnView;
        }

        articleService.deleteArticle(articleId);
        return returnView;
    }

    /**
     * 添加草稿
     * @param articleParam 精简文章
     * @param session
     * @return
     */
    @RequestMapping(value = "/insertDraftSubmit", method = RequestMethod.POST)
    public String insertDraftArticleSubmit(ArticleParam articleParam, HttpSession session){
        if (articleParam == null){
            return "redirect:/admin";
        }
        User user = (User) session.getAttribute("user");
        Article article = new Article();
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(article.getArticleCreateTime());
        article.setArticleOrder(1);
        article.setArticleIsComment(1);
        article.setArticleCommentCount(0);
        article.setArticleLikeCount(0);
        article.setArticleViewCount(0);
        article.setArticleUserId(user.getUserId());

        // 获取缩略 文章
        String summaryContent = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if (Objects.requireNonNull(summaryContent).length() > 150){
            summaryContent = summaryContent.substring(0, 150);
        }
        article.setArticleSummary(summaryContent);

        articleService.insertArticle(article);

        return "redirect:/admin";
    }

}