package co.gestor.Inventario.controller;

import co.gestor.Inventario.seguridad.AuthProvider;
import co.gestor.Inventario.seguridad.SecurityUserDetailsService;
import co.gestor.Inventario.seguridad.entities.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@Slf4j
@AllArgsConstructor
public class LoginController {

    private final AuthProvider authProvider;

    private final SecurityUserDetailsService userDetailsManager;

    @GetMapping("/")
    public String index() {
        return "redirect:/api/producto/listar";
    }

    @GetMapping("/login")
    public String Login(){
        return "login";
    }

    @GetMapping("/admin")
    public String paginaRegistro() {
        return "register";
    }

    @PostMapping(value = "/admin/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String addUser(@RequestParam Map<String, String> body) {
        User user = new User();
        user.setUsername(body.get("username"));
        user.setPassword(authProvider.encode(body.get("password")));;
        user.setRole(body.get("role"));
        user.setAccountNonLocked(true);
        userDetailsManager.createUser(user);
        return "redirect:/api/producto/listar";
    }
}
