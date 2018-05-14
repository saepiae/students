package ru.innopolis.stc9.servlets.db.dao;

import ru.innopolis.stc9.servlets.pojo.User;

public interface UserDAO {

    int add(User user);
    User get(String login);

////    boolean update(User oldUser, User newUser);
//
//    boolean delete(User user);
//
////    User get(String login, String password);
//    User get(String login);
////    ArrayList<User> allWithRole(Role role);
}
