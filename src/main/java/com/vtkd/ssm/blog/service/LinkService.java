package com.vtkd.ssm.blog.service;

import com.vtkd.ssm.blog.entity.Link;

import java.util.List;

/**
 * link 服务层接口
 * @author 君上
 * @date 2022-7-25
 */
public interface LinkService {

    /**
     * 添加链接
     * @param link 链接
     * @return
     */
    void insertLink(Link link);

    /**
     * 根据 id 删除 链接
     * @param linkId id
     * @return
     */
    void deleteLink(Integer linkId);

    /**
     * 更新 链接
     * @param link 链接
     * @return
     */
    void updateLink(Link link);

    /**
     * 根据 id 获取链接
     * @param linkId id
     * @return
     */
    Link getLinkById(Integer linkId);


    /**
     * 根据 status 查询 link 列表
     * @param linkStatus
     * @return
     */
    List<Link> listLink(Integer linkStatus);

    /**
     * 根据 status 统计 link 列表
     * @param status
     * @return
     */
    Integer countLink(Integer status);
}
