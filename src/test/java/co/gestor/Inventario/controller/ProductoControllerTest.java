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

   /* @Test
    void actualizarProducto() {
        ProductoDTO dto = new ProductoDTO(11,"celular","usado",442,66,"barato");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity("/api/producto/actualizar/{id}",dto, RespuestaDTO.class);
        assertEquals("Producto actualizado correctamente", respuesta.getBody().getMensaje());

        ProductoDTO dto = new ProductoDTO(14, "celular", "usado", 442, 66, "barato");
        ResponseEntity<RespuestaDTO> respuesta = rest.exchange(
                "/api/producto/actualizar/14",
                HttpMethod.PUT,
                null,
                RespuestaDTO.class,
                dto.getId()
        );


        assertEquals("No se pudo actualizar el producto:.", respuesta.getBody().getMensaje());


    }
*/

   /* @Test
    public void testActualizarProducto() {
        int id = 1; // Puedes modificar esto con un ID real si es necesario
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("NuevoNombre"); // Establece los campos requeridos del DTO
        // ... otros campos del ProductoDTO ...

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<ProductoDTO> requestEntity = new HttpEntity<>(productoDTO, headers);

        ResponseEntity<RespuestaDTO> response = rest.exchange(
                "http://localhost:" + randomServerPort + "/api/producto/actualizar/1" ,
                HttpMethod.PUT,
                requestEntity,
                RespuestaDTO.class
        );

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Producto actualizado correctamente", response.getBody().getMensaje());
    }
*/
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

