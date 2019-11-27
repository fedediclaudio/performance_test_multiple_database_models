package com.bd2final.demo.repositories.mongo;

import com.bd2final.demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryMongo")
public interface UserRepositoryMongo extends MongoRepository<User, ObjectId> {
}
