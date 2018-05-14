package ru.innopolis.stc9.servlets.pojo;

/**
 * Функция участника в учебном процессе.
 */
public enum Role {
    NO_SUCH_USER("Пользователь не найден"),
    STUDENT("Студент"),
    TEACHER("Преподаватель"),
    ADMIN("Администратор БД или сотрудник деканата");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
