package com.vtkd.ssm.blog.controller.home;

import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Notice;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 公告管理
 */
@Api("公告 管理")
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/notice/{noticeId}")
    public ModelAndView noticeView(@PathVariable("noticeId") Integer noticeId){
        ModelAndView modelAndView = new ModelAndView();

        Notice notice = noticeService.getNoticeById(noticeId);
        modelAndView.addObject("notice", notice);

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);

        modelAndView.setViewName("Home/Page/noticeDetail");
        return modelAndView;
    }


}
