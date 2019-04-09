package org.itstep.Dao.impl;

import org.itstep.Dao.UserDao;
import org.itstep.jdbc.ServerConnector;
import org.itstep.jdbc.ServerQuery;
import org.itstep.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection = ServerConnector.getInstance().getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    //admin

    @Override
    public User getUserById(Long id) {
        User user = new User();
        try {
            String query = ServerQuery.getInstance().getQuery("getUserById");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUser(resultSet);
//                user.setId(resultSet.getLong(1));
//                user.setLogin(resultSet.getString(2));
//                user.setPassword(resultSet.getString(3));
//                user.setName(resultSet.getString(4));
//                user.setLastName(resultSet.getString(5));
//                user.setNickname(resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public User getUserByLoginAndName(String login, String password) {
        User user = new User();
        try {
            String query = ServerQuery.getInstance().getQuery("getUserByLoginAndPassword");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                user = getUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = ServerQuery.getInstance().getQuery("getAllUsers");
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return userList;
    }

    @Override
    public User createUser(String login, String password, String name, String lastName) {
        try {
            String query = ServerQuery.getInstance().getQuery("createUser");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, lastName);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return getUserById(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User setAdmin(Long userId, Boolean isAdmin) {

        try {
            String query = ServerQuery.getInstance().getQuery("setAdmin");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setBoolean( 2, isAdmin);

            preparedStatement.setLong(3, userId);
            if (preparedStatement.executeUpdate() > 0) {
                return getUserById(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User changeUserPassword(Long userId, String password) {

        try {
            String query = ServerQuery.getInstance().getQuery("changeUserPassword");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, userId);
            if (preparedStatement.executeUpdate() > 0) {
                return getUserById(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User changeUserName(Long userId, String name) {

        try {
            String query = ServerQuery.getInstance().getQuery("changeUserName");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, userId);
            if (preparedStatement.executeUpdate() > 0) {
                return getUserById(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User changeUserLastName(Long userId, String lastName) {
        try {
            String query = ServerQuery.getInstance().getQuery("changeUserLastName");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, userId);
            if (preparedStatement.executeUpdate() > 0) {
                return getUserById(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        try {
            String query = ServerQuery.getInstance().getQuery("deleteUser");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return false;
    }

    public User getUser(ResultSet resultSet) {
        try {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setName(resultSet.getString(4));
            user.setLastName(resultSet.getString(5));
            user.setNickname(resultSet.getString(6));
            user.setAdmin(resultSet.getBoolean(7));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //-------------------------------------
    // user

    @Override
    public User addPicture(Long userId, byte[] picture) {
        return null;
    }

    @Override
    public Boolean deletePicture(Long userId) {

        try {
            String query = ServerQuery.getInstance().getQuery("deletePicture");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public User setNickname(Long userId, String nickname) {
        try {
            String query = ServerQuery.getInstance().getQuery("setNickname");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, nickname);

            preparedStatement.setLong(3, userId);
            if (preparedStatement.executeUpdate() > 0) {
                return getUserById(userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * метод закрывает соединение
     *
     * @param preparedStatement
     * @param resultSet
     */
    private void close(PreparedStatement preparedStatement, ResultSet resultSet) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
