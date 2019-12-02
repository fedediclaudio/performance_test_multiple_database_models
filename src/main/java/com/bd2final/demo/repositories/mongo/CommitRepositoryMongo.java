package com.bd2final.demo.repositories.mongo;

import com.bd2final.demo.model.CommitMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepositoryMongo extends MongoRepository<CommitMongo, ObjectId> {
}
