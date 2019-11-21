package ar.edu.unlp.info.bd2.repositories.spring.data.sql;

import ar.edu.unlp.info.bd2.model.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Long> {

    List<Branch> findByName(String name);

}
