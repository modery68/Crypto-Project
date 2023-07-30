package org.crypto.training.controller;
import org.crypto.training.model.System_User;
import org.crypto.training.service.JWTService;
import org.crypto.training.service.System_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private System_UserService system_userService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody System_User system_user) {
        try{
        //String digestPassword = DigestUtils.md5Hex(password.trim());
        System_User s = system_userService.getUserByCredentials(system_user.getEmail(), system_user.getPassword());
        if (s == null) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().body(jwtService.generateToken(s));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
