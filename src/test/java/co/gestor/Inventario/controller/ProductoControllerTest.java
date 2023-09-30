package co.gestor.Inventario.controller;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.controller.DTO.RespuestaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;


import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "sa")

class ProductoControllerTest {
    @Autowired
    TestRestTemplate rest;

    @Test
    void guardarProducto() {

        ProductoDTO dto = new ProductoDTO(14,"rollos","nuevo",452,56,"caro");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente",respuesta.getBody().getMensaje());

    }

    @Test
    void actualizarProducto() {
        /*ProductoDTO dto = new ProductoDTO(11,"celular","usado",442,66,"barato");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity("/api/producto/actualizar/{id}",dto, RespuestaDTO.class);
        assertEquals("Producto actualizado correctamente", respuesta.getBody().getMensaje());*/
    }

    @Test
    void eliminarProducto() {

    }
}