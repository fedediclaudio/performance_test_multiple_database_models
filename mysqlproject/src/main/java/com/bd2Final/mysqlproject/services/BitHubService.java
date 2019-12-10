package com.bd2Final.mysqlproject.services;

import com.bd2Final.mysqlproject.model.Commit;
import com.bd2Final.mysqlproject.model.User;

import java.util.ArrayList;
import java.util.List;

public interface BitHubService {

    User createUser(String name, String email);

    Iterable<User> allUsers();

    Commit createCommit(String message, String hash, User author);

    Iterable<Commit> allCommits();

    User updateUser(User user);

    Iterable<User> getUserByEmail(String email);

    void deleteUserById(Long id);

    List<Commit> getCommitsByUserEmail(String email);

}
