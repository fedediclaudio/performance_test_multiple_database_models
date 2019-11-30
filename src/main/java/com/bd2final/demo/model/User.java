package com.bd2final.demo.model;

import com.datastax.driver.core.utils.UUIDs;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Document
@Table(name="user")
@org.springframework.data.cassandra.core.mapping.Table("users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    //Cassandra ID
    @org.springframework.data.cassandra.core.mapping.Column
    @PrimaryKey(value = "user_id", forceQuote = true)
    private UUID id_user = UUIDs.timeBased();

//    @BsonId
//    ObjectId objectId;

    public Long getId(){ return id; }

//    public ObjectId getObjectId(){ return objectId; }

    public void setId(Long id){
        this.id = id;
    }

//    public void setObjectId(ObjectId objectId){
//        this.objectId = objectId;
//    }

    //@Column(length = 100, nullable = false)
    @org.springframework.data.cassandra.core.mapping.Column
    private String name;

    //@Column(length = 100, nullable = false)
    @org.springframework.data.cassandra.core.mapping.Column
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
