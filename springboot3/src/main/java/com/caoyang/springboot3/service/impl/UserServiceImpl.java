package com.caoyang.springboot3.service.impl;

import com.caoyang.springboot3.constant.ThreadPoolConstant;
import com.caoyang.springboot3.dao.UserDO;
import com.caoyang.springboot3.repository.UserCache;
import com.caoyang.springboot3.repository.UserRepository;
import com.caoyang.springboot3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author caoyang
 * @create 2023-12-14 10:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCache userCache;

    @Override
    public UserDO getUser(String name) {
        UserDO user = userCache.getUser(name);
        if (Objects.nonNull(user)) {
            return user;
        }
        user = userRepository.getUser(name);
        if (Objects.nonNull(user)) {
            final UserDO userDO = user;
            ThreadPoolConstant.EXECUTOR.submit(() -> userCache.cacheUser(userDO));
        }
        return user;
    }

    @Override
    public List<UserDO> getUserList() {
        return userRepository.getUserList();
    }

    @Override
    public Boolean addUser(UserDO user) {
        Boolean insert = userRepository.insert(user);
        if (insert) {ThreadPoolConstant.EXECUTOR.submit(() -> userCache.cacheUser(user));}
        return insert;
    }

}
