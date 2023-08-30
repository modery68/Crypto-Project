package org.crypto.training.repository;

import org.crypto.training.ApplicationBootStrap;
import org.crypto.training.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class RoleHibernateDaoImplTest {
    @Autowired
    private RoleHibernateDaoImpl roleHibernateDao;

    String Name = "Admin";
    @Test
    public void getByNameTest(){
        Role role = roleHibernateDao.getByName(Name);

        assertNotNull(role);
    }
}
