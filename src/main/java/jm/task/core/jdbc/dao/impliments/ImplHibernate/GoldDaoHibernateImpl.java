package jm.task.core.jdbc.dao.impliments.ImplHibernate;

import jm.task.core.jdbc.dao.GoldDao;
import jm.task.core.jdbc.entity.Gold;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class GoldDaoHibernateImpl implements GoldDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    @Override
    public void createGoldTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "create table if not exists " + Util.dbName + "."
                    + Util.dbTableNameGold +
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
    public void dropGoldTable() {

    }

    @Override
    public void saveGoldTable(String ticket, String per, Date date_trade, Integer hour_trade, Float open_trade, Float high, Float low, Float close_trade, Integer vol) {

    }

    @Override
    public void removeGoldById(long id) {

    }

    @Override
    public List<Gold> getAllGold() {
        return null;
    }

    @Override
    public void cleanGoldTable() {

    }
}
