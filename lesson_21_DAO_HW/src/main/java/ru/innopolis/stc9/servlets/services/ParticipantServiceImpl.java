package ru.innopolis.stc9.servlets.services;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.db.dao.ParticipantDAO;
import ru.innopolis.stc9.servlets.db.dao.ParticipantDAOImp;
import ru.innopolis.stc9.servlets.pojo.Participant;

public class ParticipantServiceImpl implements ParticipantService {
    static final Logger logger = Logger.getLogger(ParticipantServiceImpl.class);
    private static final String START = "start";
    ParticipantDAO participantDAO;

    @Override
    public RegisteredStatus userStatus(Participant participant) {
        logger.debug(START);
        RegisteredStatus status = RegisteredStatus.UNVERIFIABLE;
        if (participant != null) {
            status = participant.getUser()==0 && participant.getEmail()==null?
                    RegisteredStatus.UNREGISTERED:RegisteredStatus.REGISTERED;
        }
        logger.info("Success? "+(status!=RegisteredStatus.UNVERIFIABLE));
        return status;
    }

    @Override
    public Participant findParticipant(String firstName, String lastName, String patronymic) {
        logger.debug(START);
        participantDAO = new ParticipantDAOImp();
        Participant participant = participantDAO.findByName(firstName, lastName, patronymic);
        logger.info("Success? "+(participant!=null));
        return participant;
    }
    @Override
    public Participant findParticipantByUser(int user) {
        logger.debug(START);
        participantDAO = new ParticipantDAOImp();
        Participant participant = participantDAO.findByUser(user);
        logger.info("Success? "+(participant!=null));
        return participant;
    }

    @Override
    public int refreshParticipant(Participant participant, int user, String email, String phone) {
        logger.debug(START);
        int result = 0;
        participantDAO = new ParticipantDAOImp();
        result = participantDAO.upgradeParticipant(participant, user, email, phone);
        logger.info("Success? "+(result>0));
        return 0;
    }
}
