package jm.task.core.jdbc.service.Impl;

import jm.task.core.jdbc.dao.impliments.ImplHibernate.BtcDaoHibernateImpl;
import jm.task.core.jdbc.dao.impliments.ImplHibernate.GoldDaoHibernateImpl;
import jm.task.core.jdbc.dao.impliments.ImplJDBC.BtcDaoJDBCImpl;
import jm.task.core.jdbc.dao.impliments.ImplJDBC.GoldDaoJDBCImpl;
import jm.task.core.jdbc.entity.Btc;
import jm.task.core.jdbc.entity.Gold;
import jm.task.core.jdbc.service.BtcService;

import java.util.Date;
import java.util.List;

public class BtcServiceImpl implements BtcService {
    private BtcDaoJDBCImpl daoJDBC;
    private BtcDaoHibernateImpl daoHibernate;

    public BtcServiceImpl() {
        this.daoJDBC = new BtcDaoJDBCImpl();
        this.daoHibernate = new BtcDaoHibernateImpl();
    }

    @Override
    public void createBtcTable(){
        daoHibernate.createBtcTable();
    }

    @Override
    public void dropBtcTable() {
        daoHibernate.dropBtcTable();
    }

    @Override
    public void saveBtc(String ticket, String per, Date date_trade, Integer hour_trade, Float open_trade,
                        Float high, Float low, Float close_trade, Integer vol) {
        daoHibernate.saveBtcTable(ticket,per,date_trade,hour_trade,open_trade,high,low,close_trade,vol);
    }

    @Override
    public void removeBtcById(long id) {
        daoHibernate.removeBtcById(id);
    }

    @Override
    public List<Btc> getAllBtc() {
        return daoHibernate.getAllBtc();
    }

    @Override
    public void cleanBtcTable() {
        daoHibernate.cleanBtcTable();
    }
}
