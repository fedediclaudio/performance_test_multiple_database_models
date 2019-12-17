package com.bd2Final.elasticproject.services;

import com.bd2Final.elasticproject.Config.Generator;
import com.bd2Final.elasticproject.model.Commit;
import com.bd2Final.elasticproject.model.User;
import com.bd2Final.elasticproject.repositories.CommitRepository;
import com.bd2Final.elasticproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public User updateUser(User user) {
        this.userRepository.save(user);
        return user;
    }

    @Override
    public Iterable<User> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void deleteUserById(String id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<Commit> getCommitsByUserEmail(String email) {
        List<Commit> commits = this.userRepository.findByEmail(email).iterator().next().getCommits();
        return commits;
    }

    public void deleteAll() {
        userRepository.deleteAll();
        commitRepository.deleteAll();
    }

    public void generateRegistros(int cant) {
        Generator generator = new Generator();
        generator.generateRegistros(cant, userRepository);
    }
}
