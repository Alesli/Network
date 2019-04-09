package org.itstep.services.impl.dao;

import org.itstep.Dao.MessageDao;
import org.itstep.Dao.impl.MessageDaoImpl;
import org.itstep.model.Message;
import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.services.MessageService;

import java.util.Collection;

public class MessageServiceDaoImpl implements MessageService {

    private MessageDao messageDao = new MessageDaoImpl();
    private static MessageServiceDaoImpl messageServiceDaoImpl;

    private MessageServiceDaoImpl() {
    }

    public static MessageServiceDaoImpl getInstance() {
        if (messageServiceDaoImpl == null) {
            messageServiceDaoImpl = new MessageServiceDaoImpl();
        }
        return messageServiceDaoImpl;
    }

    @Override
    public Collection<Message> getMessagesFromRoom(Room room, Integer lastCount) {
        return messageDao.getMessagesFromRoom(room, lastCount);
    }

    @Override
    public Boolean sendTextMessage(Room room, User user, String message) {
        if (!isValidUserAndRoom(user, room)) {
            return false;
        }
        return messageDao.sendTextMessage(room, user, message);
    }

    @Override
    public Boolean changeMessage(Long messageId, User owner, String newMessage) {
        return messageDao.changeMessage(messageId, owner, newMessage);
    }

    @Override
    public Boolean deleteMessage(Long messageId, User owner) {
        return messageDao.deleteMessage(messageId, owner);
    }

    @Override
    public Boolean sendPicture(Room room, User user, byte[] picture) {
        return messageDao.sendPicture(room, user, picture);
    }

    private Boolean isValidUserAndRoom(User user, Room room) {
        return room != null && user != null;
    }
}
