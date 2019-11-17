package ar.edu.unlp.info.bd2.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commit")
public class Commit extends PersistentObject{

    @Column(nullable = false)
    private String hash;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @BsonIgnore
    private User author;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "commit")
    private List<File> files = new ArrayList<File>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_branch")
    @BsonIgnore
    private Branch branch;

    @Column(length = 250)
    private String message;


    public Commit(String message, String hash, User author, List<File> files, Branch branch){
        this.message = message;
        this.hash = hash;
        this.author = author;
        this.files = files;
        this.branch = branch;
        author.getCommits().add(this);
        for (File f: files) {
            f.setCommit(this);
        }
        branch.getCommits().add(this);

    }

    public Commit() {}

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

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
