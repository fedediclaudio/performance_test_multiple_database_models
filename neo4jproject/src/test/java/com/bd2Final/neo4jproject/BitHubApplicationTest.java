package com.bd2Final.neo4jproject;

import com.bd2Final.neo4jproject.model.Commit;
import com.bd2Final.neo4jproject.model.User;
import com.bd2Final.neo4jproject.services.BitHubServiceNeo4J;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(true)
public class BitHubApplicationTest{

    @Autowired
    private BitHubServiceNeo4J service;

    @BeforeEach
    public void setUp(){
        User user = this.service.createUser("user1","user1@example .com");
        Commit commit = this.service.createCommit("Un commit", "abcd123", user);
    }

    @Test
    public void listUser(){
        Iterable res = this.service.allUsers();
    }

}
