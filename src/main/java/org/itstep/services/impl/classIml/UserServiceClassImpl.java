package org.itstep.services.impl.classIml;


import org.itstep.model.User;
import org.itstep.services.AdminService;
import org.itstep.services.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

public class UserServiceClassImpl implements UserService, AdminService {

    private Map<Long, User> users = new HashMap<>();

    private LongAdder counter = new LongAdder();

    private static UserServiceClassImpl userServiceClassImpl;

    private UserServiceClassImpl() {
        User user = createUser("sa", "sa", "Super", "Admin");
        setAdmin(user.getId(), true);
        createUser("user", "user", "User", "Simple");
    }

    public static UserServiceClassImpl getInstance() {
        if (userServiceClassImpl == null) {
            userServiceClassImpl = new UserServiceClassImpl();
        }
        return userServiceClassImpl;
    }

    @Override
    public User addPicture(Long userId, byte[] picture) {
        return getUserById(userId).setPicture(picture);
    }

    @Override
    public Boolean deletePicture(Long userId) {
        return getUserById(userId).setPicture(null) == null;
    }

    @Override
    public User setNickname(Long userId, String nickname) {
        return getUserById(userId).setNickname(nickname);
    }

    @Override
    public User createUser(String login, String password, String name, String lastName) {
        if (login == null || password == null || name == null) {
            return null;
        }
        for (User user : users.values()) {
            if (user.getLogin().equals(login)) {
                return null;
            }
        }
        counter.increment();
        User user = new User()
                .setLogin(login)
                .setPassword(password)
                .setName(name)
                .setLastName(lastName)
                .setId(counter.sum());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User setAdmin(Long userId, Boolean isAdmin) {
        return getUserById(userId).setAdmin(isAdmin);
    }

    @Override
    public User getUserById(Long userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        return user;
    }

    @Override
    public User changeUserPassword(Long userId, String password) {
        return getUserById(userId).setPassword(password);
    }

    @Override
    public User changeUserName(Long userId, String name) {
        return getUserById(userId).setName(name);
    }

    @Override
    public User changeUserLastName(Long userId, String lastName) {
        return getUserById(userId).setLastName(lastName);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        return users.remove(userId) != null;
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

//    @Override
//    public Boolean isExistUser(String login, String password) {
//        return getUserByLoginAndName(login,password)!=null;
//    }

    @Override
    public User getUserByLoginAndName(String login, String password) {
        return users.values().stream()
                .filter(users -> users.getLogin().compareTo(login) == 0 && users.getPassword().compareTo(password) == 0)
                .findAny()
                .orElse(null);
    }
}
