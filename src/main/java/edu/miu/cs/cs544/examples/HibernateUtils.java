package edu.miu.cs.cs544.examples;

import java.util.List;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    
    private static Configuration configuration = new Configuration();
    
    @SuppressWarnings({ "rawtypes" })
    public static SessionFactory getSessionFactory(List<Class> entityClasses) {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();               
				settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
				settings.put(Environment.URL, "jdbc:sqlserver://localhost:51688");
				//settings.put(Environment.URL, "jdbc:sqlserver://cs544.cs.miu.edu:1433"); //10.10.10.15
				//settings.put(Environment.USER, "612422");
				//settings.put(Environment.PASS, "103272");
				settings.put(Environment.USER, "user12");
				settings.put(Environment.PASS, "1234");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

               // settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(settings);
                
                entityClasses.forEach(entityClass -> configuration.addAnnotatedClass(entityClass));

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
