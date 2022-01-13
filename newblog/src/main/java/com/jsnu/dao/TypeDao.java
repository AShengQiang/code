package com.jsnu.dao;

import com.jsnu.pojo.Type;
import com.jsnu.pojo.TypeQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeDao {

    @Select("select * from t_type ")
    List<Type> findAllTypes();

    @Select("select * from t_type where name=#{name}")
    Type findTypeByName(String name);

    @Insert("insert into t_type(name) values(#{name})")
    void saveType(Type type);

    @Delete("delete from t_type where id=#{id} ")
    void deleteType(Long id);

    @Select("select * from t_type where id=#{id}")
    Type findTypeById(Long id);

    @Update("update  t_type set name=#{type.name} where id=#{id}")
    void updateType(Long id,Type type);

    @Select("select t.id typeId,t.name typeName,count(b.id) count from t_blog b,t_type t where t.id=b.type_id " +
            "Group By t.id,t.name order by count(b.id) desc  ")
    List<TypeQuery> findAllTypesAndCount();
}
