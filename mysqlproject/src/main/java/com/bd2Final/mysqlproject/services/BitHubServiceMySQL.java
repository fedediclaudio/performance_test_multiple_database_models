package com.bd2Final.mysqlproject.services;

import com.bd2Final.mysqlproject.model.Commit;
import com.bd2Final.mysqlproject.model.User;
import com.bd2Final.mysqlproject.repositories.CommitRepository;
import com.bd2Final.mysqlproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BitHubServiceMySQL implements BitHubService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    @Override
    public User createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return user;
    }

    @Override
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        Commit commit = new Commit(message, hash,author);
        commitRepository.save(commit);
        return commit;
    }

    @Override
    public Iterable<Commit> allCommits() {
        return commitRepository.findAll();
    }
}
