package com.vtkd.ssm.blog.service;

import com.vtkd.ssm.blog.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 服务层接口
 * @author 君上
 * @date 2022-7-25
 */
public interface MenuService {

    /**
     * 添加 菜单
     * @param menu 菜单
     * @return
     */
    void insertMenu(Menu menu);

    /**
     * 通过 id 删除 菜单
     * @param menuId id
     * @return
     */
    void deleteMenu(Integer menuId);

    /**
     * 更新 menu
     * @param menu 菜单
     * @return
     */
    void updateMenu(Menu menu);

    /**
     * 根据 id 查询 菜单
     * @param menuId
     * @return
     */
    Menu getMenuById(Integer menuId);

    /**
     * 菜单列表
     * @return
     */
    List<Menu> listMenu();

}
