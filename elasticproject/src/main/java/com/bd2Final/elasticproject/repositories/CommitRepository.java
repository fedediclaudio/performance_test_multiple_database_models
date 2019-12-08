package com.bd2Final.elasticproject.repositories;

import com.bd2Final.elasticproject.model.Commit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends ElasticsearchRepository<Commit, String> {
}
