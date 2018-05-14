package ru.innopolis.stc9.servlets.services;

import ru.innopolis.stc9.servlets.pojo.Role;

public interface UserService {
    int addUser(String loginHash, String passwordHash);
    int checkAuth(String login, String password);
    Role getRole(int id);
}
