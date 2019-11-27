package com.bd2final.demo.services;

import com.bd2final.demo.model.User;
import com.bd2final.demo.repositories.mongo.UserRepositoryMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BitHubServiceMongo implements BitHubService<ObjectId> {

    @Autowired
    @Qualifier("userRepositoryMongo")
    private UserRepositoryMongo userRepositoryMongo;

    @Transactional
    public void createUser(String name, String email){
        User user = new User(name, email);
        userRepositoryMongo.save(user);
    }

    @Transactional
    public List<User> allUsers(){
        return (List<User>) userRepositoryMongo.findAll();
    }
}
