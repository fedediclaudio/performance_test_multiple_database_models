package com.bd2Final.neo4jproject.services;


import com.bd2Final.neo4jproject.model.Commit;
import com.bd2Final.neo4jproject.model.User;

public interface BitHubService{

    User createUser(String name, String email);

    Iterable<User> allUsers();

    Commit createCommit(String message, String hash, User author);

}
