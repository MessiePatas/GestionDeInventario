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
            ResponseEntity<String> response = rest.exchange(
                    "/api/producto/eliminar/21", HttpMethod.GET, null, String.class);

            // Verificar el código de respuesta esperado (200 OK)
            assertEquals(HttpStatus.OK, response.getStatusCode());


        } catch (HttpClientErrorException.NotFound ex) {
            // Si el producto no se encuentra, esta excepción será lanzada (404 Not Found)
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }
    @Test
     void Test_eliminar_producto_no_exitente_deberiaretornar_HttpStatus_NOT_FOUND(){
        ResponseEntity<String> response = rest.exchange(
                "/api/producto/eliminar/80", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }


    @Test
    void Test_actualizar_cuandoNoexiste_deberia_retornar_HttpStatus_NOT_FOUND(){
        ProductoDTO dto = new ProductoDTO(21, "rollos", "nuevo", 452, 56, "caro");
        rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);
        ResponseEntity<String> respuesta = rest.exchange(

                "/api/producto/actualizar/10",
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                String.class,
                dto.getId()
        );
        assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());

    }

    @Test
    void Test_buscar_producto_no_existente(){
        ResponseEntity<String> response = rest.exchange(
                "/api/producto/buscar/80", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }


    @Test
    void Test_buscar_producto_existente(){
        ProductoDTO dto = new ProductoDTO(21, "rollos", "nuevo", 452, 56, "caro");
        rest.postForEntity("/api/producto/agregar", dto, RespuestaDTO.class);
        ResponseEntity<String> response = rest.exchange(
                "/api/producto/buscar/21", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void actualizar_producto_no_existente(){
        ResponseEntity<RespuestaDTO> response = rest.exchange(
                "/api/producto/actualizar/80", HttpMethod.PUT, null, RespuestaDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    void listar(){
        ResponseEntity<String> response = rest.exchange(
                "/api/producto/listar", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
