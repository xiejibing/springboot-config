package com.xie.transaction.service;

import com.alibaba.fastjson.JSON;
import com.xie.transaction.dao.task.model.User;
import com.xie.transaction.dao.task1.model.User1;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void test1(){
        List<User> userList = userService.getUser();
        userList.forEach(user -> {
            System.out.println(JSON.toJSONString(user));
        });
    }

    @Test
    public void testTx(){
        User u1 = new User();
        u1.setName("liusheng3");
        User u2 = new User();
        u2.setName("liusheng4");
        userService.saveUsers(u1,u2);
    }

    @Test
    public void testTx1(){
        User1 u1 = new User1();
        u1.setName("liusheng3");
        User1 u2 = new User1();
        u2.setName("liusheng4");
        userService.saveUsers1(u1,u2);
    }


}