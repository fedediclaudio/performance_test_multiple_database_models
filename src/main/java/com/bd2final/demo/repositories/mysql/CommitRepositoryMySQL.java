package com.bd2final.demo.repositories.mysql;

import com.bd2final.demo.model.CommitJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepositoryMySQL extends CrudRepository<CommitJPA, Long> {
}
