package ru.innopolis.stc9.servlets.services;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.db.dao.ScheduleDAO;
import ru.innopolis.stc9.servlets.db.dao.ScheduleDAOImpl;
import ru.innopolis.stc9.servlets.pojo.Schedule;
import ru.innopolis.stc9.servlets.pojo.Student;

import java.util.ArrayList;
import java.util.Collections;

public class ScheduleServiceImpl implements ScheduleService {
    static final Logger logger = Logger.getLogger(ParticipantServiceImpl.class);
    private static final String START = "start";
    private ScheduleDAO scheduleDAO;

    @Override
    public ArrayList<Schedule> getByStudent(Student student) {
        logger.debug(START);
        scheduleDAO = new ScheduleDAOImpl();
        ArrayList<Schedule> list = scheduleDAO.get(student.getTeamItem());
        logger.info("success? " + (list.size() > 0));
        Collections.sort(list, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        logger.debug("sort " + (list.size() > 0));
        return list;
    }
}
