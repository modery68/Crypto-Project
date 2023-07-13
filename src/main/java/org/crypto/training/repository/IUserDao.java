package org.crypto.training.repository;

import org.crypto.training.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IUserDao {

    public List<User> getUsers();

    void save(User user);

    User getById(Long id);

    boolean delete(User user);

    User getUserEagerBy(Long id);
}
