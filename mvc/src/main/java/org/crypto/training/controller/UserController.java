package org.crypto.training.controller;
import org.crypto.training.model.Role;
import org.crypto.training.model.User;
import org.crypto.training.service.RoleService;
import org.crypto.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> users = userService.getUsers();
        return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(name = "id") Long id) {
        logger.info("THis is user controller, get by {}", id);
        return userService.getBy(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"username"})
    public User updateUserName(@PathVariable("id") Long id, @RequestParam("username") String name) {
        logger.info("pass in variable id: {} and name: {}", id.toString(), name);
        User u = userService.getBy(id);
        u.setUsername(name);
        u = userService.update(u);
        return u;
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody User user) {
        logger.info("Post a new object {}", user.getUsername());
        try{

            userService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            logger.error("Error creating user: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
