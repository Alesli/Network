package org.itstep.dto;

import org.itstep.model.Message;
import org.itstep.model.Room;
import org.itstep.model.User;

import java.util.Collection;
import java.util.List;

public class MainPageDTO {

    private Collection<Room> rooms;
    private Collection<User> users;
    private Collection<Message> messages;
    private User activeUser;
    private Room activeRoom;


    public MainPageDTO(Collection<Room> rooms, Collection<User> users, Collection<Message> messages, User activeUser, Room activeRoom) {
        this.rooms = rooms;
        this.users = users;
        this.messages = messages;
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

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
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
