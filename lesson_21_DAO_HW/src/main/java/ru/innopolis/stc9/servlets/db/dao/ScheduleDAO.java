package ru.innopolis.stc9.servlets.db.dao;

import ru.innopolis.stc9.servlets.pojo.Schedule;

import java.util.ArrayList;

public interface ScheduleDAO {
    /**
     * По номеру группы ищем расписание занятий
     *
     * @param team id группы в БД
     * @return
     */
    ArrayList<Schedule> get(int team);
//    Map<Schedule>
}
