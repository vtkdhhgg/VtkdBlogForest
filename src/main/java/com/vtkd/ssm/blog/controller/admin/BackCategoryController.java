package com.vtkd.ssm.blog.controller.admin;


import com.vtkd.ssm.blog.entity.Category;
import com.vtkd.ssm.blog.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 分类 后台管理
 *
 * @author 君上
 * @date 2022-7-26 下午
 */
@Api("分类 后台管理")
@Controller
@RequestMapping("/admin/category")
public class BackCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类 列表展示
     *
     * @return
     */
    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelAndView.addObject("categoryList", categoryList);

        modelAndView.setViewName("Admin/Category/index");
        return modelAndView;
    }

    /**
     * 跳转到添加分类页面
     *
     * @return
     */
    @RequestMapping("/insert")
    public String insertView() {
        return "redirect:/admin/category";
    }

    /**
     * 删除 分类
     *
     * @param categoryId 分类id
     * @return
     */
    @RequestMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Integer categoryId) {

        categoryService.deleteCategoryById(categoryId);

        return "redirect:/admin/category";
    }

    /**
     * 修改 分类页面展示
     *
     * @param categoryId id
     * @return
     */
    @RequestMapping("/edit/{categoryId}")
    public ModelAndView editCategoryView(@PathVariable("categoryId") Integer categoryId) {
        ModelAndView modelAndView = new ModelAndView();

        Category category = categoryService.getCategoryById(categoryId);
        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelAndView.addObject("category", category);
        modelAndView.addObject("categoryList", categoryList);

        modelAndView.setViewName("Admin/Category/edit");

        return modelAndView;
    }

    /**
     * 修改 分类提交
     *
     * @param category 分类
     * @return
     */
    @RequestMapping("/editSubmit")
    public String editCategorySubmit(Category category) {
        categoryService.updateCategory(category);

        return "redirect:/admin/category";
    }

    /**
     * 添加 分类提交
     *
     * @param category 分类
     * @return
     */
    @RequestMapping("/insertSubmit")
    public String insertCategorySubmit(Category category) {

        categoryService.insertCategory(category);

        return "redirect:/admin/category";
    }


}
