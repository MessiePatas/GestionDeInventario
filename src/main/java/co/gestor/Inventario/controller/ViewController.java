package co.gestor.Inventario.controller;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class ViewController {

    private final ProductoController productoController;


    @GetMapping("/homescreen")
    public String Home(){

        List<ProductoDTO> productoDTOList = productoController.mostrarProductos().getBody();
        log.info(String.valueOf(productoDTOList.size()));
        return "homescreen";
    }
    @GetMapping("/login")
    public String Login(){
        return "login";
    }
}
