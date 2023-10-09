package jm.task.core.jdbc.dao.impliments.ImplHibernate;

import jm.task.core.jdbc.dao.BtcDao;
import jm.task.core.jdbc.dao.GoldDao;
import jm.task.core.jdbc.entity.Btc;
import jm.task.core.jdbc.entity.Gold;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class BtcDaoHibernateImpl implements BtcDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createBtcTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "create table if not exists " + Util.dbName + "."
                    + Util.dbTableNameBtc +
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
    public void dropBtcTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableNameBtc;;
            session.createSQLQuery(sqlQuery)
                    .addEntity(Btc.class)
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
    public void saveBtcTable(String ticket, String per, Date date_trade, Integer hour_trade, Float open_trade,
                             Float high, Float low, Float close_trade, Integer vol) {
        try (Session session = sessionFactory.openSession();) {
            Btc btc = new Btc(ticket, per, date_trade,hour_trade, open_trade ,high, low, close_trade, vol);
            session.beginTransaction();
            session.save(btc);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось добавить пользователей");
        }
    }

    @Override
    public void removeBtcById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Btc btc = session.get(Btc.class, id);
            session.delete(btc);
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось удалить пользователя");
        }
    }

    @Override
    public List<Btc> getAllBtc() {
        List<Btc> btcs = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            btcs = session.createQuery("from Btc ", Btc.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось прочитать всех пользователей");
        }
        return btcs;
    }

    @Override
    public void cleanBtcTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete Btc").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось очистить таблицу");
        }
    }
}
