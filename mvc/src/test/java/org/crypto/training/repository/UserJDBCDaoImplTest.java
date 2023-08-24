package org.crypto.training.repository;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UserJDBCDaoImplTest {
    @Test
    public void getUserTest() {
        UserJDBCDaoImpl userDao = new UserJDBCDaoImpl();
        assertEquals(0, userDao.getUsers().size());
    }
}
