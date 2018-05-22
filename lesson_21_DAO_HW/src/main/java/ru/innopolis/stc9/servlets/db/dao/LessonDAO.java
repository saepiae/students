package ru.innopolis.stc9.servlets.db.dao;

import ru.innopolis.stc9.servlets.pojo.Lesson;
import ru.innopolis.stc9.servlets.pojo.Team;

import java.util.List;

public interface LessonDAO {
    List<Lesson> getLessonsByTeam(Team team);
//    List<Participant> getTeachers(Team team);
}
