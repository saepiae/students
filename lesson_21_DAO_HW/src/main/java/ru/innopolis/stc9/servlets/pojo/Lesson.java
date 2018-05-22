package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.util.Objects;

public class Lesson {
    final static Logger logger = Logger.getLogger(Lesson.class);
    private int id;
    private int scheduleItem;
    private int teacher;
    private String homework;

    public Lesson(int id, int teacherItem, int scheduleItem, String homework) {
        this.id = id;
        this.teacher = teacher;
        this.scheduleItem = scheduleItem;
        this.homework = homework;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public int getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(int scheduleItem) {
        this.scheduleItem = scheduleItem;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                scheduleItem == lesson.scheduleItem &&
                Objects.equals(teacher, lesson.teacher) &&
                Objects.equals(homework, lesson.homework);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, teacher, scheduleItem, homework);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", scheduleItem=" + scheduleItem +
                ", homework='" + homework + '\'' +
                '}';
    }
}
