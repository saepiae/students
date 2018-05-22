package ru.innopolis.stc9.servlets.services.components;

import ru.innopolis.stc9.servlets.pojo.Team;

import java.time.LocalDate;

public class TeacherSchedule {
    private LocalDate date;
    private Team team;
    private String theme;

    public TeacherSchedule(LocalDate date, Team team, String theme) {
        this.date = date;
        this.team = team;
        this.theme = theme;
    }
}
