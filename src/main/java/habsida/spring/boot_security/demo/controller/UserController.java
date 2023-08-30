package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/admin")
    public List<UserDto> getAllUser() {
        return userService.mappingUser(userService.getUsers());
    }

    @DeleteMapping(value = "/admin/{id}")
    public List<UserDto> deleteUser( @PathVariable Integer id){
        userService.deleteById(id);
        return userService.mappingUser(userService.getUsers());
    }

    @PostMapping(value = "/admin/{id}")
    public List<UserDto> editUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        Set<Role> roles = new HashSet<>();
        if(userDto.getRoles().contains("USER")){
            roles.add(roleService.findByName("USER"));
        }if(userDto.getRoles().contains("ADMIN")){
            roles.add(roleService.findByName("ADMIN"));
        }
        User user = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getAge(), userDto.getPassword(), userDto.getEmail());
        user.setRoles(roles);
        user.setId((long)id);
        userService.updateUser(user);
        return userService.mappingUser(userService.getUsers());
    }

    @PostMapping(value = "/admin/add")
    public List<UserDto> addUser(@RequestBody UserDto userDto){
        Set<Role> roles = new HashSet<>();
        if(userDto.getRoles().contains("USER")){
            roles.add(roleService.findByName("USER"));
        }if(userDto.getRoles().contains("ADMIN")){
            roles.add(roleService.findByName("ADMIN"));
        }
        User user = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getAge(), userDto.getPassword(), userDto.getEmail());
        user.setRoles(roles);
        userService.add(user);
        return userService.mappingUser(userService.getUsers());
    }
}
