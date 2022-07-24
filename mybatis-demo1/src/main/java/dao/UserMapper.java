package dao;

import pojo.User;

import java.util.List;

public interface UserMapper {
    User selectByID(int id);

    List<User> getUserList();

    int addUser(User user);

    int updateUser(User user);
}
