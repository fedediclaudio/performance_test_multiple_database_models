package com.bd2Final.mysqlproject.repositories;

import com.bd2Final.mysqlproject.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
