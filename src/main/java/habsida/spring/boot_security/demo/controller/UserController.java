package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.dto.UserDto;
import habsida.spring.boot_security.demo.model.Role;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
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


    @GetMapping(value = "/")
    public String getAll(ModelMap model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return"login";
    }

    @DeleteMapping(value = "/admin/{id}")
    public String deleteUser(ModelMap model, @PathVariable Integer id){
        userService.deleteById(id);
        return getAll(model);
    }

    @PostMapping(value = "/admin/{id}")
    public String editUser(ModelMap model, @PathVariable Integer id, @RequestBody UserDto userDto){
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
        return getAll(model);

    }

    @PostMapping(value = "/admin/add")
    public String addUser(ModelMap model, @RequestBody UserDto userDto){
        Set<Role> roles = new HashSet<>();
        if(userDto.getRoles().contains("USER")){
            roles.add(roleService.findByName("USER"));
        }if(userDto.getRoles().contains("ADMIN")){
            roles.add(roleService.findByName("ADMIN"));
        }
        User user = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getAge(), userDto.getPassword(), userDto.getEmail());
        user.setRoles(roles);
        userService.add(user);
        return getAll(model);
    }
}
