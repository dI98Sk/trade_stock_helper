package jm.task.core.jdbc.service;


import jm.task.core.jdbc.entity.Oil;

import java.util.Date;
import java.util.List;

public interface OilService {
    void createOilTable();

    void dropOilTable();

    void saveOil(String ticket, String per, Date date_trade,
                 Integer hour_trade, Float open_trade,
                 Float high, Float low, Float close_trade, Integer vol);

    void removeOilById(long id);

    List<Oil> getAllOil();

    void cleanOilTable();
}
