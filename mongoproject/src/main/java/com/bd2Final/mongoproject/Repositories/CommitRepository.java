package com.bd2Final.mongoproject.Repositories;

import com.bd2Final.mongoproject.Model.Commit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends MongoRepository<Commit, Long> {
}
