package ru.innopolis.stc9.servlets.db.connectors;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        logger.info("Test: getByStudent connection with database.");
        Assert.assertNotNull(connectionManager.getConnection());
    }
}