package com.bd2final.demo.repositories.mysql;

import com.bd2final.demo.model.UserJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryMySQL extends CrudRepository<UserJPA, Long> {
}
