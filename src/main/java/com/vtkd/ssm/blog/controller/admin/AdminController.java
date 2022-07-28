package com.vtkd.ssm.blog.controller.admin;

import com.alibaba.fastjson2.JSON;
import com.vtkd.ssm.blog.dto.JsonResult;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Comment;
import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.enums.UserRole;
import com.vtkd.ssm.blog.service.ArticleService;
import com.vtkd.ssm.blog.service.CommentService;
import com.vtkd.ssm.blog.service.UserService;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.vtkd.ssm.blog.util.MyUtils.*;

/**
 * 后台 控制层
 */
@Api("后台管理")
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    /**
     * 后台首页
     *
     * @return
     */
    @ApiOperation(value = "跳转到后台首页")
    @RequestMapping(value = "/admin")
    public ModelAndView admin(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Integer userId = null;
        if (!user.getUserRole().equals(UserRole.ADMIN.getValue())) {
            // 管理员查询所有, 用户查询自己的
            userId = user.getUserId();
        }

        // 文章列表
        List<Article> articleList = articleService.listRecentArticle(userId, 5);
        modelAndView.addObject("articleList", articleList);

        // 评论列表
        List<Comment> comments = commentService.listRecentComment(userId, 5);
        modelAndView.addObject("commentList", comments);


        modelAndView.setViewName("Admin/index");
        return modelAndView;
    }

    /**
     * 登录页面
     *
     * @return 跳转到登录页面
     */
    @ApiOperation(value = "跳转到登录页面",notes = "没有业务代码")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "Admin/login";
    }

    /**
     * 注册页面
     *
     * @return 跳转到注册页面
     */
    @ApiOperation(value = "跳转到注册页面",notes = "没有业务代码")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "Admin/register";
    }


    /**
     * 登录 验证
     *
     * @return 结果信息
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    @ApiOperation(value = "用户登陆验证")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名称", name = "username"),
            @ApiImplicitParam(value = "密码", name = "password"),
            @ApiImplicitParam(value = "是否记住密码", name = "rememberme")
    })
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");

        User user = userService.getUserByName(username);
        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效!");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "密码错误!");
        } else if (user.getUserStatus() == 0) {
            map.put("code", 0);
            map.put("msg", "账号已禁用!请联系管理员.");
        } else {
            // 登录成功
            map.put("code", 1);
            map.put("msg", "");

            // 添加session 跳转到后台首页会读取 user 信息
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 添加cookie // 记住用户
            if (rememberme != null) {
                // 创建两个cookie对象
                Cookie nameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);
                // 设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                passwordCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
            }

            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(getIpAddress(request));
            userService.updateUser(user);
        }
        String result = JSON.toJSONString(map);
        return result;
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param nickname 用户昵称
     * @param email 电子邮箱
     * @param password 密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名称", name = "username"),
            @ApiImplicitParam(value = "密码", name = "password"),
            @ApiImplicitParam(value = "昵称", name = "nickname"),
            @ApiImplicitParam(value = "电子邮件", name = "email")
    })
    public JsonResult registerSubmit(@RequestParam("username") String username, @RequestParam("nickname") String nickname
       , @RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {

        // 判断用户名是否冲突
        User checkUsername = userService.getUserByName(username);
        User checkEmail = userService.getUserByEmail(email);
        if (checkUsername != null){
            return new JsonResult().fail("用户名重复");
        }
        if (checkEmail != null){
            return new JsonResult().fail("电子邮件重复");
        }

        User user = new User();
        user.setUserName(username);
        user.setUserPass(password);
        user.setUserNickname(nickname);
        user.setUserEmail(email);

        user.setUserRegisterTime(new Date());
        user.setUserLastLoginTime(new Date());
        user.setUserLastLoginIp(getIpAddress(request));
        // 随机设置用户头像
        String avatarName = "avatar"+ (new Random().nextInt(4)+1) +".png";
        user.setUserAvatar("/img/avatar/"+avatarName);
        user.setUserStatus(1);
        user.setUserRole("user");


        try {
            userService.insertUser(user);
        } catch (Exception e){
            e.printStackTrace();
            new JsonResult().fail("系统异常");
        }

        return new JsonResult().ok("注册成功");
    }

    /**
     * 退出登录
     * @param session session
     * @return 重新向到 login
     */
    @RequestMapping("/admin/logout")
    public String adminLogout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * 跳转到个人信息页面,并且展示
     * @return 页面
     */
    @RequestMapping("/admin/profile")
    public ModelAndView userProfileView(HttpSession session){

        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        User selectUser = userService.getUserById(user.getUserId());

        modelAndView.addObject("user", selectUser);

        modelAndView.setViewName("Admin/User/profile");

        return modelAndView;
    }

    /**
     * 编辑个人信息页面
     * @return 页面
     */
    @RequestMapping("/admin/profile/edit")
    public ModelAndView toProfileEditView(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        User selectUser = userService.getUserById(user.getUserId());

        modelAndView.addObject("user", selectUser);

        modelAndView.setViewName("Admin/User/editProfile");

        return modelAndView;
    }

    /**
     * 编辑用户提交
     * @param user 用户信息
     * @param session session
     * @return
     */
    @RequestMapping(value = "/admin/profile/save",method = RequestMethod.POST)
    public String saveProfile(User user, HttpSession session){
        User dbUser = (User) session.getAttribute("user");
        System.out.println(user);
        user.setUserId(dbUser.getUserId());
        userService.updateUser(user);
        return "redirect:/admin/profile";
    }



}
