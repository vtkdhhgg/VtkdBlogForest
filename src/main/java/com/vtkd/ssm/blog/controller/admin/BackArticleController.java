package com.vtkd.ssm.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.enums.UserRole;
import com.vtkd.ssm.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

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



        modelAndView.addObject("pageInfo"+articlePageInfo);
        modelAndView.setViewName("Admin/Article/index");
        return modelAndView;
    }



}
