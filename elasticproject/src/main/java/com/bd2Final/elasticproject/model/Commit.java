package com.bd2Final.elasticproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "commit")
public class Commit {

    @Id
    private String id;

    private String hash;

    private String message;

//    Nested = Embebido
//    private User author;

    public Commit() {}

    public Commit(String message, String hash){
        this.message = message;
        this.hash = hash;
    }

    public Commit(String id, String message, String hash){
        this.id = id;
        this.message = message;
        this.hash = hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
