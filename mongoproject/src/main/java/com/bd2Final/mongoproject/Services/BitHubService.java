package com.bd2Final.mongoproject.Services;

import com.bd2Final.mongoproject.Model.Commit;
import com.bd2Final.mongoproject.Model.User;

public interface BitHubService {

    User createUser(String name, String email);

    Iterable<User> allUsers();

    Commit createCommit(String message, String hash, User author);

    Iterable<Commit> allCommits();

}
