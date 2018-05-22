package ru.innopolis.stc9.servlets.services;

import ru.innopolis.stc9.servlets.pojo.Role;
import ru.innopolis.stc9.servlets.pojo.User;

public interface UserService {
    int addUser(int participant, String loginHash, String passwordHash);
    int checkAuth(String login, String password);

    User getByParticipantId(int i);

    Role getUserRole(int i);
}
