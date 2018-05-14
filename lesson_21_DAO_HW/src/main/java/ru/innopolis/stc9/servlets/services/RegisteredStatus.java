package ru.innopolis.stc9.servlets.services;

public enum RegisteredStatus {
    UNREGISTERED("Незарегистрированный пользователь"),
    REGISTERED("Такой пользователь уже есть. Восстановите пароль"),
    UNVERIFIABLE("Не пройдена модерация");
    private final String description;

    RegisteredStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
