package ru.innopolis.stc9.servlets.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManager;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManagerImpl;
import ru.innopolis.stc9.servlets.pojo.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    static final Logger logger = Logger.getLogger(ScheduleDAOImpl.class);
    private static final String START = "Start";
    private static final String FINISH = "End";
    private static final String INF = " object(s) of Schedule";
    private ConnectionManager connectionManager = new ConnectionManagerImpl();

    @Override
    public ArrayList<Schedule> get(int team) {
        logger.debug(START);
        ArrayList<Schedule> list = new ArrayList<>();
        try (Connection con = connectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT * FROM schedules WHERE team=?;")) {
            pst.setInt(1, team);
            try (ResultSet rs = pst.executeQuery()) {
                Schedule schedule = null;
                while (rs.next()) {
                    schedule = new Schedule(
                            rs.getInt("id"),
                            rs.getInt("team"),
                            rs.getDate("date").toLocalDate(),
                            rs.getString("lesson_theme")
                    );
                    if (schedule != null) {
                        list.add(schedule);
                    }
                }
                logger.info(rs.getRow() + INF);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug(FINISH);
        return list;
    }
}
