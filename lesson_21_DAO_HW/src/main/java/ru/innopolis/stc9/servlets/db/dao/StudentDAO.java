package ru.innopolis.stc9.servlets.db.dao;

import ru.innopolis.stc9.servlets.pojo.Team;

public interface StudentDAO {
    Team findStudentTeam(int userId);
}
