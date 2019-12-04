package com.bd2final.demo.services;

import com.bd2final.demo.model.Commit;
import com.bd2final.demo.model.CommitJPA;
import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserJPA;
import com.bd2final.demo.repositories.neo4j.CommitRepositoryNeo4J;
import com.bd2final.demo.repositories.neo4j.UserRepositoryNeo4J;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BitHubServiceNeo4J implements BitHubService<Long> {

    @Autowired
    @Qualifier("userRepositoryNeo4j")
    private UserRepositoryNeo4J userRepositoryNeo4J;

    @Autowired
    private CommitRepositoryNeo4J commitRepositoryNeo4J;

    public User createUser(String name, String email){
        UserJPA user = new UserJPA(name, email);
        userRepositoryNeo4J.save(user);
        return user;
    }

    public Iterable<User> allUsers(){
        ArrayList<User> uList = new ArrayList<User>();
        for(UserJPA user :userRepositoryNeo4J.findAll()){
            uList.add(user);
        }
        return uList;
    }

    @Override
    public Commit createCommit(String message, String hash, User author) {
        CommitJPA commit = new CommitJPA(message, hash, (UserJPA) author);
        commitRepositoryNeo4J.save(commit);
        return commit;
    }

    @Override
    public Iterable<Commit> allCommits() {
        ArrayList<Commit> cList = new ArrayList<>();
        for(CommitJPA commit : commitRepositoryNeo4J.findAll()){
            cList.add(commit);
        }
        return cList;
    }
}
