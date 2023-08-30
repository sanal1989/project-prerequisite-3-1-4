package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.dao.UserDao;
import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;


   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Override
   public User getById(long id) {
      return userDao.getById(id);
   }

   @Transactional
   @Override
   public void updateUser(User user) {
      userDao.updateUser(user);
   }

   @Transactional
   @Override
   public void deleteById(long id) {
      userDao.deleteById(id);
   }

   @Override
   public List<User> getUsers() {
      return userDao.getUsers();
   }

   @Override
   public List<UserDto> mappingUser(List<User> listUser) {
      List<UserDto> listDto = new ArrayList<>();
      for (User i: listUser) {
         listDto.add(new UserDto((int) (long)i.getId(),
                 i.getFirstName(),
                 i.getLastName(),
                 i.getAge(),
                 i.getPassword(),
                 i.getEmail(),
                 i.getRoles().toString()));
      }
      return listDto;
   }

}
