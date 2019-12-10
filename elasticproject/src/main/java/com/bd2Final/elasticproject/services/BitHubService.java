package com.bd2Final.elasticproject.services;

import com.bd2Final.elasticproject.model.Commit;
import com.bd2Final.elasticproject.model.User;

import java.util.List;

public interface BitHubService {

    User createUser(String name, String email);

    Iterable<User> allUsers();

    Commit createCommit(String message, String hash, User author);

    Iterable<Commit> allCommits();

    User updateUser(User user);

    Iterable<User> getUserByEmail(String email);

    void deleteUserById(String id);

    List<Commit> getCommitsByUserEmail(String email);

}
