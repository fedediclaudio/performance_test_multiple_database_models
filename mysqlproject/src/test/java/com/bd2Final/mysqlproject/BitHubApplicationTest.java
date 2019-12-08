package com.bd2Final.mysqlproject;

import com.bd2Final.mysqlproject.model.Commit;
import com.bd2Final.mysqlproject.model.User;
import com.bd2Final.mysqlproject.services.BitHubServiceMySQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback(true)
public class BitHubApplicationTest {

    @Autowired
    private BitHubServiceMySQL service;

    @BeforeEach
    public void setUp(){
        User user = this.service.createUser("user1","user1@example.com");
        Commit commit = this.service.createCommit("Un commit", "abcd123", user);
    }

    @Test
    public void listUser(){
        Iterable res = this.service.allUsers();
    }

}
