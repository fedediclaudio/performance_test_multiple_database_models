package com.bd2Final.mysqlproject.services;

import com.bd2Final.mysqlproject.model.Commit;
import com.bd2Final.mysqlproject.model.User;
import com.bd2Final.mysqlproject.repositories.CommitRepository;
import com.bd2Final.mysqlproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BitHubServiceMySQL implements BitHubService<Long> {

    @Autowired
    private UserRepository userRepositoryMySQL;

    @Autowired
    private CommitRepository commitRepositoryMySQL;

    @Override
    public User createUser(String name, String email) {
        User user = new User(name, email);
        userRepositoryMySQL.save(user);
        return user;
    }

    @Override
    public Iterable<User> allUsers() {
        ArrayList<User> uList = new ArrayList<User>();
        for(User user :userRepositoryMySQL.findAll()){
            uList.add(user);
        }
        return uList;
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        Commit commit = new Commit(message, hash,author);
        commitRepositoryMySQL.save(commit);
        return commit;
    }
}
