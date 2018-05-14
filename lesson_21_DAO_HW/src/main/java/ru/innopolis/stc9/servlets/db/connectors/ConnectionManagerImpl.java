package ru.innopolis.stc9.servlets.db.connectors;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
    private static Logger logger = Logger.getLogger(ConnectionManagerImpl.class);
    private Connection connection;


    public ConnectionManagerImpl() {
        start();
    }

    /**
     * Подключение к БД
     * @return соединение с конкретной БД
     */
    @Override
    public Connection getConnection() {
        return connection;
    }

    private void start() {
        logger.debug("before");
        try {
            Class.forName("org.postgresql.Driver");
            logger.debug("ClassLoader - success.");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        try {connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/own",
                "postgres",
                "123456789");
            logger.debug("Connection - success.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
