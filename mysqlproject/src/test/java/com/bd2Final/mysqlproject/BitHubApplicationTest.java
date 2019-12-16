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
        System.out.println("Tiempo de creacion de usuario en MySQL: " + effectiveTimeInSecond);
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
        System.out.println("Tiempo de actualizacion de usuario en MySQL: " + effectiveTimeInSecond);
    }

    @Test
    public void getUsersByEmail(){
        long startTime = System.nanoTime();
        Iterable<User> res = this.service.getUserByEmail("kennethmyers@yahoo.com");
        long endTime = System.nanoTime();
        User user2 = res.iterator().next();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de obtencion de un usuario en MySQL: " + effectiveTimeInSecond);
    }

    @Test
    public void getCommitsByUserEmail(){
        String email = "kennethmyers@yahoo.com";
        long startTime = System.nanoTime();
        Iterable<Commit> commits = this.service.getCommitsByUserEmail(email);
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de busqueda de los commits en MySQL: " + effectiveTimeInSecond);
    }

    @Test
    public void deleteUser(){
        long startTime = System.nanoTime();
        this.service.deleteUserById(500349L);
        long endTime = System.nanoTime();
        long effectiveTimeInNano = endTime - startTime;
        double effectiveTimeInSecond = (double) effectiveTimeInNano/ 1_000_000_000;
        System.out.println("Tiempo de eliminacion de usuario vacio en MySQL: " + effectiveTimeInSecond);
    }

}
