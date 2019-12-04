package com.bd2final.demo.repositories.cassandra;

import com.bd2final.demo.model.UserCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCassandra extends CassandraRepository<UserCassandra, Long> {
}
