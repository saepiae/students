package ru.innopolis.stc9.servlets.db.dao;

import ru.innopolis.stc9.servlets.pojo.Participant;
import ru.innopolis.stc9.servlets.pojo.Role;

public interface ParticipantDAO {
    Participant findByName(String firstName, String lastName, String patronymic);
    Participant findByUser(int user);
    int upgradeParticipant(Participant participant, int user, String email, String phone);
    Role getRole(int item);
}
