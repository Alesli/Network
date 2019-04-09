package org.itstep.dto;

import org.itstep.model.Room;
import org.itstep.model.User;

import java.util.Collection;

public class AdminPageDTO {

    private Collection<Room> rooms;
    private Collection<User> users;
    private User activeUser;
    private Room activeRoom;


    public AdminPageDTO(Collection<Room> rooms, Collection<User> users, User activeUser, Room activeRoom) {
        this.rooms = rooms;
        this.users = users;
        this.activeUser = activeUser;
        this.activeRoom = activeRoom;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public Room getActiveRoom() {
        return activeRoom;
    }

    public void setActiveRoom(Room activeRoom) {
        this.activeRoom = activeRoom;
    }
}
