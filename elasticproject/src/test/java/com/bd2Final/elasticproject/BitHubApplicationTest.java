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

import java.util.Random;

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
    public void generarRegistros(){
        long startTime = System.nanoTime();
        int cantidad = 1;
        this.service.generateRegistros(cantidad);
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de toda la insercion: " + effectiveTimeInSecond);
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
        User user1 = this.service.getUserByEmail("fedemozzon@gmail.com").iterator().next();
        user1.setEmail("otromail@gmail.com");
        long startTime = System.nanoTime();
        this.service.updateUser(user1);
        long endTime = System.nanoTime();
        user1.setEmail("fedemozzon@gmail.com");
        this.service.updateUser(user1);
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de actualizacion de usuario vacio en Elastic: " + effectiveTimeInSecond);
    }

    @Test
    public void getUsersByEmail(){
        String email = "fedemozzon@gmail.com";
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
        String email = "fede@gmail.com";
        long startTime = System.nanoTime();
        Iterable<Commit> commits = this.service.getCommitsByUserEmail(email);
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de busqueda de usuario vacio en MySQL: " + effectiveTimeInSecond);
    }

    @Test
    public void deleteUser(){
        User user1 = this.service.getUserByEmail("fedemozzon@gmail.com").iterator().next();
        long startTime = System.nanoTime();
        this.service.deleteUserById(user1.getId());
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de eliminacion de usuario vacio en Elastic: " + effectiveTimeInSecond);
    }

}
