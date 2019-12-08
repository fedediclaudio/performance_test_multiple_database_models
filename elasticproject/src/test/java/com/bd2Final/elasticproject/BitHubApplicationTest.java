package com.bd2Final.elasticproject;

import com.bd2Final.elasticproject.model.Commit;
import com.bd2Final.elasticproject.model.User;
import com.bd2Final.elasticproject.services.BitHubServiceElasticsearch;
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
    private BitHubServiceElasticsearch service;

    @BeforeEach
    public void setUp(){
        service.deleteAll();
        User user = this.service.createUser("user1","user1@example .com");
        Commit commit = this.service.createCommit("Un commit", "abcd123", user);
    }

    @Test
    public void listUser(){
        Iterable res = this.service.allUsers();
    }

}
