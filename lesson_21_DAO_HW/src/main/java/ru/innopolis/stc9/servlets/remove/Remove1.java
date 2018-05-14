package ru.innopolis.stc9.servlets.remove;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.services.UserService;
import ru.innopolis.stc9.servlets.services.UserServiceImp;

public class Remove1 {
    private static Logger logger = Logger.getLogger(Remove1.class);
    public static void main(String[] args) {
        logger.info("hi!");
        UserService service = new UserServiceImp();
        int result = 0;
//        result = service.addUser("u1","p1");
//        service = new UserServiceImp();
//        result = service.addUser("u2","p2");
//        service = new UserServiceImp();
//        result = service.addUser("u3","p3");
        service = new UserServiceImp();
        int check = service.checkAuth("u1","p1");


        logger.info(check>0);

    }
}
