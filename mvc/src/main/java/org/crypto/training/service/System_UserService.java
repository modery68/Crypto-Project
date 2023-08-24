package org.crypto.training.service;

import org.crypto.training.model.System_User;
import org.crypto.training.repository.ISystem_UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class System_UserService {

    @Autowired
    private ISystem_UserDao systemUserDao;

    public System_User getUserByCredentials(String email, String password) throws Exception {
        return systemUserDao.getUserByCredentials(email,password);
    }

    public System_User getSystem_UserById(Long id) {
        return systemUserDao.getSystem_UserById(id);
    }
}
