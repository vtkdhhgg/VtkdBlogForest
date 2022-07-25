package com.vtkd.ssm.blog.controller;

import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 只是用来测试
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/id/save",method = RequestMethod.POST)
    public ModelAndView test(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");// 跳转页面

        System.out.println("user===>"+user);
        // 添加数据
        modelAndView.addObject("user", user);

        // 修改用户数据
        Integer userId = userService.getUserByName(user.getUserName()).getUserId();
        user.setUserId(userId);
        userService.updateUser(user);

        System.out.println("天地无极");
        System.out.println(System.getProperty("file.encoding"));
        return modelAndView;
    }
}
