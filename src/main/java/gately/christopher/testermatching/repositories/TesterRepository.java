package gately.christopher.testermatching.repositories;

import gately.christopher.testermatching.model.Tester;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TesterRepository extends CrudRepository<Tester, Integer> {
    @Query("select t from Tester t left join fetch t.devices where t.id = ?1")
    Tester getTester(Integer id);

    @Query("select distinct t.country from Tester t order by 1 asc")
    List<String> getAllCountries();
}
