package com.bd2final.demo.model;

import javax.persistence.Column;

public abstract class User<IdType> {

    @Column(length = 100, nullable = false)
    @org.springframework.data.cassandra.core.mapping.Column
    protected String name;

    @Column(length = 100, nullable = false)
    @org.springframework.data.cassandra.core.mapping.Column
    protected String email;

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
}
