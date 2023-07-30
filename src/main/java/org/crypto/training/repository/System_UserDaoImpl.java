package org.crypto.training.repository;

import org.crypto.training.model.System_User;
import org.crypto.training.repository.exception.System_UserNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class System_UserDaoImpl implements ISystem_UserDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(System_User system_user) {return false;}

    @Override
    public System_User getSystem_UserByEmail(String email) {return null;}

    @Override
    public System_User getSystem_UserById(Long id) {
            Session s = sessionFactory.openSession();
            String hql = "FROM System_User d where id= :Id";
            try {
                Query<System_User> query = s.createQuery(hql);
                query.setParameter("Id", id);
                System_User result = query.uniqueResult();
                s.close();
                return result;
            } catch (HibernateException e) {
                logger.error("Session close exception try again", e);
                s.close();
                return null;
            }
    }

    @Override
    public System_User getUserByCredentials(String email, String password) throws Exception {
        String hql = "FROM System_User as s where lower(s.email) = :email and s.password = :password";

        try {
            Session session = sessionFactory.openSession();
            Query<System_User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        }catch (Exception e) {
            logger.error("error: {}", e.getMessage());
            throw new System_UserNotFoundException("can't find user record with email="+email+ ", password="+password);
        }
    }

}
