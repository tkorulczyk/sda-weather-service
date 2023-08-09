package com.wheaterservice.application.utils;

import com.wheaterservice.infrastructure.config.HibernateConfig;
import org.hibernate.SessionFactory;

import java.util.logging.Level;

public class HibernateUtils {

    private final static SessionFactory sessionFactory;

    static {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        sessionFactory = HibernateConfig.getSessionFactory(); // Wykorzystaj twoją nową klasę do pobrania SessionFactory
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
