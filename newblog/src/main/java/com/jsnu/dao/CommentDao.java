package com.jsnu.dao;

import com.jsnu.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface CommentDao {

    @Insert("insert into t_comment(nickname,email,content,parent_comment_id,create_time,blog_id,avatar,admin_comment,child_comment) " +
            "values(#{nickname},#{email},#{content},#{parentCommentId},#{createTime},#{blogId},#{avatar},#{adminComment},#{childComment})")
    void saveComment(Comment comment);

    @Select("select * from t_comment where blog_id=#{blogId} and parent_comment_id=-1 order by create_time DESC")
    List<Comment> findCommentById(Long blogId);

    @Select("select * from t_comment where blog_id=#{blogId} and parent_comment_id!=-1 and child_comment=true order by create_time DESC")
    List<Comment> findCommentByIdReverse(Long blogId);

    @Select("select * from t_comment where parent_comment_id=#{id} order by create_time DESC ")
    List<Comment> findReplyComments(Long id);

    @Select("select * from t_comment where id=#{parentId}")
    Comment findParentComment(Long parentId);

    @Update("update t_comment set nickname=#{nickname},email=#{email},content=#{content},parent_comment_id=#{parentCommentId}," +
            "create_time=#{createTime},blog_id=#{blogId},avatar=#{avatar},admin_comment=#{adminComment},child_comment=#{childComment} " +
            "where id=#{id}")
    void updateComment(Comment comment);
}
