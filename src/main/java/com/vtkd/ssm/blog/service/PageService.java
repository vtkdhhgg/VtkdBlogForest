package com.vtkd.ssm.blog.service;


import com.vtkd.ssm.blog.entity.Page;

import java.util.List;

/**
 * 页面 服务层接口
 *
 * @updateAuthor 君上
 * @date 2022-7-26
 */
public interface PageService {

    /**
     * 根据Id 删除
     * @param pageId id
     */
    void deletePageById(Integer pageId);

    /**
     * 添加页面
     * @param page 页面
     */
    void insertPage(Page page);


    /**
     * 根据id获取页面
     * @param pageId id
     * @return 页面
     */
    Page getPageById(Integer pageId);

    /**
     * 根据key获取页面
     *
     * @param pageStatus 状态
     * @param pageKey 页面key
     * @return 页面
     */
    Page getPageByKey(Integer pageStatus, String pageKey);

    /**
     * 修改页面
     * @param page 页面
     */
    void updatePage(Page page);

    /**
     * 所有页面
     *
     * @param pageStatus 状态
     * @return 所有页面
     */
    List<Page> listPage(Integer pageStatus);


}
