package ru.innopolis.stc9.servlets.services;

import ru.innopolis.stc9.servlets.pojo.User;
import ru.innopolis.stc9.servlets.services.builder.Builder;
import ru.innopolis.stc9.servlets.services.builder.BuilderTeacher;

public class Director {
    public void createStudent(Builder builder, User user) {
//        BuilderStudent student = new BuilderStudent();
        builder.setPerson(user);
        builder.setTeams();
        builder.setScheldule();
        builder.setLessons();
    }

    public void createTeacher(Builder builder, User user) {
        BuilderTeacher teacher = new BuilderTeacher();
        builder.setPerson(user);
        builder.setTeams();
        builder.setScheldule();
        builder.setLessons();
    }
}
