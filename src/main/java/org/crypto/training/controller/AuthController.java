package org.crypto.training.controller;

import org.crypto.training.model.System_User;
import org.crypto.training.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthController {

    @Autowired
    private JWTService jwtService;
    public String userLogin(String userName, String password) {

        System_User system_user =

        return jwtService.generateToken(system_user);
    }
}
