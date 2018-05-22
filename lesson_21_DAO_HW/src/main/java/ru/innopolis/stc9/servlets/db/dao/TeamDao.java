package ru.innopolis.stc9.servlets.db.dao;

import ru.innopolis.stc9.servlets.pojo.Participant;
import ru.innopolis.stc9.servlets.pojo.Team;

import java.util.List;

public interface TeamDao {
    /**
     * Ищем группу студента
     *
     * @param id
     * @return
     */
    Team getByParticipantId(int id);

    /**
     * Ищем все группы куратора
     *
     * @param id
     * @return
     */
    List<Team> getTeamsOfCurator(int id);

    /**
     * Ищем все группы преподавателя
     *
     * @param id
     * @return
     */
    List<Team> getTeamsOfTeacher(int id);

    Participant getTeacher(int id);

    Participant getCurator(int id);
}
