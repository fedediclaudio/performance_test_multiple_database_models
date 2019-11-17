package ar.edu.unlp.info.bd2.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.*;

@Entity
@Table(name = "review")
public class Review extends PersistentObject{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_branch")
    @BsonIgnore
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @BsonIgnore
    private User author;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "review")
    @BsonIgnore
    private List<FileReview> fileReviews = new ArrayList<FileReview>();

    public Review(Branch branch, User author){
        this.branch = branch;
        this.author = author;
    }

    public Review() {}

    public User getAuthor() {
        return author;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<FileReview> getReviews() {
        return fileReviews;
    }

    public void setFileReviews(List<FileReview> fileReviews) {
        this.fileReviews = fileReviews;
    }

}
