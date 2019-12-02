package com.bd2final.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "user")
public class CommitElastic extends Commit<String> implements PersistentObject<String> {

    @Id
    private String id;

    public CommitElastic() {}

    public CommitElastic(String message, String hash, UserJPA author){
        this.message = message;
        this.hash = hash;
        this.author = author;
        author.getCommits().add(this);
    }

    public CommitElastic(String id, String message, String hash, UserJPA author){
        this.id = id;
        this.message = message;
        this.hash = hash;
        this.author = author;
        author.getCommits().add(this);
    }


    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
