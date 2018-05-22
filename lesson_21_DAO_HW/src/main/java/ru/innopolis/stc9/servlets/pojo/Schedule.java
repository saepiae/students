package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public class Schedule {
    static final Logger logger = Logger.getLogger(Schedule.class);
    private int item;
    private int teamItem;
    private LocalDate date;
    private String theme;

    public Schedule(int item, int teamItem, LocalDate date, String theme) {
        this.item = item;
        this.teamItem = teamItem;
        this.date = date;
        this.theme = theme;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getTeamItem() {
        return teamItem;
    }

    public void setTeamItem(int teamItem) {
        this.teamItem = teamItem;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return item == schedule.item &&
                teamItem == schedule.teamItem &&
                Objects.equals(date, schedule.date) &&
                Objects.equals(theme, schedule.theme);
    }

    @Override
    public int hashCode() {

        return Objects.hash(item, teamItem, date, theme);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "item=" + item +
                ", teamItem=" + teamItem +
                ", date=" + date +
                ", theme='" + theme + '\'' +
                '}';
    }
}
