package com.bd2Final.elasticproject.services;

import com.bd2Final.elasticproject.model.Commit;
import com.bd2Final.elasticproject.model.User;
import com.bd2Final.elasticproject.repositories.CommitRepository;
import com.bd2Final.elasticproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BitHubServiceElasticsearch implements BitHubService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    public User createUser(String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return user;
    }

    public Iterable<User> allUsers() {
        ArrayList<User> uList = new ArrayList<User>();
        for(User user : userRepository.findAll()){
            uList.add(user);
        }
        return uList;
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        Commit commit = new Commit(message, hash);
        commitRepository.save(commit);
        return commit;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
