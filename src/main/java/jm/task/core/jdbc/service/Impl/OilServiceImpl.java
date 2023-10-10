package jm.task.core.jdbc.service.Impl;

import jm.task.core.jdbc.dao.impliments.ImplHibernate.OilDaoHibernateImpl;
import jm.task.core.jdbc.dao.impliments.ImplJDBC.OilDaoJDBCImpl;
import jm.task.core.jdbc.entity.Oil;
import jm.task.core.jdbc.service.OilService;

import java.util.Date;
import java.util.List;

public class OilServiceImpl implements OilService {

    private OilDaoJDBCImpl daoJDBC;
    private OilDaoHibernateImpl daoHibernate;

    public OilServiceImpl() {
        this.daoJDBC = new OilDaoJDBCImpl();
        this.daoHibernate = new OilDaoHibernateImpl();
    }

    @Override
    public void createOilTable() {
        daoHibernate.createOilTable();
    }

    @Override
    public void dropOilTable() {
        daoHibernate.dropOilTable();
    }

    @Override
    public void saveOil(String ticket, String per, Date date_trade, Integer hour_trade, Float open_trade, Float high,
                        Float low, Float close_trade, Integer vol) {
        daoHibernate.saveOilTable(ticket,per,date_trade,hour_trade,open_trade,high,low,close_trade,vol);
    }

    @Override
    public void removeOilById(long id) {
        daoHibernate.removeOilById(id);
    }

    @Override
    public List<Oil> getAllOil() {
         return daoHibernate.getAllOil();
    }

    @Override
    public void cleanOilTable() {
        daoHibernate.cleanOilTable();
    }
}
