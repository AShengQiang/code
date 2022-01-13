package com.jsnu.dao;

import com.jsnu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao {
    @Select("select * from t_user where username=#{username}")
     User findByUsername(String username);

    @Select("select * from t_user where id=#{id}")
    public User findUserById(Long id);
}
