package com.vtkd.ssm.blog.interceptor;

import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.service.UserService;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 测试阶段使用
 * 只要是包含admin的url都添加 admin 用户到 session中
 * todo 测试阶段使用的 方便查看数据
 */
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().contains("admin")) {
            // 查询用户信息存入 session
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user == null) {
                User admin = userService.getUserByName("admin");
                request.getSession().setAttribute("user",admin);
            }
        }else{
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
