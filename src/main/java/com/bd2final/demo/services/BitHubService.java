package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import java.util.List;

public interface BitHubService<IdType> {

    void createUser(String name, String email);
    List<User> allUsers();
}
