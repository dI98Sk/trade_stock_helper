package jm.task.core.jdbc.dao.impliments.ImplJDBC;

import jm.task.core.jdbc.dao.OilDao;
import jm.task.core.jdbc.entity.Oil;
import jm.task.core.jdbc.entity.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OilDaoJDBCImpl implements OilDao {

    public OilDaoJDBCImpl() {
    }

    @Override
    public void createOilTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);
            String sqlQuery ="create table if not exists " + Util.dbName + "." + Util.dbTableNameOil +
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
    public void dropOilTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableNameOil;

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
    public void saveOilTable(String ticket, String per, Date date_trade,
                             Integer hour_trade, Float open_trade,
                             Float high, Float low, Float close_trade, Integer vol) {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQueryOil = "INSERT INTO " + Util.dbName + "." + Util.dbTableNameOil
                    + " (ticket, per, date_trade, hour_trade, open_trade, high, " +
                    "low, close_trade, vol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStatementOil = Util.connectionJDBC.prepareStatement(sqlQueryOil);
            preparedStatementOil.setString(1, ticket);
            preparedStatementOil.setString(2, per);
            preparedStatementOil.setDate(3, (java.sql.Date) date_trade);
            preparedStatementOil.setInt(4, hour_trade);
            preparedStatementOil.setFloat(5, open_trade);
            preparedStatementOil.setFloat(6, high);
            preparedStatementOil.setFloat(7, low);
            preparedStatementOil.setFloat(8, close_trade);
            preparedStatementOil.setInt(9, vol);
            preparedStatementOil.executeUpdate();
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
    public void removeOilById(long id) {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableNameOil + " WHERE id = ?";

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
    public List<Oil> getAllOil() {
        List<Oil> oilList = new ArrayList<>();
        try {
            Util.connectionJDBC.setAutoCommit(true);

            String sqlQuery ="SELECT * FROM " + Util.dbName + "." + Util.dbTableNameOil;

            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Oil oil = new Oil(rs.getString(2), rs.getString(3),
                        rs.getDate(4), rs.getInt(5), rs.getFloat(6),
                        rs.getFloat(7), rs.getFloat(8), rs.getFloat(9),
                        rs.getInt(10));
                oil.setId(rs.getInt(1));
                oilList.add(oil);
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
        return oilList;
    }

    @Override
    public void cleanOilTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);

            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableNameOil;

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
