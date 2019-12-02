package com.bd2final.demo.model;

import javax.persistence.*;

@Entity
@Table(name="commit")
public class CommitJPA extends Commit<Long> implements PersistentObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public CommitJPA() {}

    public CommitJPA(String message, String hash, UserJPA author){
        this.message = message;
        this.hash = hash;
        this.author = author;
        author.getCommits().add(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
