package com.bd2final.demo.repositories.neo4j;

import com.bd2final.demo.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryNeo4j")
public interface UserRepositoryNeo4J extends Neo4jRepository<User,Long> {
}
