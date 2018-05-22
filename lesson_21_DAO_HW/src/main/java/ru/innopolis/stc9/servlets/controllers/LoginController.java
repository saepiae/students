package ru.innopolis.stc9.servlets.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.pojo.Participant;
import ru.innopolis.stc9.servlets.pojo.Role;
import ru.innopolis.stc9.servlets.pojo.User;
import ru.innopolis.stc9.servlets.services.*;
import ru.innopolis.stc9.servlets.services.builder.BuilderStudent;
import ru.innopolis.stc9.servlets.services.builder.BuilderTeacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private static final int MIN_PASSWORD_LENGTH = 2;
    private static Logger logger = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if ("out".equals(action)) {
            req.getSession().invalidate();
        }
        req.setAttribute("message", "instruct");
        try {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("name");
        String password = req.getParameter("password");
        try {
            validate(req, resp, login, password);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Примитивная проверка
     *
     * @param resp
     * @param login
     * @param password
     * @throws IOException
     */
    private void validate(HttpServletRequest req, HttpServletResponse resp, String login, String password) throws IOException {
        if (login != null && password != null && password.length() >= MIN_PASSWORD_LENGTH) {
            UserService userService = new UserServiceImp();
            int id = userService.checkAuth(login, password);
            if (id > 0) {
                User user = userService.getByParticipantId(id);
                req.getSession().setAttribute("user", user);
                ParticipantService participantService = new ParticipantServiceImpl();
                Participant participant = participantService.findParticipantByUser(id);
                // TODO: 15.05.2018 переделать на participant
                Role role = userService.getUserRole(id);
                req.getSession().setAttribute("role", role);
                Director director = new Director();
                switch (role) {
                    case STUDENT:
                        BuilderStudent student = new BuilderStudent();
                        director.createStudent(student, user);
                        req.getSession().setAttribute("data", student);
                        break;
                    case TEACHER:
                        BuilderTeacher teacher = new BuilderTeacher();
                        director.createTeacher(teacher, user);
                        req.getSession().setAttribute("data", teacher);
                        break;
                    case ADMIN:
                        // TODO: 21.05.2018 сделать логику для админа
                        break;
                    default:
                        // TODO: 21.05.2018 придумать тут что делать
                }
//                req.getSession().setAttribute("participant", participant);
//                req.getSession().setAttribute("userFName",participant.getFirstName());
//                req.getSession().setAttribute("userLName",participant.getLastName());
                resp.sendRedirect(req.getContextPath() + "/inner/schedule");
            } else {
                logger.debug(req.getContextPath());
                resp.sendRedirect(req.getContextPath() + "/login?errorMsg=authError");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login?errorMsg=invData");
        }
    }
}
