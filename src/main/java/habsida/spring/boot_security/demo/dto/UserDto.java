package habsida.spring.boot_security.demo.dto;

import habsida.spring.boot_security.demo.model.Role;

import java.util.HashSet;
import java.util.Set;

public class UserDto {
    private String login;
    private String password;
    private String email;
    private String roles;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String roles) {
        this.login = firstName;
        this.password = lastName;
        this.email = email;
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
