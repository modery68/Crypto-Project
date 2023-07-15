package org.crypto.training.repository;

import org.crypto.training.model.User;
import org.crypto.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserHibernateDaoImpl implements IUserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserHibernateDaoImpl.class);


    @Override
    public void save(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null; // transaction : error prevention during the database change
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            //`......
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            if (transaction != null)
            logger.error("unable to save user or unable to close session and Rollback", e);
            transaction.rollback();
        }
    }

    @Override
    public List<User> getUsers() {
        logger.info("Start to getUser from Postgres via Hibernate.");

        List<User> users = new ArrayList<>();// prepare model
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); // establish connection

        try {
            Session session = sessionFactory.openSession();

            String hql = "from User"; // execute query // no need to mock basic data type
            Query<User> query = session.createQuery(hql);

            users = query.list();// extract data

            session.close(); // close session
        } catch (HibernateException e) {
            logger.error("Open session exception or close exception", e);
        }
        logger.info("Ger users {}", users);
        return users;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            //.........
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null)
                logger.error("unable to delete user or close session and Rollback", e);
            transaction.rollback();
        }
        return true;

    }


    @Override
    public User getUserEagerBy(Long id) {
        String hql = "FROM User d LEFT JOIN FETCH d.investments where d.id = :Id"; //LEFT JOIN FETCH: HQL里面的left join
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failed to retrieve data record", e);
            session.close();
            return null;
        }
    }

}



