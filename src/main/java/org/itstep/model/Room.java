package org.itstep.model;

import java.util.Objects;

/**
 * Класс которыя содержит в себе данные комнаты
 */

public class Room {

    private Long id;
    private String caption;
    private String password;
    private User owner;
    private Long maxUser;
    private Boolean defaultRoom;

    public Long getId() {
        return id;
    }

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Room setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Room setPassword(String password) {
        this.password = password;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Room setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Long getMaxUser() {
        return maxUser;
    }

    public Room setMaxUser(Long maxUser) {
        this.maxUser = maxUser;
        return this;
    }
    public Boolean getDefaultRoom() {
        return defaultRoom;
    }

    public Room setDefaultRoom(Boolean defaultRoom) {
        this.defaultRoom = defaultRoom;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(caption, room.caption) &&
                Objects.equals(password, room.password) &&
                Objects.equals(owner, room.owner) &&
                Objects.equals(maxUser, room.maxUser) &&
                Objects.equals(defaultRoom, room.defaultRoom);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, caption, password, owner, maxUser, defaultRoom);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", password='" + password + '\'' +
                ", owner=" + owner +
                ", maxUser=" + maxUser +
                ", defaultRoom=" + defaultRoom +
                '}';
    }

    //
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Room room = (Room) o;
//
//        if (!id.equals(room.id)) return false;
//        if (caption != null ? !caption.equals(room.caption) : room.caption != null) return false;
//        if (password != null ? !password.equals(room.password) : room.password != null) return false;
//        if (!owner.equals(room.owner)) return false;
//        return maxUser != null ? maxUser.equals(room.maxUser) : room.maxUser == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + (caption != null ? caption.hashCode() : 0);
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + (owner != null ? owner.hashCode() : 0);
//        result = 31 * result + (maxUser != null ? maxUser.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "Room{" +
//                "id=" + id +
//                ", caption='" + caption + '\'' +
//                ", password='" + password + '\'' +
//                ", owner=" + owner +
//                ", maxUser=" + maxUser +
//                '}';
//    }
}
