package com.vtkd.ssm.blog.service.impl;

import com.vtkd.ssm.blog.entity.Page;
import com.vtkd.ssm.blog.entity.Tag;
import com.vtkd.ssm.blog.mapper.PageMapper;
import com.vtkd.ssm.blog.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 页面 服务层接口实现
 *
 * @author 君上
 * @date 2022-7-26
 */
@Slf4j
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageMapper pageMapper;

    /**
     * 根据Id 删除
     * @param pageId id
     */
    @Override
    public void deletePageById(Integer pageId){
        try{
            pageMapper.deletePageById(pageId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("删除页面失败, pageId:{}, cause:{}", pageId, e);
        }
    }

    /**
     * 添加页面
     * @param page 页面
     */
    @Override
    public void insertPage(Page page){
        try{
            pageMapper.insertPage(page);
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加页面失败, Page:{}, cause:{}", page, e);
        }
    }


    /**
     * 根据id获取页面
     * @param pageId id
     * @return 页面
     */
    @Override
    public Page getPageById(Integer pageId){
        Page page = null;
        try{
            page =  pageMapper.getPageById(pageId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取页面失败, pageId:{}, cause:{}", pageId, e);
        }
        return page;
    }

    /**
     * 根据id获取页面
     * @param pageKey 页面key
     * @return 页面
     */
    @Override
    public Page getPageByKey(Integer status, String pageKey){
        Page page = null;
        try{
            page =  pageMapper.getPageByKey(status, pageKey);
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取页面失败, pageKey:{}, cause:{}", pageKey, e);
        }
        return page;
    }

    /**
     * 修改页面
     * @param page 页面
     */
    @Override
    public void updatePage(Page page){
        try{
            pageMapper.updatePage(page);
        }catch (Exception e){
            e.printStackTrace();
            log.error("修改页面失败, Page:{}, cause:{}", page, e);
        }
    }

    /**
     * 所有页面
     * @return 所有页面
     */
    @Override
    public List<Page> listPage(Integer pageStatus){
        List<Page> pageList = null;
        try{
            pageList=  pageMapper.listPage(pageStatus);
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取页面列表失败, cause:{}", e);
        }
        return pageList;
    }

}
