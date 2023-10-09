package jm.task.core.jdbc.dao.impliments.ImplJDBC;

import jm.task.core.jdbc.dao.BtcDao;
import jm.task.core.jdbc.entity.Btc;
import jm.task.core.jdbc.entity.Gold;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BtcDaoJDBCImpl implements BtcDao {

    public BtcDaoJDBCImpl() {
    }

    @Override
    public void createBtcTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "create table if not exists " + Util.dbName + "." + Util.dbTableNameBtc +
                    "(id bigint auto_increment, ticket text, per integer, date_trade date," +
                    "hour_trade integer, open_trade float high float, low float," +
                    " close_trade float, vol integer)";

            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error creating the table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    @Override
    public void dropBtcTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableNameBtc;

            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    @Override
    public void saveBtcTable(String ticket, String per, Date date_trade,
                             Integer hour_trade, Float open_trade,
                             Float high, Float low, Float close_trade, Integer vol) {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQueryGold = "INSERT INTO " + Util.dbName + "." + Util.dbTableNameBtc
                    + " (ticket, per, date_trade, hour_trade, open_trade, high, " +
                    "low, close_trade, vol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStatementBtc = Util.connectionJDBC.prepareStatement(sqlQueryGold);
            preparedStatementBtc.setString(1, ticket);
            preparedStatementBtc.setString(2, per);
            preparedStatementBtc.setDate(3, (java.sql.Date) date_trade);
            preparedStatementBtc.setInt(4, hour_trade);
            preparedStatementBtc.setFloat(5, open_trade);
            preparedStatementBtc.setFloat(6, high);
            preparedStatementBtc.setFloat(7, low);
            preparedStatementBtc.setFloat(8, close_trade);
            preparedStatementBtc.setInt(9, vol);
            preparedStatementBtc.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when adding a record to the table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    @Override
    public void removeBtcById(long id) {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableNameBtc + " WHERE id = ?";

            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a record from a table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    @Override
    public List<Btc> getAllBtc() {
        List<Btc> btcList = new ArrayList<>();
        try {
            Util.connectionJDBC.setAutoCommit(true);

            String sqlQuery ="SELECT * FROM " + Util.dbName + "." + Util.dbTableNameBtc;

            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Btc btc = new Btc(rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getInt(5), rs.getFloat(6),
                        rs.getFloat(7), rs.getFloat(8), rs.getFloat(9),
                        rs.getInt(10));
                btc.setId(rs.getInt(1));
                btcList.add(btc);
            }
            Util.connectionJDBC.setAutoCommit(false);
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when select from table");
        }
        return btcList;
    }

    @Override
    public void cleanBtcTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableNameBtc;

            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.execute();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a record from a table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }
}
