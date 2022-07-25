package com.vtkd.ssm.blog.service.impl;

import com.vtkd.ssm.blog.entity.Menu;
import com.vtkd.ssm.blog.mapper.MenuMapper;
import com.vtkd.ssm.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务层接口实现
 *
 * @author 君上
 * @date 2022-7-25
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 添加 菜单
     * @param menu 菜单
     */
    @Override
    public void insertMenu(Menu menu) {
        menuMapper.insertMenu(menu);
    }

    /**
     * 删除 菜单
     * @param menuId id
     */
    @Override
    public void deleteMenu(Integer menuId) {
        menuMapper.deleteMenu(menuId);
    }

    /**
     *  更新menu
     * @param menu 菜单
     */
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }

    /**
     * 根据 id 查询 菜单
     * @param menuId id
     * @return
     */
    @Override
    public Menu getMenuById(Integer menuId) {
        return menuMapper.getMenuById(menuId);
    }

    /**
     * 查询 菜单列表
     * @return
     */
    @Override
    public List<Menu> listMenu() {
        return menuMapper.listMenu();
    }
}
