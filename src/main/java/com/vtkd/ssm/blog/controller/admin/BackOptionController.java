package com.vtkd.ssm.blog.controller.admin;

import com.vtkd.ssm.blog.entity.Option;
import com.vtkd.ssm.blog.service.OptionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网站的一些信息, 选项 管理
 */
@Api("网站的一些信息, 选项 管理")
@Controller
@RequestMapping("/admin")
public class BackOptionController {

    @Autowired
    private OptionService optionService;

    /**
     * 加载 option 数据 并返回option 编辑页面
     * @return
     */
    @RequestMapping("/options")
    public ModelAndView indexView(){
        ModelAndView modelAndView = new ModelAndView();

        Option optionById = optionService.getOptionById(1);

        modelAndView.addObject(optionById);

        modelAndView.setViewName("Admin/Options/index");

        return modelAndView;
    }

    /**
     * 更新 option
     * @param option 站点的一些信息
     * @return
     */
    @RequestMapping(value = "/options/editSubmit", method = RequestMethod.POST)
    public String  optionEditSubmit(Option option){
        System.out.println("获取到的=====>"+option);
        optionService.updateOption(option);

        return "redirect:/admin/options";
    }

}
