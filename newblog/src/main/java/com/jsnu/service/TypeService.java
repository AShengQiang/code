package com.jsnu.service;

import com.jsnu.pojo.Type;
import com.jsnu.pojo.TypeQuery;

import java.util.List;

public interface TypeService {

    List<Type> listType();
    /*查询所有分类并返回每个分类下的blog*/
    List<TypeQuery> listAllType();
    Type getTypeByName(String name);
    Type getTypeById(Long id);
    void saveType(Type type);
    void deleteType(Long id);
    void updateType(Long id, Type type);
}
