package com.vtkd.ssm.blog.controller.admin;

import cn.hutool.crypto.digest.MD5;
import com.vtkd.ssm.blog.entity.Page;
import com.vtkd.ssm.blog.service.PageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 页面 后台管理
 *
 * @author 君上
 * @date 2022-7-26
 */
@Api("页面后台管理")
@Controller
@RequestMapping("/admin/page")
public class BackPageController {

    @Autowired
    private PageService pageService;

    /**
     * page列表展示
     *
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<Page> pages = pageService.listPage(null);
        modelAndView.addObject("pageList", pages);

        modelAndView.setViewName("Admin/Page/index");
        return modelAndView;
    }

    /**
     * 修改页面 的页面 展示
     *
     * @param pageId
     * @return
     */
    @RequestMapping("/edit/{pageId}")
    public ModelAndView editPageView(@PathVariable("pageId")Integer pageId){
        ModelAndView modelAndView = new ModelAndView();
        Page page = pageService.getPageById(pageId);
        modelAndView.addObject("page", page);

        modelAndView.setViewName("Admin/Page/edit");
        return modelAndView;
    }

    /**
     * 修改 页面
     *
     * @param page 页面
     * @return
     */
    @RequestMapping("/editSubmit")
    public String editPageSubmit(Page page){
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());

        //逻辑应该是这样的，查询别名 为空直接修改，不为空判断是不是当前pageId，是修改，不是，不修改。
        if (checkPage == null){
            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }
        if (checkPage != null && Objects.equals(checkPage.getPageId(), page.getPageId())) {
            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }
        return "redirect:/admin/page";
    }

    /**
     * 添加页面展示
     *
     * @return
     */
    @RequestMapping("/insert")
    public String insertPageView(){
        return "Admin/Page/insert";
    }

    /**
     * 添加 页面
     * @param page 页面
     * @return
     */
    @RequestMapping("/insertSubmit")
    public String insertPageSubmit(Page page){
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());

        //没有 相同的别名才可以创建
        if (checkPage == null){
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(page.getPageUpdateTime());
            page.setPageStatus(1);
            pageService.insertPage(page);
        }
        return "redirect:/admin/page";
    }

    /**
     * 根据id删除页面
     * @param pageId id
     * @return
     */
    @RequestMapping("/delete/{pageId}")
    public String deletePage(@PathVariable("pageId")Integer pageId){
        pageService.deletePageById(pageId);

        return "redirect:/admin/page";
    }





}
