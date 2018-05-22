package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.util.Objects;

/**
 * Группа
 */
public class Team {
    static final Logger logger = Logger.getLogger(Team.class);
    private int id;
    private String group;
    private String specialty;

    public Team(int id, String group, String specialty) {
        this.id = id;
        this.group = group;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(group, team.group) &&
                Objects.equals(specialty, team.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, specialty);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", group='" + group + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
