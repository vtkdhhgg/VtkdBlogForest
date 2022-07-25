package com.vtkd.ssm.blog.controller.admin;

import com.vtkd.ssm.blog.entity.Link;
import com.vtkd.ssm.blog.service.LinkService;
import io.swagger.annotations.Api;
import org.apache.regexp.RE;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MailDateFormat;
import java.util.Date;
import java.util.List;

/**
 * link 链接 后台管理
 *
 * @author 君上
 * @date 2022-7-25
 */
@Api("链接 后台管理")
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;


    /**
     * link 列表 展示
     * @return
     */
    @RequestMapping("")
    public ModelAndView listLinkView(){
        ModelAndView modelAndView = new ModelAndView();
        // 查询所有 link
        List<Link> links = linkService.listLink(null);
        modelAndView.addObject(links);

        modelAndView.setViewName("Admin/Link/index");
        return modelAndView;
    }

    /**
     * 添加 link 页面显示
     * @return
     */
    @RequestMapping("/insert")
    public ModelAndView insertLinkView(){
        ModelAndView modelAndView = new ModelAndView();
        List<Link> links = linkService.listLink(null);
        modelAndView.addObject("linkList", links);

        modelAndView.setViewName("Admin/Link/insert");

        return modelAndView;
    }

    /**
     * 添加 link
     * @param link 链接
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertLinkSubmit(Link link){

        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        link.setLinkStatus(0);
        link.setLinkOrder(0);
        linkService.insertLink(link);

        return "redirect:/admin/link";
    }

    /**
     * 根据 id 删除 link
     * @param linkId id
     * @return
     */
    @RequestMapping("/delete/{linkId}")
    public String deleteLinkById(@PathVariable("linkId") Integer linkId){

        linkService.deleteLink(linkId);

        return "redirect:/admin/link";
    }

    /**
     * 更新 link
     * @param link 链接
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editLinkSubmit(Link link){

        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);

        return "redirect:/admin/link";
    }

    /**
     * 编辑链接页面显示
     * @param linkId id
     * @return
     */
    @RequestMapping("/edit/{linkId}")
    public ModelAndView linkEditView(@PathVariable("linkId") Integer linkId){
        ModelAndView modelAndView = new ModelAndView();
        Link linkCustom = linkService.getLinkById(linkId);
        modelAndView.addObject("linkCustom", linkCustom);

        modelAndView.addObject("linkList", linkService.listLink(null));
        modelAndView.setViewName("Admin/Link/edit");
        return modelAndView;
    }


}
