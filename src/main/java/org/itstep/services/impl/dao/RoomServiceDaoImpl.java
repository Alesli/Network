package org.itstep.services.impl.dao;

import org.itstep.Dao.RoomDao;
import org.itstep.Dao.impl.RoomDaoImpl;
import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.services.RoomService;

import java.util.Collection;

public class RoomServiceDaoImpl implements RoomService {

    private RoomDao roomDao = new RoomDaoImpl();

    //private static final String MAIN_ROOM_CAPTION = "Main_Boltalka";
    private static RoomServiceDaoImpl roomServiceDaoImpl;

    private RoomServiceDaoImpl() {
    }

    public static RoomServiceDaoImpl getInstance() {
        if (roomServiceDaoImpl == null) {
            roomServiceDaoImpl = new RoomServiceDaoImpl();
        }
        return roomServiceDaoImpl;
    }

    private Boolean isOwner(User owner, Room room) {
        return room.getCaption().compareTo(getDefaultRoomCaption()) == 0 || room.getOwner().equals(owner) || owner.getAdmin();

    }

    @Override
    public Collection<Room> getAllRoom() {
        return roomDao.getAllRoom();
    }

    @Override
    public Collection<Room> getAllPublicRoom() {
        return roomDao.getAllPublicRoom();
    }

    @Override
    public Room getRoomFromCaption(String caption) {
        return roomDao.getRoomFromCaption(caption);
    }

    @Override
    public Collection<User> getUsersFromRoom(Room room) {
        return roomDao.getUsersFromRoom(room);
    }

    @Override
    public Room createRoom(String caption, String password, User owner, Long maxUser) {
        if (getRoomFromCaption(caption) != null || caption == null || caption.isEmpty() || owner == null || maxUser == null || maxUser < 2) {
            return null;
        }
        return roomDao.createRoom(caption, password, owner, maxUser);
    }

    @Override
    public Room changeCaption(Room room, String newCaption, User owner) {
        if (isOwner(owner, room)) {
            return roomDao.changeCaption(room, newCaption, owner);
        }
        return null;
    }

    @Override
    public Boolean deleteRoom(Room room, User owner) {
        if (isOwner(owner, room)) {
            return roomDao.deleteRoom(room, owner);
        }
        return false;
    }

    @Override
    public Boolean addUserToRoom(Room room, User user, User owner) {
        if(isOwner(owner,room)){
        return roomDao.addUserToRoom(room, user);
        }
        return false;

    }

    @Override
    public Boolean leaveRoom(Room room, User user, User owner) {
        if(isOwner(owner,room)) {
            return roomDao.leaveRoom(room, user);
        }
        return false;
    }

    @Override
    public Boolean setMaxUser(Room room, User owner, Long maxUser) {
        if(isOwner(owner,room)) {
            return roomDao.setMaxUser(room, owner, maxUser);
        }
        return false;
    }

    @Override
    public Boolean checkExistUserInToRoom(Room room, User user) {
        return roomDao.checkExistUserInToRoom(room, user);
    }

    public Boolean isPublicRoom(Room room) {
        return room.getMaxUser() > PRIVATE_ROOM_MAX_USER;
    }

    public String getDefaultRoomCaption() {
        return roomDao.getDefaultRoomCaption();
    }

}
