package ru.innopolis.stc9.servlets.services.builder;

import ru.innopolis.stc9.servlets.pojo.User;

public interface Builder {
    void setPerson(User user);

    void setTeams();

    void setScheldule();

    void setLessons();
}
