package com.bd2final.demo.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class UserJPA extends User<Long> implements PersistentObject<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public UserJPA() {}

    public UserJPA(String name, String email){
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
