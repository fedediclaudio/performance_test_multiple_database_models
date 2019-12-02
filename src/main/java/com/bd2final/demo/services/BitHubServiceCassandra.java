package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserCassandra;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.repositories.cassandra.UserRepositoryCassandra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BitHubServiceCassandra implements BitHubService<UUID> {

    @Autowired
    private UserRepositoryCassandra userRepositoryCassandra;

    @Override
    public void createUser(String name, String email) {
        UserCassandra user = new UserCassandra(name, email);
        userRepositoryCassandra.save(user);
    }

    @Override
    public Iterable<User> allUsers() {
        ArrayList<User> uList = new ArrayList<User>();
        for(UserCassandra user :userRepositoryCassandra.findAll()){
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
