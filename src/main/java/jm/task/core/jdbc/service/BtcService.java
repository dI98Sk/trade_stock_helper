package jm.task.core.jdbc.service;

import jm.task.core.jdbc.entity.Gold;

import java.util.Date;
import java.util.List;

public interface BtcService {
    void createBtcTable();

    void dropBtcTable();

    void saveBtc(String ticket, String per, Date date_trade,
                  Integer hour_trade, Float open_trade,
                  Float high, Float low, Float close_trade, Integer vol);

    void removeBtcById(long id);

    List<Gold> getAllBtc();

    void cleanBtcTable();
}
