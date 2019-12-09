package com.bd2Final.elasticproject.services;

import com.bd2Final.elasticproject.model.Commit;
import com.bd2Final.elasticproject.model.User;
import com.bd2Final.elasticproject.repositories.CommitRepository;
import com.bd2Final.elasticproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userRepository.findAll();
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        Commit commit = new Commit(message, hash, author);
        commitRepository.save(commit);
        userRepository.save(author);
        return commit;
    }

    @Override
    public Iterable<Commit> allCommits() {
        return commitRepository.findAll();
    }

    public void deleteAll() {
        userRepository.deleteAll();
        commitRepository.deleteAll();
    }
}
