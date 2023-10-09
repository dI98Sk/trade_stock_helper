package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.entity.Gold;

import java.util.Date;
import java.util.List;

public interface GoldDao {
    void createGoldTable();
    void dropGoldTable();
    void saveGoldTable(String ticket, String per, Date date_trade,
                       Integer hour_trade, Float open_trade,
                       Float high, Float low, Float close_trade, Integer vol);
    void removeGoldById(long id);
    List<Gold> getAllGold();
    void cleanGoldTable();
}
