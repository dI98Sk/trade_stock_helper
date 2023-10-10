package jm.task.core.jdbc.service.Impl;

import jm.task.core.jdbc.dao.impliments.ImplHibernate.GoldDaoHibernateImpl;
import jm.task.core.jdbc.dao.impliments.ImplHibernate.OilDaoHibernateImpl;
import jm.task.core.jdbc.dao.impliments.ImplJDBC.GoldDaoJDBCImpl;
import jm.task.core.jdbc.dao.impliments.ImplJDBC.OilDaoJDBCImpl;
import jm.task.core.jdbc.entity.Gold;
import jm.task.core.jdbc.service.GoldService;

import java.util.Date;
import java.util.List;

public class GoldServiceImpl implements GoldService {

    private GoldDaoJDBCImpl daoJDBC;
    private GoldDaoHibernateImpl daoHibernate;

    public GoldServiceImpl() {
        this.daoJDBC = new GoldDaoJDBCImpl();
        this.daoHibernate = new GoldDaoHibernateImpl();
    }

    @Override
    public void createGoldTable() {
        daoHibernate.createGoldTable();
    }

    @Override
    public void dropGoldTable() {
        daoHibernate.dropGoldTable();
    }

    @Override
    public void saveGold(String ticket, String per, Date date_trade, Integer hour_trade, Float open_trade,
                         Float high, Float low, Float close_trade, Integer vol) {
        daoHibernate.saveGoldTable(ticket,per,date_trade,hour_trade,open_trade,high,low,close_trade,vol);
    }

    @Override
    public void removeGoldById(long id) {
        daoHibernate.removeGoldById(id);
    }

    @Override
    public List<Gold> getAllGold() {
        return daoHibernate.getAllGold();
    }

    @Override
    public void cleanGoldTable() {
        daoHibernate.cleanGoldTable();
    }
}
