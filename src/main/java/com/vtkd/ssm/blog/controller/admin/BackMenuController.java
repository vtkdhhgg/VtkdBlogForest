package com.vtkd.ssm.blog.controller.admin;

import com.vtkd.ssm.blog.entity.Menu;
import com.vtkd.ssm.blog.enums.MenuLevel;
import com.vtkd.ssm.blog.mapper.MenuMapper;
import com.vtkd.ssm.blog.service.MenuService;
import io.swagger.annotations.Api;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 菜单 后台管理'
 *
 * @author 君上
 * @date 2022-7-25
 * */
@Api("菜单 后台管理")
@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 展示 菜单列表
     * @return
     */
    @RequestMapping("")
    public ModelAndView menuIndex(){
        ModelAndView modelAndView = new ModelAndView();
        // 查询所有menu
        List<Menu> menus = menuService.listMenu();
        modelAndView.addObject(menus);
        modelAndView.setViewName("Admin/Menu/index");
        return modelAndView;
    }

    /**
     * 展示编辑页面
     * @return
     */
    @RequestMapping("/edit/{menuId}")
    public ModelAndView menuEdit(@PathVariable("menuId") Integer menuId){
        ModelAndView modelAndView = new ModelAndView();

        Menu menuById = menuService.getMenuById(menuId);
        List<Menu> menus = menuService.listMenu();

        modelAndView.addObject(menuById);
        modelAndView.addObject(menus);

        modelAndView.setViewName("Admin/Menu/edit");
        return modelAndView;
    }

    /**
     * 添加 菜单
     * @param menu 菜单
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertMenu(Menu menu){
        if (menu.getMenuOrder() == null){
            menu.setMenuOrder(MenuLevel.TOP_MENU.getValue());
        }
        menuService.insertMenu(menu);

        return "redirect:/admin/menu/";
    }


    /**
     * 修改 菜单
     * @param menu 菜单
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String updateMenu(Menu menu){

        menuService.updateMenu(menu);

        return "redirect:/admin/menu/";
    }

    /**
     * 删除menu
     * @return
     */
    @RequestMapping("/delete/{menuId}")
    public String deleteMenu(@PathVariable("menuId") Integer menuId){

        menuService.deleteMenu(menuId);

        return "redirect:/admin/menu";
    }



}
