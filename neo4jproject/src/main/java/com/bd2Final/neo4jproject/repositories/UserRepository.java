package com.bd2Final.neo4jproject.repositories;

import com.bd2Final.neo4jproject.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<User,Long> {

    List<User> findByEmail(String email);
}
