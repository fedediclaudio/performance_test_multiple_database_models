package com.bd2final.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Column;
import javax.persistence.Id;

@Document(indexName = "user")
public class UserElastic extends User<String> implements PersistentObject<String>{

    @Id
    private String id;

    public UserElastic(){}

    public UserElastic(String username, String email){
        this.email = email;
        this.name = username;
    }

    public UserElastic(String id, String username, String email){
        this.id = id;
        this.email = email;
        this.name = username;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
