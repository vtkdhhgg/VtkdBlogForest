package com.vtkd.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 *评论 服务层接口
 *
 * @author 君上
 * @date 2022-7-27
 */
public interface CommentService {

    /**
     * 根据 id 删除评论及其子评论
     * @param commentId id
     */
    void deleteCommentById(Integer commentId);

    /**
     * 添加评论
     * @param comment 评论
     */
    void insertComment(Comment comment);


    /**
     * 更新 评论
     * @param comment 评论
     */
    void updateComment(Comment comment);

    /**
     * 返回所有的评论
     *
     * @param criteria 查询条件
     * @return 所有评论
     */
    List<Comment> findAll(HashMap<String, Object> criteria);

    /**
     * 分页查询 评论
     * @param pageIndex 起始页
     * @param pageSize 每页数据量
     * @param criteria 查询条件
     * @return 分页信息
     */
    PageInfo<Comment> pageComment(@Param("pageIndex") Integer pageIndex,
                                  @Param("pageSize") Integer pageSize,
                                  HashMap<String, Object> criteria);

    /**
     * 根据 pid 查询评论
     * @param pid 父级评论
     * @return 评论
     */
    List<Comment> getCommentByPid(Integer pid);


    /**
     * 根据 用户id 查询 被回复的评论
     * @param userId 用户id
     * @return 回复评论
     */
    List<Comment> getReceiveComment(Integer userId);

    /**
     * 分页查询 我被评论
     * @param pageIndex 起始页
     * @param pageSize 每页多少
     * @param userId 用户ID
     * @return 评论
     */
    PageInfo<Comment> getReceiveCommentByPage(Integer pageIndex, Integer pageSize, Integer userId);

    /**
     * 根据 Id 查询 评论
     *
     * @param commentId id
     * @return 评论
     */
    Comment getCommentById(Integer commentId);


    /**
     * 获取最新评论
     *
     * @param userId 用户id
     * @param limit 查询数量
     * @return 评论列表
     */
    List<Comment> listRecentComment(Integer userId, Integer limit);


    /**
     * 根据 文章id 获取评论
     * @param articleId 文章id
     * @return 评论
     */
    List<Comment> getCommentByArticleId(Integer articleId);

    /**
     * 获取评论总数
     * @return 评论总数
     */
    Integer countComment();
}
