package com.vtkd.ssm.blog.mapper;

import com.vtkd.ssm.blog.entity.Link;

import java.util.List;

/**
 * 链接 数据库接口
 *
 * @author 君上
 * @date 2022-7-25
 * */
public interface LinkMapper {

    /**
     * 添加链接
     * @param link 链接
     * @return
     */
    int insertLink(Link link);

    /**
     * 根据 id 删除 链接
     * @param linkId id
     * @return
     */
    int deleteLink(Integer linkId);

    /**
     * 更新 链接
     * @param link 链接
     * @return
     */
    int updateLink(Link link);

    /**
     * 根据 id 获取链接
     * @param linkId id
     * @return
     */
    Link getLinkById(Integer linkId);

    /**
     * 链接列表
     * @param linkStatus 状态
     * @return
     */
    List<Link> listLink(Integer linkStatus);

    /**
     * 获得链接总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countLink(Integer status);

}
