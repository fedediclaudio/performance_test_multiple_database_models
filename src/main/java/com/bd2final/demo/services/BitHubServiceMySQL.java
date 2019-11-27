package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import com.bd2final.demo.repositories.mysql.UserRepositoryMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitHubServiceMySQL implements BitHubService<Long> {

    @Autowired
    private UserRepositoryMySQL userRepositoryMySQL;

    @Override
    public void createUser(String name, String email) {
        User user = new User(name, email);
        userRepositoryMySQL.save(user);
    }

    @Override
    public List<User> allUsers() {
        List<User> users = (List<User>) userRepositoryMySQL.findAll();
        return users;
    }
}
