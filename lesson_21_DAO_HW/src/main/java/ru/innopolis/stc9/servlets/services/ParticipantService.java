package ru.innopolis.stc9.servlets.services;

import ru.innopolis.stc9.servlets.pojo.Participant;

public interface ParticipantService {
    RegisteredStatus userStatus(Participant participant);
    int refreshParticipant(Participant participant, int user, String email, String phone);
    Participant findParticipant(String firstName, String lastName, String patronymic);
    Participant findParticipantByUser(int user);
}
