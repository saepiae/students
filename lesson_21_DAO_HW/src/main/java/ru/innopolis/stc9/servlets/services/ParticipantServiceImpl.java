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
    public int refreshParticipant(Participant participant, String email, String phone) {
        logger.debug(START);
        int result = 0;
        participantDAO = new ParticipantDAOImp();
        result = participantDAO.upgradeParticipant(participant, email, phone);
        logger.info("Success? "+(result>0));
        return result;
    }
}
