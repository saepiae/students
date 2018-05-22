package ru.innopolis.stc9.servlets.services;

import ru.innopolis.stc9.servlets.pojo.Participant;

public interface ParticipantService {
    int refreshParticipant(Participant participant, String email, String phone);
    Participant findParticipant(String firstName, String lastName, String patronymic);
    Participant findParticipantByUser(int user);
}
