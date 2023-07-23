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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvestmentHibernateDaoImpl implements IInvestmentDao {

    private static final Logger logger = LoggerFactory.getLogger(AssetHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void save(Investment investment) {

        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(investment);
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            if (transaction != null)
            logger.error("unable to save user or unable to close session", e);
            transaction.rollback();
        }
    }

    @Override
    public List<Investment> getInvestments() {
        logger.info("Start to getAsset from Postgres via Hibernate.");

        List<Investment> investments = new ArrayList<>();// prepare model

        //SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); // establish connection

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
    public Investment getById(Long id) {
        Session s = sessionFactory.openSession();
        String hql = "FROM Investment i where id = :ID";
        //String hql = "FROM Department d JOIN FETCH d.employees where dold=tId"
        try{
            Query<Investment> query = s.createQuery(hql);
            query.setParameter("ID", id);
            Investment result = query.uniqueResult();
            s.close();
            return result;
        }catch(HibernateException e){
            logger.error("Session close exception try again", e);
            s.close();

            return null;
        }
    }

    @Override
    public boolean delete(Investment investment) {
        //SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(investment);
            transaction.commit();
            session.close();
        }catch (HibernateException e) {
            if (transaction != null)
            logger.error("unable to delete investment or close session", e);
            transaction.rollback();
        }
        return true;

    }

    @Override
    public Investment getInvestmentEagerBy(Long id) {
        String hql = "FROM Investment i LEFT JOIN FETCH i.asset LEFT JOIN i.user where i.id = :Id"; //LEFT JOIN FETCH: HQL里面的left join
        Session session = sessionFactory.openSession();
        try {
            Query<Investment> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Investment result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failed to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Investment update(Investment investment) {
        Session s = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            s.update(investment);
            Investment u = getById(investment.getId());
            transaction.commit();
            s.close();
            return u;
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
            logger.error("failed to insert record", e);
            s.close();
            return null;
        }
    }

}
