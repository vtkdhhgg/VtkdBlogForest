package com.vtkd.ssm.blog.mapper;


import com.vtkd.ssm.blog.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 页面 数据库接口
 *
 * @updateAuthor 君上
 * @date 2022-7-26
 */
public interface PageMapper {

    /**
     * 根据Id 删除
     * @param pageId id
     * @return 影响行数
     */
    int deletePageById(Integer pageId);

    /**
     * 添加页面
     * @param page 页面
     * @return 影响行数
     */
    int insertPage(Page page);


    /**
     * 根据id获取页面
     * @param pageId id
     * @return 页面
     */
    Page getPageById(Integer pageId);

    /**
     * 根据key获取页面
     * @param pageStatus 状态
     * @param pageKey 别名
     * @return 页面
     */
    Page getPageByKey(@Param("pageStatus") Integer pageStatus,
                      @Param("pageKey") String pageKey);

    /**
     * 修改页面
     * @param page 页面
     * @return 影响行数
     */
    int updatePage(Page page);

    /**
     * 所有页面
     * @return 所有页面
     */
    List<Page> listPage(Integer pageStatus);


}
