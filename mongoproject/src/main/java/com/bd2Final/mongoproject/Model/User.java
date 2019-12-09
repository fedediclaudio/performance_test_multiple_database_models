package com.bd2Final.mongoproject.Model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "user")
public class User {

    @MongoId
    private ObjectId id;

    @Field
    private String name;

    @Field
    private String email;

//    @DBRef
    private List<Commit> commits = new ArrayList<>();

    public User() {}

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
