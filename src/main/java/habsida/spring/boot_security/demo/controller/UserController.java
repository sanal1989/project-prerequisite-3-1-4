package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
import habsida.spring.boot_security.demo.service.UserServiceImp;
import org.hibernate.internal.util.collections.IdentitySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/user")
    public String getAll(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping(value = "/admin")
    public String getAllAdmin(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @DeleteMapping(value = "/admin/{id}")
    public String deleteUser(ModelMap model, @PathVariable Integer id){
        userService.deleteById(id);
        return getAllAdmin(model);
    }

    @PostMapping(value = "/admin/{id}")
    public String editUser(ModelMap model, @PathVariable Integer id, @RequestBody UserDto userDto){
        Set<Role> roles = new HashSet<>();
        if(userDto.getRoles().contains("ROLE_USER")){
            roles.add(roleService.findByName("ROLE_USER"));
        }if(userDto.getRoles().contains("ROLE_ADMIN")){
            roles.add(roleService.findByName("ROLE_ADMIN"));
        }
        User user = new User(userDto.getLogin(), userDto.getPassword(), userDto.getEmail());
        user.setRoles(roles);
        user.setId((long)id);
        userService.updateUser(user);
        return getAllAdmin(model);

    }

    @PostMapping(value = "/admin/add")
    public String addUser(ModelMap model, @RequestBody UserDto userDto){
        Set<Role> roles = new HashSet<>();
        if(userDto.getRoles().contains("ROLE_USER")){
            roles.add(roleService.findByName("ROLE_USER"));
        }if(userDto.getRoles().contains("ROLE_ADMIN")){
            roles.add(roleService.findByName("ROLE_ADMIN"));
        }
        User user = new User(userDto.getLogin(), userDto.getPassword(), userDto.getEmail());
        user.setRoles(roles);
        userService.add(user);
        return getAllAdmin(model);
    }
}
