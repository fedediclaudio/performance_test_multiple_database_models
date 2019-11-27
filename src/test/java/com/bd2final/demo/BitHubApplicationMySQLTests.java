package com.bd2final.demo;

import com.bd2final.demo.model.User;
import com.bd2final.demo.services.BitHubServiceMongo;
import com.bd2final.demo.services.BitHubServiceMySQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BitHubApplicationMySQLTests {

    @Autowired
    private BitHubServiceMySQL service;

    @BeforeEach
    public void setUp(){
        service.createUser("a","a");
        service.createUser("b","b");
    }

    @Test
    public void createUser(){
        List<User> res = service.allUsers();
    }
}
