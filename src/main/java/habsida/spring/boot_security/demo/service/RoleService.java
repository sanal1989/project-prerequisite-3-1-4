package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.dao.RoleDao;
import habsida.spring.boot_security.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role findByName(String role){
        return roleDao.getByName(role);
    }
}
