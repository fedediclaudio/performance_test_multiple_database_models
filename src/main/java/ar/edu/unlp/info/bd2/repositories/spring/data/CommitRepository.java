package ar.edu.unlp.info.bd2.repositories.spring.data;

import ar.edu.unlp.info.bd2.model.Commit;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CommitRepository extends CrudRepository<Commit, Long> {

    List<Commit> findByHash(String hash);
}
