package com.bd2Final.mongoproject.Repositories;

import com.bd2Final.mongoproject.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
}
