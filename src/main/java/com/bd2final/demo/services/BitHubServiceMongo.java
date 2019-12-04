package com.bd2final.demo.services;

import com.bd2final.demo.model.Commit;
import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.model.UserMongo;
import com.bd2final.demo.repositories.mongo.UserRepositoryMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BitHubServiceMongo implements BitHubService<ObjectId> {

    @Autowired
    @Qualifier("userRepositoryMongo")
    private UserRepositoryMongo userRepositoryMongo;

    @Override
    public User createUser(String name, String email){
        UserMongo user = new UserMongo(name, email);
        userRepositoryMongo.save(user);
        return user;
    }

    @Override
    public Iterable<User> allUsers(){
        ArrayList<User> uList = new ArrayList<User>();
        for(UserMongo user :userRepositoryMongo.findAll()){
            uList.add(user);
        }
        return uList;
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        return null;
    }

    @Override
    public Iterable<Commit> allCommits() {
        return null;
    }
}