package org.crypto.training.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.crypto.training.model.Asset;
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
public class AssetHibernateDaoImpl implements IAssetDao {

    private static final Logger logger = LoggerFactory.getLogger(AssetHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Asset asset) {
        //SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(asset);
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            if (transaction != null)
            logger.error("unable to save user or unable to close session", e);
            transaction.rollback();
        }
    }

    @Override
    public List<Asset> getAssets() {
        logger.info("Start to getAsset from Postgres via Hibernate.");

        List<Asset> assets = new ArrayList<>();// prepare model

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); // establish connection

        try {
            Session session = sessionFactory.openSession();

            String hql = "from Asset "; // execute query
            Query<Asset> query = session.createQuery(hql);

            assets = query.list();// extract data

            session.close(); // close session
        } catch (HibernateException e) {
            logger.error("Open session exception or close exception", e);
        }
        logger.info("Ger assets {}", assets);
        return assets;
    }

    @Override
    public Asset getById(Long id) {
        Session s = sessionFactory.openSession();
        String hql = "FROM Asset a where id = :ID";
    try {
        Query<Asset> query = s.createQuery(hql);
        query.setParameter("ID", id);
        Asset result = query.uniqueResult();
        s.close();
        return result;
    }catch (HibernateException e) {
        logger.error("Session close exception try again", e);
        s.close();

        return null;
    }
    }

    @Override
    public boolean delete(Asset asset) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(asset);
            transaction.commit();

            session.close();
        }catch (HibernateException e) {
            if (transaction != null)
                logger.error("unable to delete user or close session and Rollback", e);
            transaction.rollback();
        }
        return true;

    }

    @Override
    public Asset getAssetEagerBy(Long id) {
        String hql = "FROM Asset a LEFT JOIN FETCH a.investments where a.id = :Id"; //LEFT JOIN FETCH: HQL里面的left join
        Session session = sessionFactory.openSession();
        try {
            Query<Asset> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Asset result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failed to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Asset update(Asset asset) {
        Session s = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            s.update(asset);
            Asset a = getById(asset.getId());
            transaction.commit();
            s.close();
            return a;
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
            logger.error("failed to insert record", e);
            s.close();
            return null;
        }
    }
}
