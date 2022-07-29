package com.vtkd.ssm.blog.interceptor;

import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Category;
import com.vtkd.ssm.blog.entity.Menu;
import com.vtkd.ssm.blog.entity.Option;
import com.vtkd.ssm.blog.enums.ArticleStatus;
import com.vtkd.ssm.blog.enums.LinkStatus;
import com.vtkd.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * home 提前加载资源
 * 再请求处理之前,该方法主要是用于准备资源数据的,然后可以把他们当做请求属性放到 webRequest 中
 *
 * @date 20022-7-28 下午
 */
public class HomeResourceInterceptor  implements HandlerInterceptor {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CommentService commentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 菜单显示
        List<Menu> menuList = menuService.listMenu();
        request.setAttribute("menuList", menuList);

        // 分类
        List<Category> categoryList = categoryService.listCategory();
        request.setAttribute("allCategoryList", categoryList);

        // 获得网站概况
        ArrayList<String> siteBasicStatistics = new ArrayList<>();
        // 有效的文章总数
        siteBasicStatistics.add(articleService.countArticle(ArticleStatus.PUBLISH.getValue()) + "");
        // 评论总数
        siteBasicStatistics.add(commentService.countComment() + "");
        // 分类总数
        siteBasicStatistics.add(categoryService.countCategory() + "");
        // 标签总数
        siteBasicStatistics.add(tagService.countTag() + "");
        // 有效链接总数
        siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue()) + "");
        // 观看文章总数
        siteBasicStatistics.add(articleService.countArticleView() + "");
        request.setAttribute("siteBasicStatistics", siteBasicStatistics);

        // 最后更新的文章
        Article lastUpdateArticle = articleService.getLastUpdateArticle();
        request.setAttribute("lastUpdateArticle", lastUpdateArticle);

        // 页脚显示 博客基本信息显示(Options)
         Option option = optionService.listOption();
         request.setAttribute("options", option);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
