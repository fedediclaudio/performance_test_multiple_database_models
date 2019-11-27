package com.bd2final.demo.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity
@Document
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @BsonId
    ObjectId objectId;

//    public Long getId(){ return id; }

    public ObjectId getObjectId(){ return objectId; }

//    public void setId(Long id){
//        this.id = id;
//    }

    public void setObjectId(ObjectId objectId){
        this.objectId = objectId;
    }

    //@Column(length = 100, nullable = false)
    private String name;

    //@Column(length = 100, nullable = false)
    private String email;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public User() {}

    public String getEmail(){
        return email;
    }


    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

}
