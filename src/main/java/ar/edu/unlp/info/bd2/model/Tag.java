package ar.edu.unlp.info.bd2.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag extends PersistentObject {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_commit")
    @BsonIgnore
    private Commit commit;

    @Column(nullable = false, length = 100)
    private String name;

    public Tag(Commit commit, String name) {
        this.commit = commit;
        this.name = name;
    }

    public Tag() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

}
