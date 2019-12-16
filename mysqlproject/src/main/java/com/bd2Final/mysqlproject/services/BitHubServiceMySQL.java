package com.bd2Final.mysqlproject.services;

import com.bd2Final.mysqlproject.model.Commit;
import com.bd2Final.mysqlproject.model.User;
import com.bd2Final.mysqlproject.repositories.CommitRepository;
import com.bd2Final.mysqlproject.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

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
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Commit> getCommitsByUserEmail(String email) {
        List<User> users = this.userRepository.findByEmail(email);
        User user = users.iterator().next();
        Hibernate.initialize(user.getCommits());
        List<Commit> commits = user.getCommits();
        return commits;
    }
}
