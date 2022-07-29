package com.vtkd.ssm.blog.mapper;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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
     * @param criteria 条件
     * @return 所有评论
     */
    List<Comment> findAll(HashMap<String, Object> criteria);

    /**
     * 根据 pid 查询评论
     * @param pid 父级评论
     * @return 评论
     */
    List<Comment> getCommentByPid(Integer pid);


    /**
     * 根据 文章id 查询 评论我的
     *
     * @param articleId 文章id
     * @return 评论
     */
    List<Comment> getReceiveCommentByArticleId(Integer articleId);

    /**
     * 根据 Id 查询 评论
     *
     * @param commentId id
     * @return 评论
     */
    Comment getCommentById(Integer commentId);


    /**
     * 根据 文章id 删除其所有评论
     * @param articleId 文章id
     * @return 影响行数
     */
    int deleteCommentByArticleId(Integer articleId);

    /**
     * 获取最新评论
     *
     * @param userId 用户id
     * @param limit 查询数量
     * @return 评论列表
     */
    List<Comment> listRecentComment(@Param("userId") Integer userId,
                                    @Param("limit") Integer limit);

    /**
     * 根据 文章id 获取评论
     * @param articleId 文章id
     * @return 评论
     */
    List<Comment> getCommentByArticleId(Integer articleId);

    /**
     * 获取评论总数量
     * @return 评论总数
     */
    Integer countComment();
}
