package habsida.spring.boot_security.demo.dao;

import habsida.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   User getById(long id);
   void updateUser(User user);
   void deleteById(long id);
   List<User> getUsers();
}
