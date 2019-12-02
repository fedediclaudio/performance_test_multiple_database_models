package com.bd2final.demo.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CommitMongo extends Commit<ObjectId> implements PersistentObject<ObjectId> {

    @BsonId
    private ObjectId id;

    public CommitMongo() {}

    public CommitMongo(String message, String hash, UserMongo author) {
        this.message = message;
        this.hash = hash;
        this.author = author;
        author.getCommits().add(this);
    }

    @Override
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
