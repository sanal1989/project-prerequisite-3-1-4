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

   @Column(name = "firstName")
   private String firstName;

   @Column(name = "lastName")
   private String lastName;

   @Column(name = "age")
   private int age;

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

   public User(String firstName, String lastName, int age, String password, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
      this.password = password;
      this.email = email;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
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

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }
}
