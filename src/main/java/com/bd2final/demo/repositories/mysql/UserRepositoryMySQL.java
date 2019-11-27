package com.bd2final.demo.repositories.mysql;

import com.bd2final.demo.model.User;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryMySQL extends CrudRepository<User, Long> {
}
