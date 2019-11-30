package com.bd2final.demo.repositories.cassandra;

import com.bd2final.demo.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepositoryCassandra extends CassandraRepository<User, Long> {
}