package com.vtkd.ssm.blog.service.impl;

import com.vtkd.ssm.blog.entity.Notice;
import com.vtkd.ssm.blog.mapper.NoticeMapper;
import com.vtkd.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告 服务层实现
 *
 * @author 君上
 * @date 2022-7-25
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 增加公告
     * @param notice 公告
     */
    @Override
    public void insertNotice(Notice notice) {
        noticeMapper.insertNotice(notice);
    }

    /**
     * 根据id删除公獒
     * @param noticeId id
     */
    @Override
    public void deleteNoticeById(Integer noticeId) {
        noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 更新 公告
     * @param notice 公告
     */
    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }

    /**
     * 根据 id 查询公告
     * @param noticeId
     * @return
     */
    @Override
    public Notice getNoticeById(Integer noticeId) {
        return noticeMapper.getNoticeById(noticeId);
    }

    /**
     * 查询所有公告
     * @param status 状态
     * @return
     */
    @Override
    public List<Notice> listNotice(Integer status) {
        return noticeMapper.listNotice(status);
    }

    /**
     * 根据 status 查询公告总数
     * @param status 状态
     * @return
     */
    @Override
    public Integer countNotice(Integer status) {
        return noticeMapper.countNotice(status);
    }
}
