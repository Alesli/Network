package org.itstep.Dao.impl;

import org.itstep.Dao.RoomDao;
import org.itstep.Dao.UserDao;
import org.itstep.jdbc.ServerConnector;
import org.itstep.jdbc.ServerQuery;
import org.itstep.model.Room;
import org.itstep.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private PreparedStatement preparedStatement = null;
    private Connection connection = ServerConnector.getInstance().getConnection();
    private ResultSet resultSet = null;
    private UserDao userDao = new UserDaoImpl();
    private UserDaoImpl userDaoImpl = new UserDaoImpl();



    @Override
    public Collection<Room> getAllPublicRoom() {
        List<Room> roomList = new ArrayList<>();
        try {
            String query = ServerQuery.getInstance().getQuery("getAllPublicRoom");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, PRIVATE_ROOM_MAX_USER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = getRoom(resultSet);
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return roomList;
    }

    @Override
    public Collection<User> getUsersFromRoom(Room room) {
        List<User> userList = new ArrayList<>();
        try {
            String query = ServerQuery.getInstance().getQuery("getUsersFromRoom");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, room.getCaption());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = userDaoImpl.getUser(resultSet);
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
    public Room getRoomFromCaption(String caption) {
        try {
            String query = ServerQuery.getInstance().getQuery("getRoomFromCaption");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, caption);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return getRoom(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public Collection<Room> getAllRoom() {
        List<Room> roomList = new ArrayList<>();
        try {
            String query = ServerQuery.getInstance().getQuery("getAllRoom");
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = getRoom(resultSet);
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return roomList;
    }

    @Override
    public String getDefaultRoomCaption() {
        try {
            String query = ServerQuery.getInstance().getQuery("getDefaultRoomCaption");
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return String.valueOf(resultSet.getString("caption"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public Room createRoom(String caption, String password, User owner, Long maxUser) {
        try {
            String query = ServerQuery.getInstance().getQuery("createRoom");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, caption);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, owner.getId());
            preparedStatement.setLong(4, maxUser);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                query = ServerQuery.getInstance().getQuery("getRoomById");
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, resultSet.getLong(1));
                ResultSet newRoom = preparedStatement.executeQuery();
                if (newRoom.next()) {
                    Room room = new Room();
                    room.setId(newRoom.getLong(1));
                    room.setCaption(newRoom.getString("caption"));
                    room.setDefaultRoom(newRoom.getBoolean(3));
                    room.setPassword(newRoom.getString("password"));
                    room.setOwner(userDao.getUserById(newRoom.getLong(5)));
                    room.setMaxUser(maxUser);
                    newRoom.close();
                    return room;
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public Boolean addUserToRoom(Room room, User user) {

        try {
            String query = ServerQuery.getInstance().getQuery("addUserToRoom");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, room.getId());

            resultSet = preparedStatement.getGeneratedKeys();
            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public Room changeCaption(Room room, String newCaption, User owner) {
        try {
            String query = ServerQuery.getInstance().getQuery("changeCaption");
            preparedStatement = connection.prepareStatement(query);
         //   preparedStatement.setLong(1, room.getId());
            preparedStatement.setString(1, newCaption);

            preparedStatement.setString(2, room.getCaption());
            preparedStatement.setLong(3, owner.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return getRoomFromCaption(newCaption);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public Boolean setMaxUser(Room room, User owner, Long maxUser) {
        try {
            String query = ServerQuery.getInstance().getQuery("setMaxUser");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, room.getId());
            preparedStatement.setLong(2, maxUser);
            preparedStatement.setString(3, room.getCaption());
            preparedStatement.setLong(4, owner.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public Boolean checkExistUserInToRoom(Room room, User user) {
        try {
            String query = ServerQuery.getInstance().getQuery("checkExistUserInToRoom");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, room.getCaption());
            preparedStatement.setLong(2, user.getId());

            if (preparedStatement.execute()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return false;
    }

    //select * from room where not exists(select * from user_room where room_id= room.getId())
    @Override
    public Boolean deleteRoom(Room room, User owner) {
        try {
            String query = ServerQuery.getInstance().getQuery("deleteRoom");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, room.getCaption());
            preparedStatement.setLong(2, owner.getId());

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

    @Override
    public Boolean leaveRoom(Room room, User user) {
        try {
            String query = ServerQuery.getInstance().getQuery("leaveRoom");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, room.getId());
            preparedStatement.setLong(2, user.getId());

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

    private Room getRoom(ResultSet resultSet) {

        try {
            Room room = new Room();
            room.setId(resultSet.getLong(1));
            room.setCaption(resultSet.getString(2));
            room.setDefaultRoom(resultSet.getBoolean(3));
            room.setPassword(resultSet.getString(4));
            room.setOwner(userDao.getUserById(resultSet.getLong(5)));
            room.setMaxUser(resultSet.getLong(6));
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
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
