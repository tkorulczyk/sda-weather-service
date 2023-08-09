package com.wheaterservice.application.interfaces.outbound;

import com.wheaterservice.application.utils.HibernateUtils;
import com.wheaterservice.domain.entities.Weather;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public List<Weather> readEntryByLocationAndDate(String location, String dateStr) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        String hql = "FROM Weather W WHERE W.location = :location and W.date = :date";
        List<Weather> weatherMetrics = session.createQuery(hql, Weather.class)
            .setParameter("location", location)
            .setParameter("date", date)
            .getResultList();

        transaction.commit();
        session.close();
        return weatherMetrics;
    }

    public List<Weather> readAllEntries() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Weather> weatherMetrics = session.createQuery("FROM Weather", Weather.class).getResultList();

        transaction.commit();
        session.close();
        return weatherMetrics;

    }

}
