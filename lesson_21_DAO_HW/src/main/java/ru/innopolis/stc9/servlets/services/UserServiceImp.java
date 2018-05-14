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
     * @return
     */
    @Override
    public int addUser(String login, String password) {
        logger.debug(START);
        String passwordHash = hide(password);
        User user = new User(0, login, passwordHash);
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
            result = user.getId();
        }
        logger.info("success? "+ (result>0));
        return result;
    }

    @Override
    public Role getRole(int id) {
        logger.debug(START);
        ParticipantDAO participantDAO = new ParticipantDAOImp();
        Role role = participantDAO.getRole(id);
        logger.info("success? "+ (role!=Role.NO_SUCH_USER));
        return role;
    }

    private String hide(String plainTextPassword){
        logger.debug(START);
        String result = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        logger.info("success");
        return result;
    }
}
