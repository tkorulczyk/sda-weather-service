package com.wheaterservice.backend.location;

import com.wheaterservice.backend.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LocationRepositoryImpl implements LocationRepository {

    @Override
    public Location saveLocation(Location location) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(location);

        transaction.commit();
        session.close();
        return location;
    }

    @Override
    public List<Location> readAllEntries() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Location> locations = session.createQuery("FROM Location").getResultList();

        transaction.commit();
        session.close();
        return locations;

    }
}
