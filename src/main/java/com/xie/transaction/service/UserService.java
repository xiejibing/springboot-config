package com.xie.transaction.service;


import com.xie.transaction.dao.task.mappers.UserMapper;
import com.xie.transaction.dao.task.model.User;
import com.xie.transaction.dao.task.model.UserExample;
import com.xie.transaction.dao.task1.mappers.User1Mapper;
import com.xie.transaction.dao.task1.model.User1;
import com.xie.transaction.dao.task1.model.User1Example;
import com.xie.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private UserRepository userRepository;

    public List<User> getUser(){
        return userMapper.selectByExample(new UserExample());
    }

    public List<User1> getUser1(){
        return user1Mapper.selectByExample(new User1Example());
    }

    @Transactional(rollbackFor = Exception.class, value = "taskTransactionManager")
    public void saveUsers(User user1, User user2){
        userRepository.insert(user1);
        userRepository.insert(user2);
        //int i = 1/0;
        System.out.println("插入成功");
    }

    @Transactional(rollbackFor = Exception.class, value = "task1TransactionManager")
    public void saveUsers1(User1 user1, User1 user2){
        user1Mapper.insertSelective(user1);
        user1Mapper.insertSelective(user2);
        int i = 1/0;
        System.out.println("插入成功");
    }

}
