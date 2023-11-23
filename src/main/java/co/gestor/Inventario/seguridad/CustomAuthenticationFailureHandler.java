package co.gestor.Inventario.seguridad;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

 @Override
 public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
   super.onAuthenticationFailure(request, response, exception);
   if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
     response.sendRedirect("/login?error=true");
   } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
     response.sendRedirect("/login?error=locked");
   }
 }
}