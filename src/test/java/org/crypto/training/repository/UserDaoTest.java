package org.crypto.training.repository;

import org.crypto.training.model.User;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UserDaoTest {
    @Test
    public void getUserTest() {
        UserDao userDao = new UserDao();
        assertEquals(0, userDao.getUsers().size());
    }
}
