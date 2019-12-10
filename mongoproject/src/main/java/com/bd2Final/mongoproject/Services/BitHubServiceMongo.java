package com.bd2Final.mongoproject.Services;

import com.bd2Final.mongoproject.Model.*;
import com.bd2Final.mongoproject.Repositories.CommitRepository;
import com.bd2Final.mongoproject.Repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void deleteUserById(ObjectId id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<Commit> getCommitsByUserEmail(String email) {
        List<Commit> commits = this.userRepository.findByEmail(email).iterator().next().getCommits();
        return commits;
    }
}