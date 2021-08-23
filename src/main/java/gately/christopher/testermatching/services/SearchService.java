package gately.christopher.testermatching.services;

import gately.christopher.testermatching.model.SearchResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<SearchResult> searchAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<SearchResult> query = session.createQuery("select new gately.christopher.testermatching.model.SearchResult(t.id, t.firstName, t.lastName, t.country, count(1) as bugCount) " +
                "from Tester t join t.devices d join t.bugs b on b.device = d group by t order by bugCount desc");

        return query.getResultList();
    }

    public List<SearchResult> searchDevices(List<Integer> deviceIds) {
        Session session = entityManager.unwrap(Session.class);
        Query<SearchResult> query = session.createQuery("select new gately.christopher.testermatching.model.SearchResult(t.id, t.firstName, t.lastName, t.country, count(1)) " +
                "from Tester t join t.devices d join t.bugs b on b.device = d where d.id in (:devices) group by t order by 5 desc");

        query.setParameterList("devices", deviceIds);
        return query.getResultList();
    }

    public List<SearchResult> searchCountries(List<String> countries) {
        Session session = entityManager.unwrap(Session.class);
        Query<SearchResult> query = session.createQuery("select new gately.christopher.testermatching.model.SearchResult(t.id, t.firstName, t.lastName, t.country, count(1)) " +
                "from Tester t join t.devices d join t.bugs b on b.device = d where t.country in (:countries) group by t order by 5 desc");

        query.setParameterList("countries", countries);
        return query.getResultList();
    }

    public List<SearchResult> searchDevicesAndCountries(List<String> countries, List<Integer> deviceIds) {
        Session session = entityManager.unwrap(Session.class);
        Query<SearchResult> query = session.createQuery("select new gately.christopher.testermatching.model.SearchResult(t.id, t.firstName, t.lastName, t.country, count(1)) " +
                "from Tester t join t.devices d join t.bugs b on b.device = d where t.country in (:countries) and d.id in (:devices) group by t order by 5 desc");

        query.setParameterList("countries", countries);
        query.setParameterList("devices", deviceIds);
        return query.getResultList();
    }
}
