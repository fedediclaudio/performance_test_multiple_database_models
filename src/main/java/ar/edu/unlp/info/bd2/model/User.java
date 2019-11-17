package ar.edu.unlp.info.bd2.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends PersistentObject{

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "author")
    @BsonIgnore
    private List<Commit> commits = new ArrayList<Commit>();

    public User(String email, String name){
        this.email = email;
        this.name = name;
    }

    public User() {}

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
