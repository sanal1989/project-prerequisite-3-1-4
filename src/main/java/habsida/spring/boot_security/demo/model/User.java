package habsida.spring.boot_security.demo.model;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "login")
   private String login;

   @Column(name = "password")
   private String password;

   @Column(name = "email")
   private String email;

   @ManyToMany(fetch = FetchType.EAGER)
   @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.PERSIST})
   @JoinTable(name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id",referencedColumnName="id"),
           inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName="id"))
   private Set<Role> roles = new HashSet<>();

   public User() {}

   public User(String login, String password, String email) {
      this.login = login;
      this.password = password;
      this.email = email;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String firstName) {
      this.login = firstName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String lastName) {
      this.password = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }
}
