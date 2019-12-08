package com.bd2Final.mysqlproject.model;

import javax.persistence.*;

@Entity
@Table(name = "commit")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String hash;

    @Column(length = 250)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User author;

    public Commit() {
    }

    public Commit(String hash, String message, User author) {
        this.hash = hash;
        this.message = message;
        this.author = author;
        author.getCommits().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
