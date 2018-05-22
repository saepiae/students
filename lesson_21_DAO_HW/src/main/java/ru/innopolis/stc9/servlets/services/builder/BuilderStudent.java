package ru.innopolis.stc9.servlets.services.builder;

import ru.innopolis.stc9.servlets.db.dao.*;
import ru.innopolis.stc9.servlets.pojo.*;

import java.util.List;

public class BuilderStudent implements Builder {
    private Participant participant;
    private Team team;
    private Participant teacher;
    private Participant curator;
    private List<Schedule> schedules;
    private List<Lesson> lessons;

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
        team = teamDao.getByParticipantId(userId);
    }

    @Override
    public void setScheldule() {
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        schedules = scheduleDAO.get(team.getId());
    }

    @Override
    public void setLessons() {
        LessonDAO lessonDAO = new LessonDAOImp();
        lessons = lessonDAO.getLessonsByTeam(team);
    }

    public void setTeachers() {
        TeamDao teamDao = new TeamDaoImp();
        teacher = teamDao.getTeacher(team.getId());
    }

    public void setCurator() {
        TeamDao teamDao = new TeamDaoImp();
        curator = teamDao.getTeacher(team.getId());
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}
