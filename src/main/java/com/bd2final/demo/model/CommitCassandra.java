package com.bd2final.demo.model;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("commit")
public class CommitCassandra extends Commit<UUID> implements PersistentObject<UUID> {

    @Column
    @PrimaryKey(value = "id", forceQuote = true)
    private UUID id = UUIDs.timeBased();

    public CommitCassandra() {}

    public CommitCassandra(String message, String hash, UserJPA author) {
        this.message = message;
        this.hash = hash;
        this.author = author;
        author.getCommits().add(this);
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
