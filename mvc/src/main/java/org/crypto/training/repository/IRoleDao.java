package org.crypto.training.repository;

import org.crypto.training.model.Role;

public interface IRoleDao {

    Role getByName(String Name);
}
