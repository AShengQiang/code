package com.jsnu.dao;

import com.jsnu.pojo.Blog;
import com.jsnu.pojo.BlogQuery;
import com.jsnu.pojo.BlogTagQuery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface BlogDao {

    @Select("select * from t_blog")
    @Results(id = "blogMap",value = {

            @Result(column = "type_id",property = "type",one=@One(select = "com.jsnu.dao.TypeDao.findTypeById",fetchType = FetchType.EAGER)),
            @Result(column = "user_id",property = "user",one=@One(select = "com.jsnu.dao.UserDao.findUserById",fetchType = FetchType.EAGER))
    })
    /*查询所有博客*/
    List<Blog> findAllBlog();

    @Select({"<script>"+
            "select * from t_blog where 1=1"
            +"<if test='title!=null'> and title like concat('%',#{title},'%')</if>"
            +"<if test='typeId!=null'>and type_id =#{typeId}</if>"
            +"<if test='recommend!=null'>and recommend=#{recommend}</if>"
            +"</script>"
    })
    @Results(id = "blogMap2",value = {

            @Result(column = "type_id",property = "type",one=@One(select = "com.jsnu.dao.TypeDao.findTypeById",fetchType = FetchType.EAGER)),
            @Result(column = "user_id",property = "user",one=@One(select = "com.jsnu.dao.UserDao.findUserById",fetchType = FetchType.EAGER))
    })
    List<Blog> findBlogByBlogQuery(BlogQuery blog);


    @Insert("insert into t_blog(title,content,first_picture,flag,views,description,appreciation,share_statement,commentabled,published," +
            "recommend,create_time,update_time,user_id,type_id,tag_ids) values(#{title},#{content},#{firstPicture},#{flag},#{views},#{description}," +
            "#{appreciation},#{shareStatement},#{commentabled},#{published},#{recommend},#{createTime},#{updateTime},#{user.id},#{typeId},#{tagIds})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void saveBlog(Blog blog);


    @Delete("delete from t_blog where id=#{id}")
    void deleteBlog(Long id);

    @Select("select * from t_blog where id=#{id}")
    @Results(id = "blogMapById",value = {

            @Result(column = "type_id",property = "type",one=@One(select = "com.jsnu.dao.TypeDao.findTypeById",fetchType = FetchType.EAGER)),
            @Result(column = "user_id",property = "user",one=@One(select = "com.jsnu.dao.UserDao.findUserById",fetchType = FetchType.EAGER))
    })
    Blog findBlogById(Long id);

    @Update("update t_blog set title=#{blog.title},content=#{blog.content} ,first_picture=#{blog.firstPicture}," +
            "flag=#{blog.flag},views=#{blog.views},description=#{blog.description},appreciation=#{blog.appreciation}," +
            "share_statement=#{blog.shareStatement},commentabled=#{blog.commentabled},published=#{blog.published}," +
            "recommend=#{blog.recommend},create_time=#{blog.createTime},update_time=#{blog.updateTime},user_id=#{blog.user.id}," +
            "type_id=#{blog.type.id},tag_ids=#{blog.tagIds} where id=#{id}")
    void updateBlog(Long id, Blog blog);

    @Select("select * from t_blog where recommend=true limit 6")
    List<Blog> listRecommendBlog();

    @Select("select * from t_blog where type_id=#{typeId} ")
    @Results(id = "blogMap3",value = {

            @Result(column = "type_id",property = "type",one=@One(select = "com.jsnu.dao.TypeDao.findTypeById",fetchType = FetchType.EAGER)),
            @Result(column = "user_id",property = "user",one=@One(select = "com.jsnu.dao.UserDao.findUserById",fetchType = FetchType.EAGER))
    })
    List<Blog> findBlogByTypeId(Long typeId);

    @Select("select b.id id,b.title,b.views,b.update_time,b.description,b.user_id,b.first_picture firstpicture,t.name tagname" +
            " from t_blog b,t_tag t,t_blog_tags bt where b.id=bt.blog_id and t.id=bt.tag_id and t.id=#{tagId}" +
            " order by b.update_time")
    @Results(id = "blogMap4",value = {

            @Result(column = "user_id",property = "user",one=@One(select = "com.jsnu.dao.UserDao.findUserById",fetchType = FetchType.EAGER))
    })
    List<BlogTagQuery> findBlogByTagId(Long tagId);

    /*查询博客所有年份*/
    @Select("select date_format(update_time,'%Y') year from t_blog group by year order by year desc ")
    List<String> findBlogYear();

    /*根据博客年份查询该年份所有博客*/
    @Select("select * from t_blog where date_format(update_time,'%Y') =#{year}  ")
    List<Blog> findBlogByYear(String year);

    /*查询博客总数量*/
    @Select("select count(id) from t_blog")
    Integer findBlogCount();


    /*查询推荐博客 限制数量3*/
    @Select("select * from t_blog where recommend=true limit 3")
    List<Blog> findRecommendBlogLimit3();




}
