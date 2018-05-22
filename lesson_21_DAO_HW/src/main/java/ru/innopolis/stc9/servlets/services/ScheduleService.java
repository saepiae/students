package ru.innopolis.stc9.servlets.services;

import ru.innopolis.stc9.servlets.pojo.Schedule;
import ru.innopolis.stc9.servlets.pojo.Student;

import java.util.ArrayList;

public interface ScheduleService {
    /**
     * Расписание занятия группы.
     *
     * @param student
     * @return
     */
    ArrayList<Schedule> getByStudent(Student student);
}
