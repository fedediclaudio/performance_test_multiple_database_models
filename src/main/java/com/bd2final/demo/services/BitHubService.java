package com.bd2final.demo.services;

import com.bd2final.demo.model.Commit;
import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserJPA;
import java.util.List;

public interface BitHubService<IdType> {

    User createUser(String name, String email);

    Iterable<User> allUsers();

    Commit createCommit(String message, String hash, User author);

    Iterable<Commit> allCommits();

}
