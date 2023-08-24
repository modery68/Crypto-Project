package org.crypto.training.repository;

import org.crypto.training.model.User;
import org.crypto.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.hibernate.query.Query;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserHibernateDaoTest {
    @Mock
    private SessionFactory mockSessionFactory; // since SessionFactory is class from outside
    @Mock
    private Session mockSession;
    @Mock
    private Query mockQuery;

    UserHibernateDaoImpl userDao = new UserHibernateDaoImpl();


    @Test
    public void getUsersTest_happyPath() {

        User user = new User(1, "wang", "abcd", "abc@gmail.com");
        List<User> result = List.of(user);
        
        try (MockedStatic mockedStatic = mockStatic(HibernateUtil.class)) { // use try: mockstatic for line 25 because of static method
            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);

            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(result);
            doNothing().when(mockSession).close(); // use doNothing because of close is void method

            List<User> actualResult = userDao.getUsers();
            assertEquals(result, actualResult);
        }
    }

    @Test
    public void getUsersTest_getHibernateException() {
        User user = new User(1, "wang", "abcd", "abc@gmail.com");
        List<User> result = List.of(user);

        try (MockedStatic mockedStatic = mockStatic(HibernateUtil.class)) {
            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);

            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(result);
            doThrow(HibernateException.class).when(mockSession).close();


            assertThrows(HibernateException.class, () -> userDao.getUsers());
        }
    }
}
