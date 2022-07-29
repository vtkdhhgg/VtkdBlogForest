package com.vtkd.ssm.blog.controller.home;

import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Link;
import com.vtkd.ssm.blog.enums.ArticleStatus;
import com.vtkd.ssm.blog.enums.LinkStatus;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.LinkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MailDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 链接管理
 *
 */
@Api("链接管理")
@Controller
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/applyLink")
    public ModelAndView applyLink(){
        ModelAndView modelAndView = new ModelAndView();

        //侧边栏显示
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        modelAndView.addObject("mostCommentArticleList", mostCommentArticleList);

        modelAndView.setViewName("Home/Page/applyLink");
        return  modelAndView;
    }

    @RequestMapping(value = "/applyLinkSubmit", method = RequestMethod.POST)
    @ResponseBody
    public void applyLinkSubmit(Link link){

        link.setLinkStatus(LinkStatus.HIDDEN.getValue());
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(link.getLinkCreateTime());
        link.setLinkOrder(1);

        linkService.insertLink(link);
    }



}
