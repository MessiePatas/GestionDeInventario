package co.gestor.Inventario.controller;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.controller.DTO.RespuestaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "sa")
class ProductoControllerTest {
    @Autowired
    TestRestTemplate rest;
    @LocalServerPort
    private int randomServerPort;

    @Test
    void cuando_guardarProducto_deberiaRetornar_productoGuardadoCorrectamente_productoActualizadoCorrectamente() {

        ProductoDTO dto = new ProductoDTO(14, "rollos", "nuevo", 452, 56, "caro");
        ResponseEntity<RespuestaDTO> respuesta = rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);
        assertEquals("Producto guardado correctamente", respuesta.getBody().getMensaje());
    }

    @Test
    void cuando_actualizarProducto_deberiaRetornar_Producto_actualizado_correctamente() {
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
    void testEliminarProducto_cuandoproductoexite_deberiaRetornar_Producto_eliminado_correctamente() {

        ProductoDTO dto = new ProductoDTO(21, "rollos", "nuevo", 452, 56, "caro");
        rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);

        try {


            // Realizar la solicitud DELETE con exchange
            ResponseEntity<RespuestaDTO> response = rest.exchange(
                    "/api/producto/eliminar/21", HttpMethod.DELETE, null, RespuestaDTO.class);

            // Verificar el código de respuesta esperado (200 OK)
            assertEquals("Producto eliminado correctamente", response.getBody().getMensaje());
            assertEquals(HttpStatus.OK, response.getStatusCode());


        } catch (HttpClientErrorException.NotFound ex) {
            // Si el producto no se encuentra, esta excepción será lanzada (404 Not Found)
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }
    @Test
     void Test_eliminar_producto_no_exitente_deberiaretornar_HttpStatus_NOT_FOUND(){
        ResponseEntity<RespuestaDTO> response = rest.exchange(
                "/api/producto/eliminar/80", HttpMethod.DELETE, null, RespuestaDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }

    @Test

    void Test_actualizar_cuandoNoexiste_deberia_retornar_HttpStatus_NOT_FOUND(){
        ProductoDTO dto = new ProductoDTO(21, "rollos", "nuevo", 452, 56, "caro");
        rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);
        ResponseEntity<RespuestaDTO> respuesta = rest.exchange(

                "/api/producto/actualizar/10",
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                RespuestaDTO.class,
                dto.getId()
        );
        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());

    }
}
