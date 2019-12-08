package com.bd2Final.mongoproject.Services;

import com.bd2Final.mongoproject.Model.*;
import com.bd2Final.mongoproject.Repositories.CommitRepository;
import com.bd2Final.mongoproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BitHubServiceMongo implements BitHubService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    @Override
    public User createUser(String name, String email){
        User user = new User(name, email);
        userRepository.save(user);
        return user;
    }

    @Override
    public Iterable<User> allUsers(){
        ArrayList<User> uList = new ArrayList<User>();
        uList.addAll(userRepository.findAll());
        return uList;
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        Commit commit = new Commit(message, hash, author);
        commitRepository.save(commit);
        userRepository.save(author);
        return commit;
    }
}