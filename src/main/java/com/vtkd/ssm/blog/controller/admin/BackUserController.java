package com.vtkd.ssm.blog.controller.admin;

import com.vtkd.ssm.blog.dto.JsonResult;
import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 后台用户信息管理
 */
@Api("后台用户信息管理")
@Controller
@RequestMapping("/admin/user")
public class BackUserController {

    @Autowired
    private UserService userService;

    /**
     * 用户信息列表
     *
     * @return
     */
    @RequestMapping("")
    public ModelAndView userListView() {
        ModelAndView modelAndView = new ModelAndView();
        // 需要用户信息列表
        List<User> users = userService.listUser();
        modelAndView.addObject("userList", users);

        // 返回到页面
        modelAndView.setViewName("Admin/User/index");

        return modelAndView;
    }

    /**
     * 个人信息编辑页面
     *
     * @param userId 用户id
     * @return
     */
    @ApiOperation("个人信息编辑页面")
    @RequestMapping("/edit/{userId}")
    public ModelAndView editUserView(Integer userId) {
        //根据id查询user
        User user = userService.getUserById(userId);

        // 返回
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("Admin/User/edit");
        return modelAndView;
    }

    /**
     * 修改用户信息
     *
     * @param user    用户
     * @param session
     * @return
     */
    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public ModelAndView editSubmit(User user, HttpSession session) {
        // 修改用户信息
        userService.updateUser(user);
        User sessionUser = userService.getUserById(user.getUserId());
        session.setAttribute("user", sessionUser);

        return new ModelAndView("/Admin/User/edit");
    }

    /**
     * 检查 用户名是否重复, 除了自己的
     *
     * @param userName 用户名
     * @param userId   用户id
     * @return
     */
    @ApiOperation("检查 用户名是否重复, 除了自己的")
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult checkUserName(@RequestParam("username") String userName
            , @RequestParam("id") Integer userId) {

        User checkUser = userService.getUserByName(userName);
        // 如果查询到 这个用户就是自己那么就不算是重复
        if (checkUser != null && !Objects.equals(checkUser.getUserId(), userId)) {
            // 用户名重复
            return new JsonResult().fail("用户名重复");
        }
        // 用户名可用
        return new JsonResult().ok("用户名可用");
    }


    /**
     * 检查 用户邮箱地址是否重复, 除了自己的
     *
     * @param userEmail
     * @param userId
     * @return
     */
    @ApiOperation("检查 用户邮箱地址是否重复, 除了自己的")
    @RequestMapping(value = "/checkUserEmail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult checkUserEmail(@RequestParam("email") String userEmail
            , @RequestParam("id") Integer userId) {

        User checkEmail = userService.getUserByName(userEmail);
        // 如果查询到 这个用户就是自己那么就不算是重复(除了自己)
        if (checkEmail != null && !checkEmail.getUserId().equals(userId)) {
            // 电子邮件
            return new JsonResult().fail("电子邮件重复");
        }
        // 电子邮件
        return new JsonResult().ok("电子邮件可用");
    }

    /**
     * 根据id 删除用户
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据 id 删除用户")
    @RequestMapping("/delete/{userId}")
    public ModelAndView deleteUserById(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return new ModelAndView("redirect:/admin/user");
    }

    /**
     * 跳转到 inset user页面
     *
     * @return
     */
    @RequestMapping("/insert")
    public ModelAndView insertUserView() {
        return new ModelAndView("Admin/User/insert");
    }

    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public ModelAndView insertUser(User user) {

        // 初始化用户
        user.setUserRegisterTime(new Date());
        user.setUserStatus(1);
        user.setUserRole("user");

        userService.insertUser(user);

        return new ModelAndView("redirect:/admin/user");
    }

}
