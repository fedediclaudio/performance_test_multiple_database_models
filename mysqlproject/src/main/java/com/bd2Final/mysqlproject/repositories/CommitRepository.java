package com.bd2Final.mysqlproject.repositories;

import com.bd2Final.mysqlproject.model.Commit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends CrudRepository<Commit, Long> {
}
