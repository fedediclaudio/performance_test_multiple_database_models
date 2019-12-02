package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.repositories.neo4j.UserRepositoryNeo4J;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BitHubServiceNeo4J implements BitHubService<Long> {

    @Autowired
    @Qualifier("userRepositoryNeo4j")
    private UserRepositoryNeo4J userRepositoryNeo4J;

    @Transactional
    public void createUser(String name, String email){
        UserJPA user = new UserJPA(name, email);
        userRepositoryNeo4J.save(user);
    }

    @Transactional
    public Iterable<User> allUsers(){
        ArrayList<User> uList = new ArrayList<User>();
        for(UserJPA user :userRepositoryNeo4J.findAll()){
            uList.add(user);
        }
        return uList;
    }
}
