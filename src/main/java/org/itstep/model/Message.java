package org.itstep.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class Message {

    private Long id;
    private User user;
    private Room room;
    private LocalDateTime messageDate;
    private String message;
    private byte[] picture;

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Message setUser(User user) {
        this.user = user;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Message setRoom(Room room) {
        this.room = room;
        return this;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public Message setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public byte[] getPicture() {
        return picture;
    }

    public Message setPicture(byte[] picture) {
        this.picture = picture;
        return this;
    }

    @Override
    public String toString() {
        return "Message:" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", messageDate=" + messageDate +
                ", message='" + message + '\'' +
                ", picture=" + Arrays.toString(picture);
    }
}
