package habsida.spring.boot_security.demo.dao;

import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserDaoImp implements UserDao {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDaoImp(UserRepository userRepository,RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("user don't find");
        }
        return optionalUser.get();
    }

    @Override
    public void updateUser(User user) {

        userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        User user = userRepository.findById(id).get();
        Set<Role> roles = user.getRoles();
        for (Role i: roles) {
            Set<User> users = i.getUsers();
            users.remove(user);
        }
        userRepository.deleteById(id);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

}
