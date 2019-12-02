package com.bd2final.demo.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public abstract class Commit<IdType> {

    @Column(nullable = false)
    @org.springframework.data.cassandra.core.mapping.Column
    protected String hash;

    @Column(length = 250)
    @org.springframework.data.cassandra.core.mapping.Column
    protected String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    protected User author;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
