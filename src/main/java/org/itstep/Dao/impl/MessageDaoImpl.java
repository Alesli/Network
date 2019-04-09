package org.itstep.Dao.impl;

import org.itstep.Dao.MessageDao;
import org.itstep.Dao.RoomDao;
import org.itstep.Dao.UserDao;
import org.itstep.jdbc.ServerConnector;
import org.itstep.jdbc.ServerQuery;
import org.itstep.model.Message;
import org.itstep.model.Room;
import org.itstep.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MessageDaoImpl implements MessageDao {

    private UserDao userDao = new UserDaoImpl();
    private RoomDao roomDao = new RoomDaoImpl();

    private PreparedStatement preparedStatement = null;
    private Connection connection = ServerConnector.getInstance().getConnection();
    private ResultSet resultSet = null;

    @Override
    public Collection<Message> getMessagesFromRoom(Room room, Integer lastCount) {
        List<Message> messageList = new ArrayList<>();
        try {
            String query = ServerQuery.getInstance().getQuery("getMessagesFromRoom");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, room.getCaption());
            preparedStatement.setLong(2, lastCount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = getMessage(resultSet);
                messageList.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, resultSet);
        }
        return messageList;
    }

    @Override
    public Boolean sendTextMessage(Room room, User user, String message) {
        try {
            String query = ServerQuery.getInstance().getQuery("sendTextMessage");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, message);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.setLong(3, room.getId());

            resultSet = preparedStatement.getGeneratedKeys();
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
    public Boolean sendPicture(Room room, User user, byte[] picture) {
        try {
            String query = ServerQuery.getInstance().getQuery("sendPicture");
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, room.getId());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.setBytes(3, picture);

            resultSet = preparedStatement.getGeneratedKeys();
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

    @Override
    public Boolean changeMessage(Long messageId, User owner, String newMessage) {
        try {
            String query = ServerQuery.getInstance().getQuery("changeMessage");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, messageId);
            preparedStatement.setLong(2, owner.getId());

            preparedStatement.setString(3, newMessage);
            preparedStatement.setLong(4, messageId);
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
    public Boolean deleteMessage(Long messageId, User owner) {
        try {
            String query = ServerQuery.getInstance().getQuery("deleteMessage");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, messageId);
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

    private Message getMessage(ResultSet resultSet) {

        try {
            Message message = new Message();
            message.setId(resultSet.getLong(1));
            message.setMessage(resultSet.getString(2));
            message.setUser(userDao.getUserById(resultSet.getLong(3)));
            message.setRoom(roomDao.getRoomFromCaption(resultSet.getString(4)));
            message.setMessageDate(resultSet.getTimestamp(5).toLocalDateTime());

            return message;
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
