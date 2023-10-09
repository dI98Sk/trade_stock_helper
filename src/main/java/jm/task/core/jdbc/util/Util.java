package jm.task.core.jdbc.util;

import jm.task.core.jdbc.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String dbName = "trade_test";
    public static final String dbTableNameUsers = "users";
    public static final String dbTableNameOil = "oil";
    public static final String dbTableNameGold = "gold";
    public static final String dbTableNameBtc = "btc";

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/" + dbName;
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Fors0897danna";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQL5Dialect";

    public static Connection connectionJDBC = getConnectionJDBC();

    private static Connection getConnectionJDBC() {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error connect with database");
            throw new RuntimeException(e);
        }
    }
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();//Для настройки параметров

                Properties settingHibernate = new Properties();
                settingHibernate.put(Environment.DRIVER, DB_DRIVER);
                settingHibernate.put(Environment.URL, DB_URL);
                settingHibernate.put(Environment.USER, DB_USERNAME);
                settingHibernate.put(Environment.PASS, DB_PASSWORD);
                settingHibernate.put(Environment.DIALECT, DB_DIALECT);

                settingHibernate.put(Environment.SHOW_SQL, "true");
                settingHibernate.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settingHibernate.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settingHibernate);
                configuration.addAnnotatedClass(User.class); // Указываем класс для сопоставления с таблицей

                //Создание объекта для управления службами
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                //Сюда упаковываем все результаты
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Подключились к БД через Hibernate");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка подключения к БД через Hibernate");
            }
        }
        return sessionFactory;
    }
}
