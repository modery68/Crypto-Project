package org.crypto.training.service;

import io.jsonwebtoken.Claims;
import org.crypto.training.model.System_User;
import org.crypto.training.ApplicationBootStrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class JWTServiceTest {

    @Autowired
    private JWTService jwtService;

    @Test
    public void generateTokenTest() {
        System_User u = new System_User();
        u.setId(1);
        u.setName("Z");

        String token = jwtService.generateToken(u);

        String[] array = token.split("\\.");
        boolean bool = array.length == 3 ? true: false;
        assertTrue(bool);
    }

    @Test
    public void decryptTokenTest() {
        System_User u = new System_User();
        u.setId(1);
        u.setName("Z");
        String token = jwtService.generateToken(u);
        Claims c = jwtService.decryptToken(token);
        String userName = c.getSubject();
        assertEquals(u.getName(), userName);
    }
}
