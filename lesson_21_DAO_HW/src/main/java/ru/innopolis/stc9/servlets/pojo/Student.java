package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.util.Objects;

public class Student {
    static final Logger logger = Logger.getLogger(Student.class);
    private int id;
    private int participantItem;
    private int teamItem;

    public Student(int id, int userItem, int teamItem) {
        this.id = id;
        this.participantItem = userItem;
        this.teamItem = teamItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParticipantItem() {
        return participantItem;
    }

    public void setParticipantItem(int participantItem) {
        this.participantItem = participantItem;
    }

    public int getTeamItem() {
        return teamItem;
    }

    public void setTeamItem(int teamItem) {
        this.teamItem = teamItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                participantItem == student.participantItem &&
                teamItem == student.teamItem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, participantItem, teamItem);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", participantItem=" + participantItem +
                ", teamItem=" + teamItem +
                '}';
    }
}
