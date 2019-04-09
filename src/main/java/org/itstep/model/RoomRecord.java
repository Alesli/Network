package org.itstep.model;

/**
 * Класс связи между пользователем и комнатой
 */
public class RoomRecord {

    private Long id;
    private Room room;
    private User user;

    public Long getId() {
        return id;
    }

    public RoomRecord setId(Long id) {
        this.id = id;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public RoomRecord setRoom(Room room) {
        this.room = room;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RoomRecord setUser(User user) {
        this.user = user;
        return this;
    }
}
