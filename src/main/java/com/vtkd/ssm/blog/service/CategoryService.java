package com.vtkd.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Category;

import java.util.List;

/**
 * 分类 服务层接口
 *
 * @author 君上
 * @date 2022-7-26 下午
 */
public interface CategoryService {


    /**
     * 根据 id 删除分类
     * @param categoryId id
     * @return 影响行数
     */
    void deleteCategoryById(Integer categoryId);

    /**
     * 修改 分类
     * @param category 分类
     * @return 影响行数
     */
    void updateCategory(Category category);

    /**
     * 添加分类
     * @param category 分类
     * @return 影响行数
     */
    void insertCategory(Category category);


    /**
     * 根据id查询分类
     * @param categoryId id
     * @return 影响行数
     */
    Category getCategoryById(Integer categoryId);

    /**
     * 查询所有分类，不分页
     * @return
     */
    List<Category> listCategory();

    /**
     * 分页查询分类
     * // todo 暂时不写sql，等后面写完整个项目再来完成这个功能
     * @param pageSize 每页多少条
     * @param pageIndex 第几页
     * @return 分页数据
     */
    PageInfo<Category> pageListCategory(Integer pageSize, Integer pageIndex);


    /**
     * 查询所有标签 和 文章数量
     * @return
     */
    List<Category> listCategoryWithCount();
}
