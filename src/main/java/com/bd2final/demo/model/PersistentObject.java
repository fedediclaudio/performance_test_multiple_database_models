package com.bd2final.demo.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import javax.persistence.*;

@MappedSuperclass
public abstract class PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @BsonId
    ObjectId objectId;

    public Long getId(){ return id; }

    public ObjectId getObjectId(){ return objectId; }

    public void setId(Long id){
        this.id = id;
    }

    public void setObjectId(ObjectId objectId){
        this.objectId = objectId;
    }
}
