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
    }

    @Test
    public void listUser(){
        Iterable<User> users = this.service.allUsers();
        Iterable<Commit> commits = this.service.allCommits();
    }
    

    @Test
    public void createUser(){
        long startTime = System.nanoTime();
        this.service.createUser("user1","user1@example.com");
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de creacion de usuario vacio en Elastic: " + effectiveTimeInSecond);
    }

    @Test
    public void updateUserById(){
        User user1 = this.service.createUser("user1","user1@example.com");
        user1.setEmail("otromail@gmail.com");
        long startTime = System.nanoTime();
        this.service.updateUser(user1);
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de actualizacion de usuario vacio en Elastic: " + effectiveTimeInSecond);
    }

    @Test
    public void deleteUser(){
        User user1 = this.service.createUser("user1","user1@example.com");
        long startTime = System.nanoTime();
        this.service.deleteUserById(user1.getId());
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de eliminacion de usuario vacio en Elastic: " + effectiveTimeInSecond);
    }

    @Test
    public void getUsersByEmail(){
        User user1 = this.service.createUser("user1","user1@example.com");
        String email = user1.getEmail();
        long startTime = System.nanoTime();
        Iterable<User> res = this.service.getUserByEmail(email);
        long endTime = System.nanoTime();
        User user2 = res.iterator().next();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de busqueda de usuario vacio en Elastic: " + effectiveTimeInSecond);
    }

    @Test
    public void getCommitsByUserEmail(){
        User user1 = this.service.createUser("user1","user1@example.com");
        Commit commit1 = this.service.createCommit("Primer commit", "1234567", user1);
        Commit commit2 = this.service.createCommit("Segundo commit", "1234568", user1);
        String email = user1.getEmail();
        long startTime = System.nanoTime();
        Iterable<Commit> commits = this.service.getCommitsByUserEmail(email);
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de busqueda de usuario vacio en MySQL: " + effectiveTimeInSecond);
    }

}
