package com.bd2final.demo.services;

import com.bd2final.demo.model.UserElastic;
import com.bd2final.demo.repositories.elasticsearch.UserRepositoryElasticsearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitHubServiceElasticsearch {

    @Autowired
    private UserRepositoryElasticsearch userRepositoryElasticsearch;

    public void createUser(String name, String email) {
        UserElastic user = new UserElastic(name, email);
        userRepositoryElasticsearch.save(user);
    }

    public Iterable<UserElastic> allUsers() {
        return userRepositoryElasticsearch.findAll();
    }

    public void deleteAll() {
        userRepositoryElasticsearch.deleteAll();
    }
}
