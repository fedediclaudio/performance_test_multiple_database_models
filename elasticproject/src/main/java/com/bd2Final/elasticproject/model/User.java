package com.bd2Final.elasticproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "user")
public class User {

    @Id
    private String _id = null;

    private String name;

    private String email;

    // NESTED: Objetos embebidos ***** Funciona bien
//    @Field(type = FieldType.Nested)
    private List<Commit> commits = new ArrayList<>();

    public User() {}

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public User(String id, String name, String email){
        this._id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, ArrayList<Commit> commits) {
//        this._id = id;
        this.name = name;
        this.email = email;
        this.commits = commits;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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
