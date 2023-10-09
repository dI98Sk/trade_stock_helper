package jm.task.core.jdbc.dao.impliments.ImplHibernate;

import jm.task.core.jdbc.dao.GoldDao;
import jm.task.core.jdbc.entity.Gold;
import jm.task.core.jdbc.entity.Oil;
import jm.task.core.jdbc.entity.User;
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
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableNameGold;;
            session.createSQLQuery(sqlQuery)
                    .addEntity(Gold.class)
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
    public void saveGoldTable(String ticket, String per, Date date_trade, Integer hour_trade, Float open_trade,
                              Float high, Float low, Float close_trade, Integer vol) {
        try (Session session = sessionFactory.openSession();) {
            Gold gold = new Gold(ticket, per, date_trade,hour_trade, open_trade ,high, low, close_trade, vol);
            session.beginTransaction();
            session.save(gold);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось добавить пользователей");
        }
    }

    @Override
    public void removeGoldById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Gold gold = session.get(Gold.class, id);
            session.delete(gold);
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось удалить пользователя");
        }
    }

    @Override
    public List<Gold> getAllGold() {
        List<Gold> golds = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            golds = session.createQuery("from Gold ", Gold.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e){
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось прочитать всех пользователей");
        }
        return golds;
    }

    @Override
    public void cleanGoldTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete Gold").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (sessionFactory.openSession() != null) sessionFactory.openSession().getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Не получилось очистить таблицу");
        }
    }
}
