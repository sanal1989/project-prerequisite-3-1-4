package habsida.spring.boot_security.demo.dao;

import habsida.spring.boot_security.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {

    private RoleRepository roleRepository;

    @Autowired
    public RoleDao(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getByName(String role){
        return roleRepository.findByName(role).get();
    }
}
