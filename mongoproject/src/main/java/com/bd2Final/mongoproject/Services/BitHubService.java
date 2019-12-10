package com.bd2Final.mongoproject.Services;

import com.bd2Final.mongoproject.Model.Commit;
import com.bd2Final.mongoproject.Model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface BitHubService {

    User createUser(String name, String email);

    Iterable<User> allUsers();

    Commit createCommit(String message, String hash, User author);

    Iterable<Commit> allCommits();

    User updateUser(User user);

    Iterable<User> getUserByEmail(String email);

    void deleteUserById(ObjectId id);

    List<Commit> getCommitsByUserEmail(String email);

}
