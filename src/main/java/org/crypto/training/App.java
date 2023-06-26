package org.crypto.training;

import org.crypto.training.model.User;
import org.crypto.training.repository.UserDao;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getUsers();
        System.out.format("List user %s", users);
    }
}
