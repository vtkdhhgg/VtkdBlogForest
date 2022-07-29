package com.vtkd.ssm.blog.mapper;


import com.vtkd.ssm.blog.entity.ArticleCategoryRef;
import com.vtkd.ssm.blog.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章和分类 关联类 数据层接口映射
 *
 * @author 君上
 * @date 2022-7-26 下午
 */
public interface ArticleCategoryRefMapper {

    /**
     * 根据 分类id 查询 相关联文章总数
     * @param categoryId 分类id
     * @return 文章总数
     */
    Integer countArticleByCategoryId(Integer categoryId);

    /**
     * 根据 文章id 获取相关联分类 列表
     * @param articleId 文章id
     * @return 关联分类id
     */
    List<Category> getCategoryListByArticleId(Integer articleId);

    /**
     * 添加 文章和分类的映射关系
     * @param articleCategoryRef 文章和分类
     * @return 影响行数
     */
    int insertArticleCategoryRef(ArticleCategoryRef articleCategoryRef);

    /**
     * 根据 文章id 删除分类
     * @param articleId 文章id
     * @return 影响行数
     */
    int deleteByArticleId(Integer articleId);
}
