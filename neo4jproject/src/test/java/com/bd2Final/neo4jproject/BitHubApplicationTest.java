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
        System.out.println("Tiempo de creacion de usuario en Neo4J: " + effectiveTimeInSecond);
    }

    @Test
    public void updateUserById(){
        User user1 = this.service.getUserByEmail("sancheztueardasgary@hotmail.com").iterator().next();
        user1.setEmail("otromail@hotmail.com");
        long startTime = System.nanoTime();
        this.service.updateUser(user1);
        long endTime = System.nanoTime();
        user1.setEmail("sancheztueardasgary@hotmail.com");
        this.service.updateUser(user1);
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de actualizacion de usuario en Neo4j: " + effectiveTimeInSecond);
    }

    @Test
    public void getUsersByEmail(){
        long startTime = System.nanoTime();
        Iterable<User> res = this.service.getUserByEmail("kennethmyers@yahoo.com");
        long endTime = System.nanoTime();
        User user2 = res.iterator().next();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de obtencion de un usuario en Neo4J: " + effectiveTimeInSecond);
    }

    @Test
    public void getCommitsByUserEmail(){
        String email = "kennethmyers@yahoo.com";
        long startTime = System.nanoTime();
        Iterable<Commit> commits = this.service.getCommitsByUserEmail(email);
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de busqueda de los commits de un usuario en Neo4J: " + effectiveTimeInSecond);
    }

    @Test
    public void deleteUser(){
        long startTime = System.nanoTime();
        this.service.deleteUserById(56010L); // Un valor entre 1 y 1M
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de eliminacion de usuario en Neo4J: " + effectiveTimeInSecond);
    }
}
