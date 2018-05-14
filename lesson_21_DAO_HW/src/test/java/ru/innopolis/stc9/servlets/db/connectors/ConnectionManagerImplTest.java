package ru.innopolis.stc9.servlets.db.connectors;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionManagerImplTest extends Assert{
    private Logger logger = Logger.getLogger(ConnectionManagerImplTest.class);
    private ConnectionManagerImpl connectionManager;

    @Before
    public void setUp() throws Exception {
        connectionManager = new ConnectionManagerImpl();
    }

    /**
     * Проверяем, что есть ненуловое соединение с БД
     */
    @Test
    public void getConnection() {
        logger.info("Test: get connection with database.");
        Assert.assertNotNull(connectionManager.getConnection());
    }
}