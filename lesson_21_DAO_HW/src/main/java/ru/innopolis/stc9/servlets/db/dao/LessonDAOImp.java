package ru.innopolis.stc9.servlets.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManager;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManagerImpl;
import ru.innopolis.stc9.servlets.pojo.Lesson;
import ru.innopolis.stc9.servlets.pojo.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAOImp implements LessonDAO {
    static final Logger logger = Logger.getLogger(LessonDAOImp.class);
    private static final String START = "Start";
    private static final String FINISH = "End";
    private static final String INF = " object(s) of Person";
    private ConnectionManager connectionManager = new ConnectionManagerImpl();

    @Override
    public List<Lesson> getLessonsByTeam(Team team) {
        logger.debug(START);
        List<Lesson> lessons = new ArrayList<>();
        try (Connection con = connectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT * FROM lessons l INNER JOIN schedules s2 ON l.schedule = s2.id WHERE team = ?;")) {
            pst.setInt(1, team.getId());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Lesson lesson = new Lesson(
                            rs.getInt("id"),
                            rs.getInt("teacher"),
                            rs.getInt("schedule"),
                            rs.getString("homework")
                    );
                    lessons.add(lesson);
                }
                logger.info(rs.getRow() + INF);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug(FINISH);
        return lessons;
    }

//    @Override
//    public List<Participant> getTeachers(Team team) {
//        logger.debug(START);
//        Set<Participant> teachers = new HashSet<>();
//        try (Connection con = connectionManager.getConnection();
//             PreparedStatement pst = con.prepareStatement(
//                     "SELECT * FROM lessons l INNER JOIN schedules s2 ON l.schedule = s2.id WHERE team = ?;")) {
//            pst.setInt(1, user);
//            try (ResultSet rs = pst.executeQuery()){
//                if (rs.next()) {
//                    participant = getParticipant(rs);
//                }
//                logger.info(rs.getRow() + INF);
//            }catch (SQLException e){
//                logger.error(e.getMessage());
//            }
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//        }
//        logger.debug(FINISH);
//        return participant;
//
//        return null;
//    }
}
