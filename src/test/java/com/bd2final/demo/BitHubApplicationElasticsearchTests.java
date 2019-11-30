package com.bd2final.demo;

import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserElastic;
import com.bd2final.demo.services.BitHubServiceElasticsearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BitHubApplicationElasticsearchTests {

    @Autowired
    private BitHubServiceElasticsearch service;

    @BeforeEach
    public void setUp(){
        service.deleteAll();
        service.createUser("a","a");
        service.createUser("b","b");
    }

    @Test
    public void createUser(){
        Iterable<UserElastic> res = service.allUsers();
    }
}
