package co.gestor.Inventario.controller;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.controller.DTO.RespuestaDTO;
import co.gestor.Inventario.logica.ProductoLogica;
import co.gestor.Inventario.modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoControllerTest {

    private ProductoController productoController;
    private ProductoLogica productoLogica;

    @BeforeEach
    void setUp() {
        productoLogica = Mockito.mock(ProductoLogica.class);
        productoController = new ProductoController(productoLogica);
    }

    @Test
    void guardarProductoDeberiaRetornarRespuestaExitosa() {
        // Arrange
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Producto de prueba");
        Mockito.when(productoLogica.guardarProducto(productoDTO)).thenReturn(new Producto());

        // Act
        ResponseEntity<RespuestaDTO> respuesta = productoController.guardarProducto(productoDTO);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Producto guardado correctamente", respuesta.getBody().getMensaje());
    }

    @Test
    void actualizarProductoDeberiaRetornarRespuestaExitosa() {
        // Arrange
        int id = 1;
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(id);
        productoDTO.setNombre("Producto actualizado de prueba");
        Mockito.when(productoLogica.actualizarProducto(productoDTO)).thenReturn(new Producto());

        // Act
        ResponseEntity<RespuestaDTO> respuesta = productoController.actualizarProducto(id, productoDTO);

        // Assert
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Producto actualizado correctamente", respuesta.getBody().getMensaje());
    }


}
