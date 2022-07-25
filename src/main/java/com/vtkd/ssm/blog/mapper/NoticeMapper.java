package com.vtkd.ssm.blog.mapper;


import com.vtkd.ssm.blog.entity.Notice;

import java.util.List;

/**
 * 公告 数据库接口
 * @author 君上
 * @date 2022-7-25
 */
public interface NoticeMapper {

    /**
     * 增加 公告
     * @param notice 公告
     * @return
     */
    int insertNotice(Notice notice);

    /**
     * 根据 id 删除公告
     * @param noticeId
     * @return
     */
    int deleteNoticeById(Integer noticeId);

    /**
     * 更新 公告
     * @param notice 公告
     * @return
     */
    int updateNotice(Notice notice);

    /**
     * 根据 id 查询公告
     * @param noticeId
     * @return
     */
    Notice getNoticeById(Integer noticeId);


    /**
     * 查询所有公告
     * @param status 状态
     * @return
     */
    List<Notice> listNotice(Integer status);

    /**
     * 根据 status 查询公告总数
     * @param status 状态
     * @return
     */
    Integer countNotice(Integer status);

}
