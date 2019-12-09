package com.bd2Final.neo4jproject.services;

import com.bd2Final.neo4jproject.model.Commit;
import com.bd2Final.neo4jproject.model.User;
import com.bd2Final.neo4jproject.repositories.CommitRepository;
import com.bd2Final.neo4jproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BitHubServiceNeo4J implements BitHubService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommitRepository commitRepository;

    public User createUser(String name, String email){
        User user = new User(name, email);
        userRepository.save(user);
        return user;
    }

    public Iterable<User> allUsers(){
        return userRepository.findAll();
    }

    public Commit createCommit(String message, String hash, User author) {
        Commit commit = new Commit(message,hash, author);
        commitRepository.save(commit);
        return commit;
    }

    @Override
    public Iterable<Commit> allCommits() {
        return commitRepository.findAll();
    }
}
