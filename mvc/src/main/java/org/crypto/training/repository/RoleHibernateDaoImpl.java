package org.crypto.training.repository;
import org.crypto.training.model.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleHibernateDaoImpl implements IRoleDao{

    private static final Logger logger = LoggerFactory.getLogger(AssetHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getByName(String name) {
        Session s = sessionFactory.openSession();
        String hql = "FROM Role r where name = :Name";
        try {
            Query<Role> query = s.createQuery(hql);
            query.setParameter("Name", name);
            Role result = query.uniqueResult();
            s.close();
            return result;
        }catch(HibernateException e){
            logger.error("Session close exception try again", e);
            s.close();

            return null;
        }

    }
}
