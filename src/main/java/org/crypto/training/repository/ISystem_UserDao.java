package org.crypto.training.repository;

import org.crypto.training.model.System_User;

public interface ISystem_UserDao {

    boolean save(System_User system_user);

    System_User getSystem_UserByEmail(String email);

    System_User getSystem_UserById(Long id);

    System_User getUserByCredentials(String email, String password) throws Exception;
}
