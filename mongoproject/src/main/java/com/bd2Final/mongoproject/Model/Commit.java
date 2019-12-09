package com.bd2Final.mongoproject.Model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.*;


@Document(collection = "commit")
public class Commit {

    @MongoId
    private ObjectId id;

    @Field
    private String hash;

    @Field
    private String message;

//    @DBRef
//    private User author;

    public Commit() {}

    public Commit(String message, String hash, User author){
        this.message = message;
        this.hash = hash;
//        this.author = author;
        author.getCommits().add(this);
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//    }
}
