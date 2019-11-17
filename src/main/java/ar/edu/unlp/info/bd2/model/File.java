package ar.edu.unlp.info.bd2.model;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class File extends PersistentObject{

    @Column(length = 200, nullable = false)
    private String filename;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_commit")
    @BsonIgnore
    private Commit commit;

    public File(String name, String content){
        this.filename = name;
        this.content = content;
    }

    public File() {}

    public String getFilename() {
        return filename;
    }

    public void setFilename(String name) {
        this.filename = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

}
