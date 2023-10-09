package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.impliments.ImplJDBC.UserDaoJDBCImpl;
import jm.task.core.jdbc.entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl daoJDBC;
    private UserDaoJDBCImpl daoHibernate;

    public UserServiceImpl() {
        this.daoJDBC = new UserDaoJDBCImpl();
        this.daoHibernate = new UserDaoJDBCImpl();
    }

    public void createUsersTable() {
        daoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        daoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        daoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        daoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return daoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        daoHibernate.cleanUsersTable();
    }
}