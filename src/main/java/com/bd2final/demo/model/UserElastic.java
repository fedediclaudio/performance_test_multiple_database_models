package com.bd2final.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Column;
import javax.persistence.Id;

@Document(indexName = "user")
public class UserElastic {
    @Id
    private String id;
    @Field
    private String email;
    @Field
    private String username;

    public UserElastic(String username, String email){
        this.email = email;
        this.username = username;
    }

    public UserElastic(String id, String username, String email){
        this.id = id;
        this.email = email;
        this.username = username;
    }



    public UserElastic(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
