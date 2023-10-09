package jm.task.core.jdbc.dao.impliments.ImplHibernate;

import jm.task.core.jdbc.dao.OilDao;
import jm.task.core.jdbc.entity.Oil;
import jm.task.core.jdbc.entity.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class OilDaoHibernateImpl implements OilDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    @Override
    public void createOilTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "create table if not exists " + Util.dbName + "."
                    + Util.dbTableNameOil +
                    "(id bigint auto_increment, ticket text, per integer, date_trade date," +
                    "hour_trade integer, open_trade float high float, low float," +
                    " close_trade float, vol integer)";
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
    public void dropOilTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableNameOil;;
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
    public void saveOilTable(String ticket, String per, Date date_trade, Integer hour_trade, Float open_trade,
                             Float high, Float low, Float close_trade, Integer vol) {
        try (Session session = sessionFactory.openSession();) {
            Oil oil = new Oil(ticket, per, date_trade,hour_trade, open_trade ,high, low, close_trade, vol);
            session.beginTransaction();
            session.save(oil);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось добавить пользователей");
        }
    }

    @Override
    public void removeOilById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Oil oil = session.get(Oil.class, id);
            session.delete(oil);
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось удалить пользователя");

        }
    }

    @Override
    public List<Oil> getAllOil() {
        List<Oil> oils = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            oils = session.createQuery("from Oil", Oil.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось прочитать всех пользователей");
        }
        return oils;
    }

    @Override
    public void cleanOilTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete Oil").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось очистить таблицу");
        }
    }
}
