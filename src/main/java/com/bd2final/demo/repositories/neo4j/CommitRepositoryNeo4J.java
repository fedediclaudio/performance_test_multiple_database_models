package com.bd2final.demo.repositories.neo4j;

import com.bd2final.demo.model.CommitJPA;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepositoryNeo4J extends Neo4jRepository<CommitJPA, Long> {
}
