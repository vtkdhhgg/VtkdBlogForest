package com.vtkd.ssm.blog.service.impl;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Category;
import com.vtkd.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.vtkd.ssm.blog.mapper.CategoryMapper;
import com.vtkd.ssm.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类 服务层接口实现
 *
 * @author 君上
 * @date 2022-7-26 下午
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryRefMapper acMapper;

    @Override
    public void deleteCategoryById(Integer categoryId) {
        try {
            // 先判断 有没有文章,没有文章才能删除
            // 也一并把子分类删除
            Integer articleCount = acMapper.countArticleByCategoryId(categoryId);
            if (articleCount == 0) {
                categoryMapper.deleteCategoryById(categoryId);
            }
        } catch (Exception e) {
            log.error("分类删除失败，categoryId:{}, cause:{}", categoryId, e);
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryMapper.updateCategory(category);
        } catch (Exception e) {
            log.error("分类修改失败，category:{}, cause:{}", category, e);
        }
    }

    @Override
    public void insertCategory(Category category) {
        try {
            categoryMapper.insertCategory(category);
        } catch (Exception e) {
            log.error("分类插入失败，category:{}, cause:{}", category, e);
        }
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = null;
        try {
            category = categoryMapper.getCategoryById(categoryId);
        } catch (Exception e) {
            log.error("根据id获取分类失败，categoryId:{}, cause:{}", categoryId, e);
        }

        return category;
    }

    @Override
    public List<Category> listCategory() {

        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listCategory();
        } catch (Exception e) {
            log.error("获取所有分类失败, cause:{}", e);
        }
        return categoryList;

    }

    @Override
    public List<Category> listCategoryWithCount() {

        List<Category> categoryList = null;

        try {

            categoryList = categoryMapper.listCategory();
            for (Category category : categoryList) {
                Integer articleCount = acMapper.countArticleByCategoryId(category.getCategoryId());
                category.setArticleCount(articleCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取所有分类和文章数量失败, cause:{}", e);
        }

        return categoryList;
    }


    @Override
    public Integer countCategory() {
        Integer categoryCount = null;

        try {
            categoryCount = categoryMapper.countCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取所有分类数量失败, cause:{}", e);
        }

        return categoryCount;
    }
}
