package ru.innopolis.stc9.servlets.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManager;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManagerImpl;
import ru.innopolis.stc9.servlets.pojo.Participant;
import ru.innopolis.stc9.servlets.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantDAOImp implements ParticipantDAO {
    static final Logger logger = Logger.getLogger(ParticipantDAOImp.class);
    private static final String START = "Start";
    private static final String FINISH = "End";
    private static final String INF = " object(s) of Participant";
    private ConnectionManager connectionManager = new ConnectionManagerImpl();

    @Override
    public Participant findByName(String firstName, String lastName, String patronymic) {
        logger.debug(START);
        Participant participant = null;
        try (Connection con = connectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT * FROM participants WHERE fname= ? AND lname =? AND patronymic=?;")) {
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, patronymic);
            try (ResultSet rs = pst.executeQuery()){
                if (rs.next()) {
                    participant = getParticipant(rs);
                }
                logger.info(rs.getRow() + INF);
            }catch (SQLException e){
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug(FINISH);
        return participant;
    }

    @Override
    public Participant findByUser(int user) {
        logger.debug(START);
        Participant participant = null;
        try (Connection con = connectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT * FROM participants WHERE item_user=?;")) {
            pst.setInt(1, user);
            try (ResultSet rs = pst.executeQuery()){
                if (rs.next()) {
                    participant = getParticipant(rs);
                }
                logger.info(rs.getRow() + INF);
            }catch (SQLException e){
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug(FINISH);
        return participant;
    }

    private Participant getParticipant(ResultSet rs) throws SQLException {
        return new Participant(
                rs.getInt("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("patronymic"),
                rs.getString("email"),
                rs.getString("phone"),
                Role.valueOf(rs.getObject("role").toString()),
                rs.getInt("item_user")
        );
    }

    @Override
    public int upgradeParticipant(Participant participant, int user, String email, String phone) {
        logger.debug(START);
        int result = 0;
        try (Connection con = connectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "UPDATE participants SET item_user=?, email=?, phone=? WHERE id=? RETURNING id;")) {
            pst.setInt(1, user);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setInt(4, participant.getId());
            try (ResultSet rs = pst.executeQuery()){
                if (rs.next()) {
                    result = rs.getInt("id");
                }
                logger.info(rs.getRow() + INF);
            }catch (SQLException e){
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug(FINISH);
        return result;
    }

    @Override
    public Role getRole(int item) {
        logger.debug(START);
        Role result = Role.NO_SUCH_USER;
        try (Connection con = connectionManager.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT role FROM participants WHERE item_user = ?;")) {
            pst.setInt(1, item);
            try (ResultSet rs = pst.executeQuery()){
                if (rs.next()) {
                    result = Role.valueOf(rs.getString("role"));
                }
                logger.info(rs.getRow() + INF);
            }catch (SQLException e){
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.debug(FINISH);
        return result;
    }


}
