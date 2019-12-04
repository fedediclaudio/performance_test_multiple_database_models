package com.bd2final.demo.services;

import com.bd2final.demo.model.Commit;
import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserElastic;
import com.bd2final.demo.repositories.elasticsearch.UserRepositoryElasticsearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BitHubServiceElasticsearch implements BitHubService<String> {

    @Autowired
    private UserRepositoryElasticsearch userRepositoryElasticsearch;

    public User createUser(String name, String email) {
        UserElastic user = new UserElastic(name, email);
        userRepositoryElasticsearch.save(user);
        return user;
    }

    public Iterable<User> allUsers() {
        ArrayList<User> uList = new ArrayList<User>();
        for(UserElastic user :userRepositoryElasticsearch.findAll()){
            uList.add(user);
        }
        return uList;
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        return null;
    }

    @Override
    public Iterable<Commit> allCommits() {
        return null;
    }

    public void deleteAll() {
        userRepositoryElasticsearch.deleteAll();
    }
}
