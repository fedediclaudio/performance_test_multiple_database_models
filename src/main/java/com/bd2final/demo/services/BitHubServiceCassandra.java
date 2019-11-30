package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import com.bd2final.demo.repositories.cassandra.UserRepositoryCassandra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitHubServiceCassandra implements BitHubService<Long> {

    @Autowired
    private UserRepositoryCassandra userRepositoryCassandra;

    @Override
    public void createUser(String name, String email) {
        User user = new User(name, email);
        userRepositoryCassandra.save(user);
    }

    @Override
    public List<User> allUsers() {
        return (List<User>) userRepositoryCassandra.findAll();
    }
}
