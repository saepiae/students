package ru.innopolis.stc9.servlets.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManager;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManagerImpl;
import ru.innopolis.stc9.servlets.pojo.Participant;
import ru.innopolis.stc9.servlets.pojo.Role;
import ru.innopolis.stc9.servlets.pojo.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDaoImp implements TeamDao {
    static final Logger logger = Logger.getLogger(TeamDaoImp.class);
    private static final String START = "Start";
    private static final String FINISH = "End";
    private static final String INF = " object(s) of Team";
    private ConnectionManager connectionManager; // = new ConnectionManagerImpl();

    @Override
    public Participant getTeacher(int id) {
        String sql = "SELECT * FROM participants INTER JOIN teams t ON INTER.id = t.teacher WHERE t.id=?";
        return getParticipant(id, sql);
    }

    @Override
    public Participant getCurator(int id) {
        String sql = "SELECT * FROM participants INTER JOIN teams t ON INTER.id = t.curator WHERE t.id=?";
        return getParticipant(id, sql);
    }

    public Participant getParticipant(int id, String sql) {

        logger.debug(START);
        Participant participant = null;

        connectionManager = new ConnectionManagerImpl();
        try (Connection con = connectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    participant = new Participant(
                            rs.getInt("id"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("patronymic"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            Role.valueOf(rs.getObject("role").toString())
                    );
                }
                logger.info(rs.getRow() + INF);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug(FINISH);
        return participant;
    }

    @Override
    public Team getByParticipantId(int id) {
        String sql = "SELECT t.id, t.group_number, t.specialty FROM participants p INNER JOIN students s ON p.id = s.student\n" +
                "INNER JOIN teams t ON s.team = t.id WHERE p.id=?;";
        ArrayList<Team> list = getTeamsByUser(id, sql);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Team> getTeamsOfCurator(int id) {
        String sql = "SELECT * FROM teams WHERE curator=?;";
        ArrayList<Team> list = getTeamsByUser(id, sql);
        logger.info("found " + list.size() + INF);
        return list;
    }

    @Override
    public List<Team> getTeamsOfTeacher(int id) {
        String sql = "SELECT * FROM teams WHERE teacher=?;";
        ArrayList<Team> list = getTeamsByUser(id, sql);
        logger.info("found " + list.size() + INF);
        return list;
    }

    /**
     * Выборка по sql запросу
     *
     * @param id
     * @param sql
     * @return
     */
    private ArrayList<Team> getTeamsByUser(int id, String sql) {
        logger.debug(START);
        connectionManager = new ConnectionManagerImpl();
        ArrayList<Team> list = new ArrayList<>();
        if (id > 0) {
            try (Connection con = connectionManager.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        list.add(parseTableTeams(rs));
                    }
                    logger.info(rs.getRow() + INF);
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            logger.debug(FINISH);
        }
        return list;
    }

    /**
     * Парсим таблицу БД в объект группа
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Team parseTableTeams(ResultSet rs) throws SQLException {
        return new Team(
                rs.getInt("id"),
                rs.getString("group_number"),
                rs.getString("specialty")
        );
    }
}
