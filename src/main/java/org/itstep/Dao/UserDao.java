package org.itstep.Dao;

import org.itstep.model.User;

import java.util.Collection;

public interface UserDao {

    User getUserById(Long id);

    Collection<User> getAllUsers();

    User getUserByLoginAndName(String login, String password);

    User createUser(String login, String password, String name, String lastName);

    User setAdmin(Long userId, Boolean isAdmin);

    User changeUserPassword(Long userId, String password);

    User changeUserName(Long userId, String name);

    User changeUserLastName(Long userId, String lastName);

    Boolean deleteUser(Long userId);

       //-----------------
    //user

    User addPicture(Long userId, byte[] picture);

    Boolean deletePicture(Long userId);

    User setNickname(Long userId, String nickname);
}
