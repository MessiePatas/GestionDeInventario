package co.gestor.Inventario.seguridad.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity 
@Table(name = "users")

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User implements UserDetails {

   @Serial
   private static final long serialVersionUID = 1L;

   @Id 
   private String username;

   private String password;
   @Column(name = "account_non_locked")
   private boolean accountNonLocked;

   @Column(name = "role")
   private String role;

   @Override 
   public Collection<? extends GrantedAuthority> getAuthorities() {
      String roleName = this.role.toUpperCase();
      System.out.println(roleName);
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();

      authorities.add(new SimpleGrantedAuthority(roleName));
      return authorities;
   }
   @Override
   public String getPassword() {    
      return password; 
   }
   @Override 
   public String getUsername() { 
      return username; 
   }
   @Override 
   public boolean isAccountNonExpired() { 
      return true; 
   } 
   @Override
   public boolean isAccountNonLocked() { 
      return accountNonLocked; 
   } 
   @Override public boolean isCredentialsNonExpired() { 
      return true; 
   } 
   @Override public boolean isEnabled() { 
   return true; 
   }
   public boolean getAccountNonLocked() { 
      return accountNonLocked; 
   } 
}