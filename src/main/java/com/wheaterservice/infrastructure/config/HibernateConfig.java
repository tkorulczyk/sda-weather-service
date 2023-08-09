package com.wheaterservice.infrastructure.config;

/**
 * Created on 26.07.2023
 *
 * @author Tomasz Korulczyk
 */

import com.wheaterservice.domain.entities.Weather;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateConfig {
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      try {
        Yaml yaml = new Yaml();
        InputStream inputStream = HibernateConfig.class
            .getClassLoader()
            .getResourceAsStream("application-dev.yaml");

        if (inputStream == null) {
          System.out.println("Nie udało się załadować pliku konfiguracyjnego");
          return null;
        }

        Map<String, Object> yamlProps = yaml.load(inputStream);

        Configuration configuration = new Configuration();

        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, (String) yamlProps.get("driver"));
        settings.put(Environment.URL, (String) yamlProps.get("url"));
        settings.put(Environment.USER, (String) yamlProps.get("user"));
        settings.put(Environment.PASS, (String) yamlProps.get("password"));
        settings.put(Environment.DIALECT, (String) yamlProps.get("dialect"));
        settings.put(Environment.SHOW_SQL, String.valueOf(yamlProps.get("show_sql")));
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, (String) yamlProps.get("hibernate.hbm2ddl.auto"));


        configuration.addAnnotatedClass(Weather.class);

        // Convert Map to Properties
        Properties props = new Properties();
        for (Map.Entry<String, String> entry : settings.entrySet()) {
          if (entry.getValue() != null) {
            props.put(entry.getKey(), entry.getValue());
          } else {
            System.out.println("Wartość dla klucza: " + entry.getKey() + " jest null");
          }
        }

        props.putAll(settings);


        configuration.setProperties(props);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return sessionFactory;
  }
}
