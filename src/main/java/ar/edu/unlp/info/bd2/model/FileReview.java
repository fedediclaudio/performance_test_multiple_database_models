package ar.edu.unlp.info.bd2.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "file_review")
public class FileReview extends PersistentObject{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_review")
    @BsonIgnore
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_file")
    private File file;

    @Column(length = 250, nullable = false)
    private String comment;

    @Column(nullable = false)
    private int lineNumber;

    public FileReview(Review review, File file, int lineNumber, String comment) {
        this.review = review;
        review.getReviews().add(this);
        this.file = file;
        this.lineNumber = lineNumber;
        this.comment = comment;
    }


    public FileReview() {}

    public Review getReview() {
        return review;
    }

    public File getReviewedFile() {
        return file;
    }

    public File getFile() { return file; }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
