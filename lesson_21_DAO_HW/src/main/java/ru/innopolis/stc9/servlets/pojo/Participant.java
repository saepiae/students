package ru.innopolis.stc9.servlets.pojo;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;

/**
 * Таблица участников учебного процесса
 */
public class Participant implements Serializable{
    static final Logger logger = Logger.getLogger(Participant.class);
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phone;
    private Role role;
    private int user;

    public Participant(int id, String firstName, String lastName, String patronymic,
                       String email, String phone, Role role, int user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public int getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        boolean result = id == that.id &&
                user == that.user &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                role == that.role;
        logger.debug("result = " + result);
        return result;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, lastName, patronymic, email, phone, role, user);
        logger.debug("hashcode = " + result);
        return result;
    }

    @Override
    public String toString() {
        String result = "Participant{" + id +
                ", " + firstName +
                ", " + lastName +
                ", " + patronymic +
                ", " + email +
                ", " + phone +
                ", " + role +
                ", " + user +
                '}';
        logger.debug("result = "+result);
        return result;
    }
}
