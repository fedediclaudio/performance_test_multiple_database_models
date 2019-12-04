package com.bd2final.demo.model;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.UUID;

@Table("user")
public class UserCassandra extends User<UUID> implements PersistentObject<UUID>{

    @Column
    @PrimaryKey(value = "id", forceQuote = true)
    private UUID id = UUIDs.timeBased();

    public UserCassandra() {}

    public UserCassandra(String name, String email){
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
