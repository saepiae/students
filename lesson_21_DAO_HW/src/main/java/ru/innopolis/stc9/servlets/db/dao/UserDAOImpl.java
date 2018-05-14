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

    @Override
    public int add(User user) {
        logger.debug("start");
        int id = 0;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO users (login, password) VALUES (?,?) RETURNING id;");
        ) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
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
        logger.debug("start");
        User user =null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM users WHERE login=?;"
             )){
            st.setString(1, login);
            try (ResultSet resultSet = st.executeQuery()){
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                    resultSet.getString("login"),
                            resultSet.getString("password")
                    );
                }
                logger.info("found " + resultSet.getRow() + " user(s)");
            }
        }catch (SQLException e){
            logger.error("failed insert: " + e.getMessage());
        }
        return user;
    }


}
