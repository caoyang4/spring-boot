package com.caoyang.springboot3.repository.impl;

import com.caoyang.springboot3.dao.UserDO;
import com.caoyang.springboot3.mapper.UserMapper;
import com.caoyang.springboot3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

/**
 * @author caoyang
 * @create 2023-12-14 13:18
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO getUser(String name) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName", name);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public List<UserDO> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public Boolean insert(UserDO userDO) {
        if (Objects.isNull(userDO)) {
            return false;
        }
        return userMapper.insertSelective(userDO) == 1;
    }
}
