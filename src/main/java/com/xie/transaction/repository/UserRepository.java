package com.xie.transaction.repository;

import com.xie.transaction.dao.task.mappers.UserMapper;
import com.xie.transaction.dao.task.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public int insert(User user){
        try{
            int ret = userMapper.insert(user);
            int i = 1/0;
            if(ret <= 0){
                System.out.println("插入失败");
            }
            return ret;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
