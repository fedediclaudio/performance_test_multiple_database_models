package com.bd2Final.elasticproject.repositories;

import com.bd2Final.elasticproject.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

}
