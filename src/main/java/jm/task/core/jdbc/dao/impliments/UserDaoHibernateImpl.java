package jm.task.core.jdbc.dao.impliments;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.entity.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "create table if not exists " + Util.dbName + "."
                    + Util.dbTableNameUsers +
                    "(id bigint auto_increment," +
                    " name text, last_name text, age integer, " +
                    "constraint table1_pk primary key (id))";
            session.createSQLQuery(sqlQuery).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null)
                sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось создать таблицу");
        }
    }

    @Override
    public void dropUsersTable() {
        //
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableNameUsers;;
            session.createSQLQuery(sqlQuery)
                    .addEntity(User.class)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            //
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось удалить таблицу");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = sessionFactory.openSession();) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось добавить пользователей");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось удалить пользователя");

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            users = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось прочитать всех пользователей");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось очистить таблицу");
        }
    }
}