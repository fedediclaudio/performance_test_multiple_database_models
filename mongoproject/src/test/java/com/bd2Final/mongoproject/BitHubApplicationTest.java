package com.bd2Final.mongoproject;

import com.bd2Final.mongoproject.Model.Commit;
import com.bd2Final.mongoproject.Model.User;
import com.bd2Final.mongoproject.Services.BitHubServiceMongo;
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
    private BitHubServiceMongo service;

    @BeforeEach
    public void setUp(){
        User user = this.service.createUser("user1","user1@example .com");
        Commit commit = this.service.createCommit("Un commit", "abcd123", user);
    }

    @Test
    public void listUser(){
        Iterable<User> users = this.service.allUsers();
        Iterable<Commit> commits = this.service.allCommits();
    }

}
