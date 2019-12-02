package com.bd2final.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public abstract class User<IdType> {

    @Column(length = 100, nullable = false)
    @org.springframework.data.cassandra.core.mapping.Column
    protected String name;

    @Column(length = 100, nullable = false)
    @org.springframework.data.cassandra.core.mapping.Column
    protected String email;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "author")
    private List<Commit> commits = new ArrayList<>();

    public String getEmail(){
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
}
