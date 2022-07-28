package com.vtkd.ssm.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vtkd.ssm.blog.entity.Article;
import com.vtkd.ssm.blog.entity.Comment;
import com.vtkd.ssm.blog.mapper.ArticleMapper;
import com.vtkd.ssm.blog.mapper.CommentMapper;
import com.vtkd.ssm.blog.service.CommentService;
import com.vtkd.ssm.blog.util.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 评论 服务层接口实现
 *
 * @author 君上
 * @date 2022-7-27
 */
@Slf4j
@Controller
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Autowired
    private ArticleMapper articleMapper;

    /**
     * @param commentId id
     */
    @Override
    public void deleteCommentById(Integer commentId) {
        try {
            commentMapper.deleteCommentById(commentId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评论失败, commentId:{},  cause:{}", commentId, e);
        }
    }

    /**
     * 添加评论
     * @param comment 评论
     */
    @Override
    public void insertComment(Comment comment) {
        try {
            commentMapper.insertComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("回复评论失败, comment:{},  cause:{}", comment, e);
        }
    }

    /**
     * @param comment 评论
     */
    @Override
    public void updateComment(Comment comment) {
        try {
            commentMapper.updateComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新评论失败, comment:{},  cause:{}", comment, e);
        }
    }

    /**
     * @return
     */
    @Override
    public List<Comment> findAll(HashMap<String,Object> criteria) {

        List<Comment> comments = null;

        try {
            comments = commentMapper.findAll(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询所有评论失败, criteria:{},  cause:{}", criteria, e);
        }
        return comments;
    }

    /**
     * @param pageIndex 起始页
     * @param pageSize  每页数据量
     * @param criteria 条件
     * @return
     */
    @Override
    public PageInfo<Comment> pageComment(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageInfo<Comment> pageInfo = null;

        try {
            PageHelper.startPage(pageIndex, pageSize);
            List<Comment> all = commentMapper.findAll(criteria);
            // 封装用户数据
            if (!all.isEmpty()) {
                for (Comment comment : all) {
                    Article article = articleMapper.getArticleById(comment.getCommentArticleId());
                    comment.setArticle(article);
                }
            }
            pageInfo = new PageInfo<>(all);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分页查询评论失败, pageIndex:{}, pageSize:{}, cause:{}", pageIndex, pageSize, e);
        }
        return pageInfo;
    }

    /**
     * 根据 pid 查询评论
     * @param pid 父级评论
     * @return 评论
     */
    @Override
    public List<Comment> getCommentByPid(Integer pid){
        List<Comment> comments = null;

        try {
            comments = commentMapper.getCommentByPid(pid);
            if (!comments.isEmpty()) {
                for (Comment comment : comments) {
                    Article article = articleMapper.getArticleById(comment.getCommentArticleId());
                    comment.setArticle(article);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据 父级评论ID 查询失败, pid:{}, cause:{}", pid, e);
        }
        return comments;
    }

    @Override
    public List<Comment> getReceiveComment(Integer userId) {
        List<Comment> comments = null;

        try {
            HashMap<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("userId", userId);
            List<Article> articles = articleMapper.findAll(criteria);
            // 查询文章
            if (!articles.isEmpty()) {
                for (Article article : articles) {
                    Integer pid = commentMapper.getCommentByArticleId(article.getArticleId());
                    comments = commentMapper.getCommentByPid(pid);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询 被回复的评论失败, userId:{}, cause:{}", userId, e);
        }

        return comments;
    }

    @Override
    public PageInfo<Comment> getReceiveCommentByPage(Integer pageIndex, Integer pageSize, Integer userId) {
        PageInfo<Comment> pageInfo = null;

        try {
            PageHelper.startPage(pageIndex, pageSize);
            List<Comment> receiveComments = new ArrayList<>();
            HashMap<String, Object> criteria = new HashMap<>();
            criteria.put("userId", userId);
            List<Article> articles = articleMapper.findAll(criteria);
            // 查询文章
            if (!articles.isEmpty()) {
                for (Article article : articles) {
                    receiveComments = commentMapper.getReceiveCommentByArticleId(article.getArticleId());
                    // 获取被评论的评论
//                    receiveComments = commentMapper.getCommentByPid(pid);
                }
            }
            pageInfo = new PageInfo<>(receiveComments);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询 被回复的评论失败, userId:{}, cause:{}", userId, e);
        }

        return pageInfo;
    }

    /**
     * 根据 Id 查询 评论
     *
     * @param commentId id
     * @return 评论
     */
    @Override
    public Comment getCommentById(Integer commentId){
        Comment comment = null;

        try {
            comment = commentMapper.getCommentById(commentId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据id查询评论, commentId:{}, cause:{}", commentId, e);
        }
        return comment;
    }

    /**
     * @param userId 用户id
     * @param limit  查询数量
     * @return
     */
    @Override
    public List<Comment> listRecentComment(Integer userId, Integer limit) {
        List<Comment> comments = null;
        try {
            comments = commentMapper.listRecentComment(userId, limit);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询最新评论失败, userId:{}, limit:{}, cause:{}", userId, limit, e);
        }

        return comments;
    }

    /**
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Comment> getCommentByUserId(Integer userId) {
        return null;
    }
}
