package ru.innopolis.stc9.servlets.db.dao;

import ru.innopolis.stc9.servlets.pojo.User;

public interface UserDAO {

    int add(User user);
    User get(String login);

    User getByParticipantId(int id);

////    boolean update(User oldUser, User newUser);
//
//    boolean delete(User user);
//
////    User getByStudent(String login, String password);
//    User getByStudent(String login);
////    ArrayList<User> allWithRole(Role role);
}
