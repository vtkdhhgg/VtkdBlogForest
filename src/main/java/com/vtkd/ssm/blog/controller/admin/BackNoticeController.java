package com.vtkd.ssm.blog.controller.admin;

import com.vtkd.ssm.blog.entity.Notice;
import com.vtkd.ssm.blog.mapper.NoticeMapper;
import com.vtkd.ssm.blog.service.NoticeService;
import io.swagger.annotations.Api;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 公告 后台管理
 *
 * @author 君上
 * @date 2022-7-25
 */
@Api("公告 后台管理")
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 全部 公告展示
     * @return
     */
    @RequestMapping("")
    public ModelAndView noticeListView(){
        ModelAndView modelAndView = new ModelAndView();
        List<Notice> notices = noticeService.listNotice(null);
        modelAndView.addObject("noticeList", notices);

        modelAndView.setViewName("Admin/Notice/index");
        return modelAndView;
    }

    /**
     * 编辑 公告页面展示
     * @param noticeId id
     * @return
     */
    @RequestMapping("/edit/{noticeId}")
    public ModelAndView editNoticeView(@PathVariable("noticeId")Integer noticeId){
        ModelAndView modelAndView = new ModelAndView();
        Notice noticeById = noticeService.getNoticeById(noticeId);
        modelAndView.addObject("notice",noticeById);
        modelAndView.setViewName("Admin/Notice/edit");
        return modelAndView;
    }

    /**
     * 更新 公告
     * @param notice 公告
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice){

        notice.setNoticeUpdateTime(new Date());
        noticeService.updateNotice(notice);

        return "redirect:/admin/notice";
    }

    /**
     * 删除 公告
     * @param noticeId id
     * @return
     */
    @RequestMapping("/delete/{noticeId}")
    public String deleteNoticeById(@PathVariable("noticeId") Integer noticeId){

        noticeService.deleteNoticeById(noticeId);

        return "redirect:/admin/notice";
    }

    /**
     * 添加 公告页面展示
     * @return
     */
    @RequestMapping("insert")
    public String insertNoticeView(){
        return "Admin/Notice/insert";
    }

    /**
     * 添加 公告
     * @param notice 公告
     * @return
     */
    @RequestMapping("/insertSubmit")
    public String insertNoticeSubmit(Notice notice){
        
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(notice.getNoticeCreateTime());
        notice.setNoticeStatus(1);
        notice.setNoticeOrder(1);
        noticeService.insertNotice(notice);

        return "redirect:/admin/notice";
    }



}
