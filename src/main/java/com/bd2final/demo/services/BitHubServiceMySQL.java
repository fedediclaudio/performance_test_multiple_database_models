package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.repositories.mysql.UserRepositoryMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BitHubServiceMySQL implements BitHubService<Long> {

    @Autowired
    private UserRepositoryMySQL userRepositoryMySQL;

    @Override
    public void createUser(String name, String email) {
        UserJPA user = new UserJPA(name, email);
        userRepositoryMySQL.save(user);
    }

    @Override
    public Iterable<User> allUsers() {
        ArrayList<User> uList = new ArrayList<User>();
        for(UserJPA user :userRepositoryMySQL.findAll()){
            uList.add(user);
        }
        return uList;
    }

    @Override
    public void createCommit(String message, String hash, User author) {

    }

    @Override
    public Iterable<User> allCommits() {
        return null;
    }
}
