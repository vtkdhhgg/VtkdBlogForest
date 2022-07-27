package com.vtkd.ssm.blog.mapper;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *评论 数据层接口
 *
 * @author 君上
 * @date 2022-7-27
 */
public interface CommentMapper {

    /**
     * 根据 id 删除评论
     * @param commentId id
     * @return 影响行数
     */
    int deleteCommentById(Integer commentId);

    /**
     * 添加评论
     * @param comment 评论
     * @return 影响行数
     */
    int insertComment(Comment comment);


    /**
     * 更新 评论
     * @param comment 评论
     * @return 影响行数
     */
    int updateComment(Comment comment);

    /**
     * 返回所有的评论
     *
     * @return 所有评论
     */
    List<Comment> listComment();

    /**
     * 分页查询 评论
     * @param pageIndex 起始页
     * @param pageSize 每页数据量
     * @return 分页信息
     */
    PageInfo<Comment> pageComment(@Param("pageIndex") Integer pageIndex,
                                  @Param("pageSize") Integer pageSize);

    /**
     * 根据用户id 获取评论
     * @param userId 用户id
     * @return 用户评论
     */
    List<Comment> getCommentByUserId(Integer userId);



}
