package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.entity.Btc;

import java.util.Date;
import java.util.List;

public interface BtcDao {
    void createBtcTable();
    void dropBtcTable();
    void saveBtcTable(String ticket, String per, Date date_trade,
                      Integer hour_trade, Float open_trade,
                      Float high, Float low, Float close_trade, Integer vol);
    void removeBtcById(long id);
    List<Btc> getAllBtc();

    void  cleanBtcTable();
}
