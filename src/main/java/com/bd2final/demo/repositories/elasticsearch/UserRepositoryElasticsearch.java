package com.bd2final.demo.repositories.elasticsearch;

import com.bd2final.demo.model.User;
import com.bd2final.demo.model.UserElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryElasticsearch extends ElasticsearchRepository<UserElastic, Long> {

}
