package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserJPA;
import java.util.List;

public interface BitHubService<IdType> {

    void createUser(String name, String email);

    Iterable<User> allUsers();

    void createCommit(String message, String hash, User author);

    Iterable<User> allCommits();

}
