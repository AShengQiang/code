package com.jsnu.service.Impl;

import com.jsnu.dao.TypeDao;
import com.jsnu.pojo.Type;
import com.jsnu.pojo.TypeQuery;
import com.jsnu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
    @Override
    public List<Type> listType() {
        return typeDao.findAllTypes();
    }

    @Override
    public List<TypeQuery> listAllType() {
        return typeDao.findAllTypesAndCount();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findTypeByName(name);
    }

    @Override
    public void saveType(Type type) {
        typeDao.saveType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Transactional
    @Override
    public void updateType(Long id, Type type) {
      typeDao.updateType(id,type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeDao.findTypeById(id);
    }
}
