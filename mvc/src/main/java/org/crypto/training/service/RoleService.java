package org.crypto.training.service;

import org.crypto.training.model.Role;
import org.crypto.training.repository.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private IRoleDao roleDao;

    public Role getByName(){
        return getByName();
    }
}
