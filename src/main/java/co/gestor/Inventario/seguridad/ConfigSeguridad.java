package co.gestor.Inventario.seguridad;

import co.gestor.Inventario.seguridad.repository.AttemptsRepository;
import co.gestor.Inventario.seguridad.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class ConfigSeguridad extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new SecurityUserDetailsService(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthProvider authenticationProvider( ){
        return new AuthProvider();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/api/producto/**").anonymous()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler(authenticationFailureHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/api/producto/listar")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }

}
