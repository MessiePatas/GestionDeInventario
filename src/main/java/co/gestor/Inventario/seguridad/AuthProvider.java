package co.gestor.Inventario.seguridad;
import co.gestor.Inventario.seguridad.entities.Attempts;
import co.gestor.Inventario.seguridad.entities.User;
import co.gestor.Inventario.seguridad.repository.AttemptsRepository;
import co.gestor.Inventario.seguridad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {
   private static final int ATTEMPTS_LIMIT = 3;

   @Autowired
   private AttemptsRepository attemptsRepository;


   @Autowired
   private UserRepository userRepository;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;


   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username = authentication.getName();
      String password = authentication.getCredentials().toString();

      // Retrieve the user based on the username
      Optional<User> userOptional = userRepository.findUserByUsername(username);

      if (userOptional.isPresent()) {
         User user = userOptional.get();

         // Check if the account is locked
         if (!user.isAccountNonLocked()) {
            throw new LockedException("La cuenta está bloqueada. Contacte al administrador.");
         }

         if (bCryptPasswordEncoder.matches(password, user.getPassword())) {

            resetFailedAttempts(username);
            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
         } else {
            processFailedAttempts(username, user);
            throw new BadCredentialsException("Credenciales incorrectas.");
         }
      } else {
         throw new UsernameNotFoundException("Usuario no encontrado.");
      }
   }

   private void resetFailedAttempts(String username) {
      Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
      if (userAttempts.isPresent()) {
         Attempts attempts = userAttempts.get();
         attempts.setAttempts(0);
         attemptsRepository.save(attempts);
      }
   }
   private void processFailedAttempts(String username, User user) {
      Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
      if (userAttempts.isEmpty()) { 
         Attempts attempts = new Attempts();
         attempts.setUsername(username); 
         attempts.setAttempts(1); 
         attemptsRepository.save(attempts); 
      } else {
         Attempts attempts = userAttempts.get(); 
         attempts.setAttempts(attempts.getAttempts() + 1); 
         attemptsRepository.save(attempts);
      
         if (attempts.getAttempts() + 1 > ATTEMPTS_LIMIT) {
            user.setAccountNonLocked(false); 
            userRepository.save(user); 
            throw new LockedException("Demasiados intentos. Se ha bloqueado la cuenta");
         } 
      }
   }

   public String encode(String password){
      return bCryptPasswordEncoder.encode(password);
   }


   @Override
   public boolean supports(Class<?> authentication) {
      return true; 
   }
}

