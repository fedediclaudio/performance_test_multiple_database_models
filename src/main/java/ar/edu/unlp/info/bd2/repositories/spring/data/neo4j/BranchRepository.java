package ar.edu.unlp.info.bd2.repositories.spring.data.neo4j;

import ar.edu.unlp.info.bd2.model.Branch;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BranchRepository extends PagingAndSortingRepository<Branch, Long> {
    List<Branch> findByName(String name);
}
