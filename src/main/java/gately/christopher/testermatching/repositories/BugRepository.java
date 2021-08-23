package gately.christopher.testermatching.repositories;

import gately.christopher.testermatching.model.Bug;
import org.springframework.data.repository.CrudRepository;

public interface BugRepository extends CrudRepository<Bug, Integer> {
}
