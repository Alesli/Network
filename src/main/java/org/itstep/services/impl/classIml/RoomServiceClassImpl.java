package org.itstep.services.impl.classIml;

import org.itstep.model.Room;
import org.itstep.model.RoomRecord;
import org.itstep.model.User;
import org.itstep.services.RoomService;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class RoomServiceClassImpl implements RoomService {

    private Set<Room> rooms = new HashSet<>();
    private List<RoomRecord> roomRecords = new ArrayList<>();

    private static final String MAIN_ROOM_CAPTION = "MainBoltalka";

    private LongAdder counter = new LongAdder();

    private static RoomServiceClassImpl roomServiceClass;

    // private AdminService userService = UserServiceClassImpl.getInstance();

//    private RoomServiceClassImpl() {
//        counter.increment();
//        User user = userService.getUserById(1L);
//        Room room = new Room()
//                .setCaption(MAIN_ROOM_CAPTION)
//                .setMaxUser(999L)
//                .setId(counter.sum());
//        rooms.add(room);
//    }

    public static RoomServiceClassImpl getInstance() {
        if (roomServiceClass == null) {
            roomServiceClass = new RoomServiceClassImpl();
        }
        return roomServiceClass;
    }

    @Override
    public Room createRoom(String caption, String password, User owner, Long maxUser) {
        counter.increment();
        if (getRoomFromCaption(caption) != null || caption == null || caption.isEmpty() || owner == null || maxUser == null || maxUser < 2) {
            return null;
        }
        Room room = new Room()
                .setCaption(caption)
                .setPassword(password)
                .setOwner(owner)
                .setMaxUser(maxUser)
                .setId(counter.sum());
        rooms.add(room);
        return room;
    }

    @Override
    public Room changeCaption(Room room, String newCaption, User owner) {
        if (isOwner(owner, room)) {
            return room.setCaption(newCaption);
        }
        throw new RuntimeException("Caption wasn't change caption");
    }

    @Override
    public Boolean deleteRoom(Room room, User owner) {
        if (isOwner(owner, room)) {
            roomRecords.removeAll(roomRecords
                    .stream()
                    .filter(rm -> rm.getRoom().equals(room))
                    .collect(Collectors.toList()));
            return rooms.remove(room);
        }
        throw new RuntimeException("Room wasn't delete");
    }

    @Override
    public Boolean addUserToRoom(Room room, User user, User owner) {
        if (isOwner(owner, room)) {
            counter.increment();
            return roomRecords.add(new RoomRecord()
                    .setId(counter.sum())
                    .setRoom(room)
                    .setUser(user));
        }
        return false;
    }

    @Override
    public Boolean leaveRoom(Room room, User user, User owner) {
        if (isOwner(owner, room)) {
            roomRecords.removeAll(roomRecords.stream()
                    .filter(rm -> rm.getRoom().equals(room) && rm.getUser().equals(user))
                    .collect(Collectors.toList()));
            if (roomRecords.stream().noneMatch(r -> r.getRoom().equals(room))) {
                rooms.remove(room);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean setMaxUser(Room room, User owner, Long maxUser) {
        if (isOwner(owner, room)) {
            room.setMaxUser(maxUser);
            return true;
        }
        return false;
    }

    private Boolean isOwner(User owner, Room room) {
        return room.getCaption().compareTo(MAIN_ROOM_CAPTION) == 0 || room.getOwner().equals(owner) || owner.getAdmin();
    }

    @Override
    public Collection<Room> getAllPublicRoom() {
        return rooms.stream()
                .filter(r -> r.getMaxUser() > PRIVATE_ROOM_MAX_USER)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<User> getUsersFromRoom(Room room) {
        return roomRecords.stream()
                .filter(r -> r.getRoom().equals(room))
                .map(RoomRecord::getUser)
                .collect(Collectors.toList());


        // Специально что бы было понятно!!!!!!!!!!
//        List<User> userFromRoom = new ArrayList<>();
//        for (RoomRecord r :  roomRecords) {
//            if(r.getRoom().equals(room))
//            {
//                userFromRoom.add(r.getUser());
//            }
//        }
//        return userFromRoom;

    }


    @Override
    public Room getRoomFromCaption(String caption) {
        return rooms.stream()
                .filter(r -> r.getCaption()
                        .compareTo(caption) == 0)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean checkExistUserInToRoom(Room room, User user) {

        for (RoomRecord rr : roomRecords) {
            if (rr.getRoom().equals(room) & rr.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Room> getAllRoom() {
        return new ArrayList<>(rooms);
    }

    public String getDefaultRoomCaption() {
        return MAIN_ROOM_CAPTION;
    }

    public Boolean isPublicRoom(Room room) {
        return room.getMaxUser() > PRIVATE_ROOM_MAX_USER;
    }
}
