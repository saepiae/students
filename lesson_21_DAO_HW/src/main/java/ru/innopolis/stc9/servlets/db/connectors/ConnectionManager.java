package ru.innopolis.stc9.servlets.db.connectors;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
