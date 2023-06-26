package org.crypto.training;

import org.crypto.training.model.User;
import org.crypto.training.repository.UserJDBCDaoImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserJDBCDaoImpl userDao = new UserJDBCDaoImpl();
        List<User> users = userDao.getUsers();
        System.out.format("List user %s", users);
    }
}
