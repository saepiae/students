package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.util.Objects;

public class User {
    static final Logger logger = Logger.getLogger(User.class);
    /**
     * Суррогатный ключ
     */
    private int id;
    /**
     * логин пользователя (хэш)
     */
    private String login;
    /**
     * пароль пользователя (хэш)
     */
    private String password;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        boolean result = id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
        logger.debug("result = "+result);
        return result;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, login, password);
        logger.debug("hashcode = "+result);
        return result;
    }

    @Override
    public String toString() {
        String result =  "User{" +
                "id=" + id +
                ", (double hash) login='" + login.hashCode() + '\'' +
                ", (double hash) password='" + password.hashCode() + '\'' +
                '}';
        logger.debug("result =  "+result);
        return result;
    }
}
