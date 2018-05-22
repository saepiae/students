package ru.innopolis.stc9.servlets.services.builder;

import ru.innopolis.stc9.servlets.db.dao.*;
import ru.innopolis.stc9.servlets.pojo.*;

import java.util.List;
import java.util.Map;

public class BuilderTeacher implements Builder {
    private Participant participant;
    private List<Team> teams;
    private Map<Team, List<Schedule>> schedules;
    private Map<Team, List<Lesson>> lessons;

    @Override
    public void setPerson(User user) {
        int userId = user.getParticipantItem();
        ParticipantDAO participantDAO = new ParticipantDAOImp();
        participant = participantDAO.findByUser(userId);
    }

    @Override
    public void setTeams() {
        int userId = participant.getId();
        TeamDao teamDao = new TeamDaoImp();
        teams = teamDao.getTeamsOfTeacher(userId);
    }

    @Override
    public void setScheldule() {
        for (Team t : teams) {
            ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
            schedules.put(t, scheduleDAO.get(t.getId()));
        }
    }

    @Override
    public void setLessons() {
        for (Team t : teams) {
            LessonDAO lessonDAO = new LessonDAOImp();
            lessons.put(t, lessonDAO.getLessonsByTeam(t));
        }
    }
}
