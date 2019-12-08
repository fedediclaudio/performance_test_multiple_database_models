package com.bd2Final.mysqlproject.services;

import com.bd2Final.mysqlproject.model.Commit;
import com.bd2Final.mysqlproject.model.User;

public interface BitHubService<IdType> {

    User createUser(String name, String email);

    Iterable<User> allUsers();

    Commit createCommit(String message, String hash, User author);

}
