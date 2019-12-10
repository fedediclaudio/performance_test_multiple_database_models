package com.bd2Final.mysqlproject.repositories;

import com.bd2Final.mysqlproject.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    void deleteById(Long aLong);

    List<User> findByEmail(String email);
}
