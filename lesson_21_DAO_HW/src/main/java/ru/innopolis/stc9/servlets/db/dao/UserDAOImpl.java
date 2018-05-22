package ru.innopolis.stc9.servlets.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManager;
import ru.innopolis.stc9.servlets.db.connectors.ConnectionManagerImpl;
import ru.innopolis.stc9.servlets.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private static final String START = "start";

    @Override
    public int add(User user) {
        logger.debug(START);
        int id = 0;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO users (participant_item, login, password) VALUES (?,?,?) returning participant_item;");
        ) {
            st.setInt(1, user.getParticipantItem());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("participant_item");
                }
                logger.info("found " + resultSet.getRow() + " user(s)");
            }
        } catch (SQLException e) {
            logger.error("failed insert: " + e.getMessage());
        }
        return id;
    }

    @Override
    public User get(String login) {
        logger.debug(START);
        User user = null;
        if (login != null) {
            try (Connection connection = connectionManager.getConnection();
                 PreparedStatement st = connection.prepareStatement(
                         "SELECT * FROM users WHERE login=?;"
                 )) {
                st.setString(1, login);
                user = parseFromTable(st);
            } catch (SQLException e) {
                logger.error("failed insert: " + e.getMessage());
            }
        }
        return user;
    }

    @Override
    public User getByParticipantId(int id) {
        logger.debug(START);
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM users WHERE participant_item=?;"
             )) {
            st.setInt(1, id);
            user = parseFromTable(st);
        } catch (SQLException e) {
            logger.error("failed insert: " + e.getMessage());
        }
        return user;
    }

    /**
     * Парсим объект из таблицы БД.
     *
     * @param st
     * @return
     */
    private User parseFromTable(PreparedStatement st) {
        User user = null;
        logger.debug(START);
        try (ResultSet resultSet = st.executeQuery()) {
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("participant_item"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
            logger.info("found " + resultSet.getRow() + " user(s)");
        } catch (SQLException e) {
            logger.error("failed parsing: " + e.getMessage());
        }
        return user;
    }


}
