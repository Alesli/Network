package org.itstep.Dao;

import org.itstep.model.Room;
import org.itstep.model.User;

import java.util.Collection;

public interface RoomDao {

    final static Long PRIVATE_ROOM_MAX_USER = 2L;

    Collection<Room> getAllPublicRoom();

    Collection<User> getUsersFromRoom(Room room);

    Room getRoomFromCaption(String caption);

    Collection<Room> getAllRoom();

    String getDefaultRoomCaption();

    Room createRoom(String caption, String password, User owner, Long maxUser);

    Room changeCaption(Room room, String newCaption, User owner);

    Boolean addUserToRoom(Room room, User user);

    Boolean setMaxUser(Room room, User owner, Long maxUser);

    Boolean checkExistUserInToRoom(Room room, User user);

    Boolean deleteRoom(Room room, User owner);

    Boolean leaveRoom(Room room, User user);
}
