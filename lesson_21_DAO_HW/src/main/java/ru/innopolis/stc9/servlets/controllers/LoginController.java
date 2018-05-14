package ru.innopolis.stc9.servlets.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.pojo.Participant;
import ru.innopolis.stc9.servlets.services.ParticipantService;
import ru.innopolis.stc9.servlets.services.ParticipantServiceImpl;
import ru.innopolis.stc9.servlets.services.UserService;
import ru.innopolis.stc9.servlets.services.UserServiceImp;

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
        if ("out".equals(action)){
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
                req.getSession().setAttribute("user", id);
                ParticipantService participantService = new ParticipantServiceImpl();
                Participant participant = participantService.findParticipantByUser(id);
                req.getSession().setAttribute("userFName",participant.getFirstName());
                req.getSession().setAttribute("userLName",participant.getLastName());
                req.getSession().setAttribute("role", userService.getRole(id));
                resp.sendRedirect(req.getContextPath() + "/inner/schedule");
            } else {
                logger.debug(req.getContextPath());
                resp.sendRedirect(req.getContextPath() + "/login?errorMsg=authError");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login?errorMsg=invData");
        }
    }

    /**
     * По роли участника определяем, куда его перенаправить.
     * @param userService
     * @param id
     * @return
     */
    private String selectRedirect(UserService userService, int id) {
        String result;
        switch (userService.getRole(id)) {
            case ADMIN:
                result = "/admin/dashboard";
                break;
            case TEACHER:
                result = "/teacher/dashboard";
                break;
            case STUDENT:
                result = "/student/dashboard";
                break;
            default:
                logger.warn("something went wrong");
        }
        result = "/dashboard?errorMsg=noUser";
        logger.info(result);
        return result;
    }
}
