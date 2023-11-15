package co.gestor.Inventario.controller;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.controller.DTO.RespuestaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "sa")
class ProductoControllerTest {
    @Autowired
    TestRestTemplate rest;
    @LocalServerPort
    private int randomServerPort;

    @Test
    void guardarProducto() {

        ProductoDTO dto = new ProductoDTO(14, "rollos", "nuevo", 452, 56, "caro");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuesta.getBody().getMensaje());
    }

    @Test
    void actualizarProducto() {
        ProductoDTO dto = new ProductoDTO(21, "rollos", "nuevo", 452, 56, "caro");
        rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);

        ResponseEntity<RespuestaDTO> respuesta = rest.exchange(
                "/api/producto/actualizar/21",
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                RespuestaDTO.class,
                dto.getId()
        );


        assertEquals("Producto actualizado correctamente", respuesta.getBody().getMensaje());


    }


    @Test
    public void testEliminarProducto() {
        // ID del producto a eliminar
        int idProductoAEliminar = 1;

        // Configuración del RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // URL relativa para la solicitud DELETE
        String url = "/api/producto/eliminar/" + idProductoAEliminar;

        try {
            // Construir la URL base dinámicamente usando el puerto aleatorio
            String BASE_URL = "http://localhost:" + randomServerPort;
            String fullUrl = BASE_URL + url;

            // Realizar la solicitud DELETE con exchange
            ResponseEntity<RespuestaDTO> response = restTemplate.exchange(
                    fullUrl, HttpMethod.DELETE, null, RespuestaDTO.class);

            // Verificar el código de respuesta esperado (200 OK)
            assertEquals(HttpStatus.OK, response.getStatusCode());
        } catch (HttpClientErrorException.NotFound ex) {
            // Si el producto no se encuentra, esta excepción será lanzada (404 Not Found)
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }
}

