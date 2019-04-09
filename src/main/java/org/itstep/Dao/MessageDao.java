package org.itstep.Dao;

import org.itstep.model.Message;
import org.itstep.model.Room;
import org.itstep.model.User;

import java.util.Collection;

public interface MessageDao {
    Collection<Message> getMessagesFromRoom(Room room, Integer lastCount);

    Boolean sendTextMessage(Room room, User user, String message);

    Boolean changeMessage(Long messageId, User owner, String newMessage);

    Boolean deleteMessage(Long messageId, User owner);

    Boolean sendPicture(Room room, User user, byte[] picture);
}
