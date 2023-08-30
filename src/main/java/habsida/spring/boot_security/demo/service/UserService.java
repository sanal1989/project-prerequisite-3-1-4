package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    User getById(long id);
    void updateUser(User user);
    void deleteById(long id);
    List<User> getUsers();
    List<UserDto> mappingUser(List<User> listUser);
}
