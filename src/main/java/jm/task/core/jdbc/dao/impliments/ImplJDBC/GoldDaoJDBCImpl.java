package jm.task.core.jdbc.dao.impliments.ImplJDBC;

import jm.task.core.jdbc.dao.GoldDao;
import jm.task.core.jdbc.entity.Gold;
import jm.task.core.jdbc.entity.Oil;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoldDaoJDBCImpl implements GoldDao {
    public GoldDaoJDBCImpl() {
    }

    @Override
    public void createGoldTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "create table if not exists " + Util.dbName + "." + Util.dbTableNameGold +
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
    public void dropGoldTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableNameGold;

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
    public void saveGoldTable(String ticket, String per, Date date_trade,
                              Integer hour_trade, Float open_trade,
                              Float high, Float low, Float close_trade, Integer vol) {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQueryGold = "INSERT INTO " + Util.dbName + "." + Util.dbTableNameGold
                    + " (ticket, per, date_trade, hour_trade, open_trade, high, " +
                    "low, close_trade, vol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStatementGold = Util.connectionJDBC.prepareStatement(sqlQueryGold);
            preparedStatementGold.setString(1, ticket);
            preparedStatementGold.setString(2, per);
            preparedStatementGold.setDate(3, (java.sql.Date) date_trade);
            preparedStatementGold.setInt(4, hour_trade);
            preparedStatementGold.setFloat(5, open_trade);
            preparedStatementGold.setFloat(6, high);
            preparedStatementGold.setFloat(7, low);
            preparedStatementGold.setFloat(8, close_trade);
            preparedStatementGold.setInt(9, vol);
            preparedStatementGold.executeUpdate();
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
    public void removeGoldById(long id) {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableNameGold + " WHERE id = ?";

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
    public List<Gold> getAllGold() {
        List<Gold> goldList = new ArrayList<>();
        try {
            Util.connectionJDBC.setAutoCommit(true);

            String sqlQuery ="SELECT * FROM " + Util.dbName + "." + Util.dbTableNameGold;

            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Gold gold = new Gold(rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getInt(5), rs.getFloat(6),
                        rs.getFloat(7), rs.getFloat(8), rs.getFloat(9),
                        rs.getInt(10));
                gold.setId(rs.getInt(1));
                goldList.add(gold);
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
        return goldList;
    }

    @Override
    public void cleanGoldTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableNameGold;

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
