package com.bd2Final.mongoproject.Repositories;

import com.bd2Final.mongoproject.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findByEmail(String email);

    void deleteById(ObjectId id);
}
