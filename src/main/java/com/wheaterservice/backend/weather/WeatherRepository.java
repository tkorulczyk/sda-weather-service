package com.wheaterservice.backend.weather;

import com.wheaterservice.backend.HibernateUtils;
import com.wheaterservice.backend.location.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class WeatherRepository {

    public Weather saveWeatherMetrics(Weather weather) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(weather);

        transaction.commit();
        session.close();
        return weather;
    }

    public List<Location> readAllEntries() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Location> weatherMetrics = session.createQuery("FROM Weather").getResultList();

        transaction.commit();
        session.close();
        return weatherMetrics;

    }
}
