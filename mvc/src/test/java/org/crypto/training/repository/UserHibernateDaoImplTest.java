package org.crypto.training.repository;


import org.crypto.training.model.Asset;
import org.crypto.training.model.Investment;
import org.crypto.training.model.User;
import org.crypto.training.ApplicationBootStrap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class UserHibernateDaoImplTest{
    @Autowired
    private UserHibernateDaoImpl userHibernateDao;
    @Autowired
    private AssetHibernateDaoImpl assetHibernateDao;
    @Autowired
    private InvestmentHibernateDaoImpl investmentHibernateDao;

    private Asset e1;

    private Investment f1;

    private User d1;
    @Before
    public void setup(){
//       userHibernateDao = new UserHibernateDaoImpl();
        //step1: set up one side, so the following many side can map to one side
        d1 = new User();
//        d1.setId((long) (Math.random()*(100L-1L)));
        d1.setUsername("abc");
        d1.setPassword("abcd1234");
        d1.setEmail("abc@gmail.com");
        d1.setRegistration_date(new java.util.Date());
        userHibernateDao.save(d1);

        //step2: set up record in many side
//        assetHibernateDao = new AssetHibernateDaoImpl();
        e1 = new Asset();
        //e1.setId()
        e1.setName("aapl");
        e1.setType("stock");
        assetHibernateDao.save(e1);

//     investmentHibernateDao = new InvestmentHibernateDaoImpl();
        f1 = new Investment();
        //f1.setId()
        f1.setAsset(e1);
        f1.setQuantity(new BigDecimal("3.14"));
        f1.setPurchase_price(new BigDecimal("100"));
        f1.setUser(d1);
        f1.setPurchase_date(LocalDate.now());
        investmentHibernateDao.save(f1);
    }
    @After
    public void tearDown() {
        //step 1: delete records in many side first
        investmentHibernateDao.delete(f1);
        assetHibernateDao.delete(e1);
        //step 2: delete records in one side
        userHibernateDao.delete(d1);
    }

    @Test
    public void getUsersTest() {
        assertEquals(1, userHibernateDao.getUsers().size());
    }

    @Test
    public void getDepartmentEagerByTest() {
        User user = userHibernateDao.getUserEagerBy(d1.getId());
        assertNotNull(user);
        assertEquals(user.getUsername(), d1.getUsername());
        assertTrue(user.getInvestments().size() > 0);
    }
}