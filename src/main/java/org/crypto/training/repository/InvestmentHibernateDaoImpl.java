package org.crypto.training.repository;

import org.crypto.training.model.Investment;
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
public class InvestmentHibernateDaoImpl implements IInvestmentDao {

    private static final Logger logger = LoggerFactory.getLogger(AssetHibernateDaoImpl.class);


    @Override
    public void save(Investment investment) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(investment);
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            logger.error("unable to save user or unable to close session", e);
        }
    }

    @Override
    public List<Investment> getInvestments() {
        logger.info("Start to getAsset from Postgres via Hibernate.");

        List<Investment> investments = new ArrayList<>();// prepare model

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); // establish connection

        try {
            Session session = sessionFactory.openSession();

            String hql = "from Investment "; // execute query
            Query<Investment> query = session.createQuery(hql);

            investments = query.list();// extract data

            session.close(); // close session
        } catch (HibernateException e) {
            logger.error("Open session exception or close exception", e);
        }
        logger.info("Ger investments {}", investments);
        return investments;
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Investment investment) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(investment);
            transaction.commit();
            session.close();
        }catch (HibernateException e) {
            logger.error("unable to delete investment or close session", e);
        }
        return true;

    }
}
