package com.bd2Final.neo4jproject.repositories;

import com.bd2Final.neo4jproject.model.Commit;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends Neo4jRepository<Commit, Long> {
}
