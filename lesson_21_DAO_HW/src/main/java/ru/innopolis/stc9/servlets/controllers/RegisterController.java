package ru.innopolis.stc9.servlets.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.pojo.Participant;
import ru.innopolis.stc9.servlets.pojo.User;
import ru.innopolis.stc9.servlets.services.ParticipantService;
import ru.innopolis.stc9.servlets.services.ParticipantServiceImpl;
import ru.innopolis.stc9.servlets.services.UserService;
import ru.innopolis.stc9.servlets.services.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    final static Logger logger = Logger.getLogger(RegisterController.class);
    private static final int MIN_PASSWORD_LENGTH = 2;
    private static final String START = "start";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(START);
        req.setAttribute("message", getMessage(req));
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
        info(true);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(START);
        req.setCharacterEncoding("UTF-8");
        String firstName = req.getParameter("first name");
        String lastName = req.getParameter("last name");
        String patronymic = req.getParameter("patronymic");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String login = req.getParameter("login");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (isCorrectInputs(firstName, lastName, patronymic, email, phone, login, password1, password2)) {
            ParticipantService participantService = new ParticipantServiceImpl();
            Participant participant = participantService.findParticipant(firstName, lastName, patronymic);

            if (participant != null) {
                UserService userService = new UserServiceImp();
                User user = userService.getByParticipantId(participant.getId());
                if (user != null) {
                    resp.sendRedirect(req.getContextPath() + "/register?act=recovery");
                } else {
                    int id = participantService.refreshParticipant(participant, email, phone);
                    userService.addUser(id, login, password1);
                    if (id > 0) {
                        req.getSession().setAttribute("participant", participant);
                    } else {
                        logger.warn("данные не обновились");
                    }
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/register?errorMsg=moderErr");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/register?errorMsg=wrongForm");
        }
    }

    private boolean isCorrectInputs(String firstName, String lastName, String patronymic,
                                    String email, String phone, String login, String password, String password2) {
        logger.debug(START);
        boolean result = validateString(firstName) &&
                validateString(lastName) &&
                validateString(patronymic, 0) &&
                validateString(email, 3) &&
                validateString(phone, 0) &&
                validateString(login) &&
                validateString(password, MIN_PASSWORD_LENGTH) &&
                validateString(password2, MIN_PASSWORD_LENGTH) &&
                password.equals(password2);
        info(result);
        return result;
    }

    private boolean validateString(String s, int minLength) {
        return s != null && s.length() >= minLength;
    }

    private boolean validateString(String s) {
        return validateString(s, 1);
    }

    private String getMessage(HttpServletRequest req) {
        String message;
        if (req.getParameter("errorMsg") != null) {
            switch (req.getParameter("errorMsg")) {
                case ("modError"):
                    message = "Ошибка модерации, свяжитесь с деканатом";
                    break;
                case ("noAuth"):
                    message = "Необходимо пройти идентификацию";
                    break;
                default:
                    message = "Не понятненько...";
            }
        } else {
            if (req.getParameter("action") != null) {
                switch (req.getParameter("action")) {
                    case ("passRecovery"):
                        message = "На вашу почту выслали временный пароль";
                        break;
                    default:
                        message = "Очень даже не понятненько...";
                }
            } else {
                message = "Укажите свои данные";
            }
        }
        return message;
    }

    private void debud(String s) {
        logger.debug(s);
    }

    private void info(boolean b) {
        logger.info("Success? " + b);
    }
}
