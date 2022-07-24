package dao;

import pojo.User;

import java.util.List;

public interface UserDao {
    User selectByID(int id);
    List<User> getUserList();
}
