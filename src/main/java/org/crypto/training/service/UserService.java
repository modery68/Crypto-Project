package org.crypto.training.service;

import org.crypto.training.model.User;
import org.crypto.training.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User update(User user) {
        return userDao.getById(user.getId());
    }

    public boolean delete(User user) {
        return userDao.delete(user);
    }

    public User getUserEager(long id) {
        return userDao.getUserEagerBy(id);
    }
}
