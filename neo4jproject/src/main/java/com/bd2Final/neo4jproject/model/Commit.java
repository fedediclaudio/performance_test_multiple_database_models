package com.bd2Final.neo4jproject.model;

import org.neo4j.ogm.annotation.NodeEntity;
import com.bd2Final.neo4jproject.model.User;

import javax.persistence.*;
import java.util.List;

@NodeEntity(label = "commit")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User author;

    @Column(nullable = false)
    private String hash;

    @Column(length = 250)
    private String message;

    public Commit() { }

    public Commit(String message, String hash, User author){
        this.message = message;
        this.hash = hash;
        this.author = author;
        author.getCommits().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
