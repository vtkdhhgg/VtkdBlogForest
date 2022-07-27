package com.vtkd.ssm.blog.controller.home;


import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.LinkService;
import com.vtkd.ssm.blog.service.NoticeService;
import com.vtkd.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    private TagService tagService;

    @RequestMapping("/404")
    public ModelAndView notFound(@RequestParam(required = false) String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);

        modelAndView.setViewName("Home/Error/404");

        return modelAndView;
    }

    @RequestMapping("/500")
    public ModelAndView serverError(@RequestParam(required = false) String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);

        modelAndView.setViewName("Home/Error/500");

        return modelAndView;
    }

    @RequestMapping("/403")
    public ModelAndView page403(@RequestParam(required = false) String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);

        modelAndView.setViewName("Home/Error/403");

        return modelAndView;
    }

}
