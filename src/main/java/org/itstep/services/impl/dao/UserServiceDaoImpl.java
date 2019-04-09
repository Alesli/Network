package org.itstep.services.impl.dao;


import org.itstep.Dao.UserDao;
import org.itstep.Dao.impl.UserDaoImpl;
import org.itstep.model.User;
import org.itstep.services.AdminService;
import org.itstep.services.UserService;

import java.util.Collection;

public class UserServiceDaoImpl implements UserService, AdminService {

    private UserDao userDao = new UserDaoImpl();

    private static UserServiceDaoImpl userServiceClassImpl;

    private UserServiceDaoImpl() {
    }

    public static UserServiceDaoImpl getInstance() {
        if (userServiceClassImpl == null) {
            userServiceClassImpl = new UserServiceDaoImpl();
        }
        return userServiceClassImpl;
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByLoginAndName(String login, String password) {
        return userDao.getUserByLoginAndName(login, password);
    }

    @Override
    public User createUser(String login, String password, String name, String lastName) {
        if (login == null || password == null || name == null) {
            return null;
        }
//        if (user.getLogin().equals(login)) {
//            return null;
//        }
        return userDao.createUser(login, password, name, lastName);
    }

    @Override
    public User setAdmin(Long userId, Boolean isAdmin) {
        return userDao.setAdmin(userId, isAdmin);
    }

    @Override
    public User changeUserPassword(Long userId, String password) {
        return userDao.changeUserPassword(userId, password);
    }

    @Override
    public User changeUserName(Long userId, String name) {
        return userDao.changeUserName(userId, name);
    }

    @Override
    public User changeUserLastName(Long userId, String lastName) {
        return userDao.changeUserLastName(userId, lastName);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        return userDao.deleteUser(userId);
    }

//   ----- user ------

    @Override
    public User addPicture(Long userId, byte[] picture) {
        return userDao.addPicture(userId, picture);
    }

    @Override
    public Boolean deletePicture(Long userId) {
        return userDao.deletePicture(userId);
    }

    @Override
    public User setNickname(Long userId, String nickname) {
        return userDao.setNickname(userId, nickname);
    }
}
