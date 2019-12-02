package com.bd2final.demo.repositories.cassandra;

import com.bd2final.demo.model.CommitCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommitRepositoryCassandra extends CassandraRepository<CommitCassandra, UUID> {
}
