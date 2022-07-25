package com.vtkd.ssm.blog.service.impl;

import com.vtkd.ssm.blog.entity.Link;
import com.vtkd.ssm.blog.mapper.LinkMapper;
import com.vtkd.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * link 服务层接口实现
 *
 * @author 君上
 * @date 2022-7-25
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 添加 link
     * @param link 链接
     */
    @Override
    public void insertLink(Link link) {
        linkMapper.insertLink(link);
    }


    /**
     * 根据 id 删除 link
     * @param linkId id
     */
    @Override
    public void deleteLink(Integer linkId) {
        linkMapper.deleteLink(linkId);
    }

    /**
     * 更新 link
     * @param link 链接
     */
    @Override
    public void updateLink(Link link) {
        linkMapper.updateLink(link);
    }

    /**
     * 根据 id 查询 link
     * @param linkId id
     * @return
     */
    @Override
    public Link getLinkById(Integer linkId) {
        return linkMapper.getLinkById(linkId);
    }

    /**
     * 查询 link 列表
     * @return
     */
    @Override
    public List<Link> listLink(Integer linkStatus) {
        return linkMapper.listLink(linkStatus);
    }

    /**
     * 获得链接总数
     *
     * @param status 状态
     * @return 数量
     */
    @Override
    public Integer countLink(Integer status){
        return linkMapper.countLink(status);
    }
}
