package com.lunatech.domain.repository.custom;

import com.lunatech.domain.commands.RunwayCommand;
import com.lunatech.domain.model.Runway;
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
public class RunwayRepositoryImpl implements RunwayRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<RunwayCommand> findAllByAirport_Ident(String airport_ident, int page, int limit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        int offset = (page - 1) * limit;

        Query query = session.createQuery(
                "FROM Runway " +
                        "WHERE airport_ident = :airport_ident"
        );

        query.setParameter("airport_ident", airport_ident);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<RunwayCommand> runways = (List<RunwayCommand>) query.list();
        entityManager.clear();
        entityManager.close();

        return runways;
    }

    @Override
    public long countAllByAirport_Ident(String airport_ident) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "select count(*) from Runway " +
                        "WHERE airport_ident=:airport_ident"
        );

        query.setParameter("airport_ident", airport_ident);
        long count = (Long) query.uniqueResult();
        entityManager.clear();
        entityManager.close();
        return count;
    }

    @Override
    public void save(List<RunwayCommand> runwayCommands) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);

        int count = 0;
        for (RunwayCommand runwayCommand : runwayCommands) {
            session.save(new Runway(runwayCommand));

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
