package com.vtkd.ssm.blog.service.impl;

import com.vtkd.ssm.blog.entity.User;
import com.vtkd.ssm.blog.mapper.UserMapper;
import com.vtkd.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户 service 实现
 * @author 刘帮君
 * @date 2022/7/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    @Override
    public User getUserById(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    /**
     * 修改用户信息
     * @param user 用户
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 根据id删除用户
     * @param id 用户id
     */
    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    /**
     * 添加用户
     * @param user 用户
     * @return 用户
     */
    @Override
    public User insertUser(User user) {
        int i = userMapper.insertUser(user);
        return user;
    }

    /**
     * 根据用户名or邮件查询用户
     * @param str 用户名或Email
     * @return 用户
     */
    @Override
    public User getUserByNameOrEmail(String str) {
        return userMapper.getUserByNameOrEmail(str);
    }


    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户
     */
    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    /**
     * 根据邮箱查询用户信息
     * @param email 邮箱
     * @return 用户
     */
    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }
}
