package ru.innopolis.stc9.servlets.services;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ru.innopolis.stc9.servlets.db.dao.ParticipantDAO;
import ru.innopolis.stc9.servlets.db.dao.ParticipantDAOImp;
import ru.innopolis.stc9.servlets.db.dao.UserDAO;
import ru.innopolis.stc9.servlets.db.dao.UserDAOImpl;
import ru.innopolis.stc9.servlets.pojo.Role;
import ru.innopolis.stc9.servlets.pojo.User;

public class UserServiceImp implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImp.class);
    private UserDAO userDAO = new UserDAOImpl();
    static final String START = "start";
    /**
     * Хэшируем логин и пароль, добавляем в БД.
     * @param login
     * @param password
     * @param participant
     * @return
     */
    @Override
    public int addUser(int participant, String login, String password) {
        logger.debug(START);
        String passwordHash = hide(password);
        User user = new User(participant, login, passwordHash);
        int result = userDAO.add(user);
        logger.info("success? "+ (result>0));
        return result;
    }

    @Override
    public int checkAuth(String login, String password) {
        logger.debug(START);
        int result = 0;
        User user = userDAO.get(login);
        if (user!=null&&BCrypt.checkpw(password, user.getPassword())){
            result = user.getParticipantItem();
        }
        logger.info("success? "+ (result>0));
        return result;
    }

    @Override
    public User getByParticipantId(int id) {
        logger.debug(START);
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getByParticipantId(id);
        logger.info("success? " + (user != null));
        return user;
    }


    @Override
    public Role getUserRole(int id) {
        logger.debug(START);
        ParticipantDAO participantDAO = new ParticipantDAOImp();
        Role role = participantDAO.getRole(id);
        logger.info("success? "+ (role!=Role.NO_SUCH_USER));
        return role;
    }
//
//    @Override
//    public RegisteredStatus registrationStatus(Person participant) {
//        logger.debug(START);
//        RegisteredStatus status = RegisteredStatus.UNVERIFIABLE;
//        if (participant!=null){
//            UserDAO userDAO = new UserDAOImpl();
//            User user = userDAO.getByParticipantId(participant.getId());
//            status = user!=null?RegisteredStatus.UNREGISTERED:RegisteredStatus.REGISTERED;
//        }
//        logger.info("success? "+ (status!=RegisteredStatus.UNVERIFIABLE));
//        return status;
//    }

    private String hide(String plainTextPassword){
        logger.debug(START);
        String result = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        logger.info("success");
        return result;
    }
}
