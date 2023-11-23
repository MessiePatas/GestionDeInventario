package co.gestor.Inventario.seguridad;

import co.gestor.Inventario.seguridad.entities.Attempts;
import co.gestor.Inventario.seguridad.repository.AttemptsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private AttemptsRepository attemptsRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/login?invalid=true");
        } else if (exception.getClass().isAssignableFrom(LockedException.class)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect("/login?locked=true");
        }
    }
}
