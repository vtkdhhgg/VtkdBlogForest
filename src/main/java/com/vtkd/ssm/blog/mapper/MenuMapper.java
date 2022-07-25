package com.vtkd.ssm.blog.mapper;

import com.vtkd.ssm.blog.entity.Menu;

import java.util.List;

/**
 * 菜单 数据库接口
 * @author 君上
 * @date 2022-7-24
 */
public interface MenuMapper {


    /**
     * 添加 菜单
     * @param menu 菜单
     * @return
     */
    int insertMenu(Menu menu);

    /**
     * 通过 id 删除 菜单
     * @param menuId id
     * @return
     */
    int deleteMenu(Integer menuId);

    /**
     * 更新 menu
     * @param menu 菜单
     * @return
     */
    int updateMenu(Menu menu);

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
