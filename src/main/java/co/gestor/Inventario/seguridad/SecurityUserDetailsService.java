package co.gestor.Inventario.seguridad;

import co.gestor.Inventario.seguridad.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import co.gestor.Inventario.seguridad.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@Slf4j

public class SecurityUserDetailsService implements UserDetailsService {


   private final UserRepository userRepository;

   public SecurityUserDetailsService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     return userRepository.findUserByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("User not present"));
   } 
   public void createUser(UserDetails user) { 
      userRepository.save((User) user);
      log.info("User created: {}", user);
   }

}