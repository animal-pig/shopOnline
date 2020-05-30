package service;


import entity.User;
import reply.Response;

import java.util.List;

public interface UserService {
    User getUserById(int id);

    User getUserByNameOrEmail(String nameOrEmail);

    void addUser(User user);

    Response deleteUser(int id);

    boolean updateUser(User user);

    List<User> getAllUser();
}
