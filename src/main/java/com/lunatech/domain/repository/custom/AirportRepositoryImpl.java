package com.lunatech.domain.repository.custom;

import com.lunatech.domain.commands.AirportCommand;
import com.lunatech.domain.model.Airport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * @author Justin Seyvecou
 */
@Component
public class AirportRepositoryImpl implements AirportRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<AirportCommand> findAllByIso_Country(String iso_country, int page, int limit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        int offset = (page - 1) * limit;

        Query query = session.createQuery(
                "FROM Airport " +
                        "WHERE iso_country = :iso_country " +
                        "ORDER BY name "
        );

        query.setParameter("iso_country", iso_country);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<AirportCommand> airports = (List<AirportCommand>) query.list();
        entityManager.clear();
        entityManager.close();

        return airports;
    }

    @Override
    public long countAllByIso_Country(String iso_country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "select count(*) from Airport " +
                        "WHERE iso_country=:iso_country"
        );

        query.setParameter("iso_country", iso_country);
        long count = (Long) query.uniqueResult();
        entityManager.clear();
        entityManager.close();
        return count;
    }

    @Override
    public void save(List<AirportCommand> airportCommands) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);

        int count = 0;
        for (AirportCommand airportCommand : airportCommands) {
            session.save(new Airport(airportCommand));

            // Flush results every 1000 records
            if ((count % 1000) == 0) {
                session.flush();
                session.clear();
            }
        }
        session.flush();
        entityManager.clear();
        entityManager.close();
    }
}
