package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.util.Objects;

public class User {
    static final Logger logger = Logger.getLogger(User.class);
    private int participantItem;
    private String login;
    private String password;

    public User(int id, String login, String password) {
        this.participantItem = id;
        this.login = login;
        this.password = password;
    }

    public int getParticipantItem() {
        return participantItem;
    }

    public void setParticipantItem(int participantItem) {
        this.participantItem = participantItem;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        boolean result = participantItem == user.participantItem &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
        logger.debug("result = "+result);
        return result;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(participantItem, login, password);
        logger.debug("hashcode = "+result);
        return result;
    }

    @Override
    public String toString() {
        String result =  "User{" +
                "participantItem=" + participantItem +
                ", login='" + login + '\'' +
                ", (double hash) password='" + password.hashCode() + '\'' +
                '}';
        logger.debug("result =  "+result);
        return result;
    }
}
