package com.bd2final.demo.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserMongo extends User<ObjectId> implements PersistentObject<ObjectId>{
    @BsonId
    private ObjectId id;

    public UserMongo() {}

    public UserMongo(String name, String email){
        this.name = name;
        this.email = email;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }
}
