package com.vtkd.ssm.blog.service;


import com.vtkd.ssm.blog.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 君上
 * @date 2022/7/23
 */
public interface UserService {

    /**
     * 获得用户列表
     * @return 用户列表
     */
    List<User> listUser();

    /**
     * 通过id查询用户信息
     * @param id 用户id
     * @return 用户
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 根据 id删除用户
     * @param id 用户id
     */
    void deleteUser(Integer id);

    /**
     * 添加用户
     * @param user 用户
     * @return 用户
     */
    User insertUser(User user);

    /**
     * 根据用户名 或 邮箱 查询用户
     * @param str 用户名或Email
     * @return 用户
     */
    User getUserByNameOrEmail(String str);

    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户
     */
    User getUserByName(String name);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户
     */
    User getUserByEmail(String email);

}
