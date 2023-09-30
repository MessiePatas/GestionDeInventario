package co.gestor.Inventario.logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.modelo.Producto;
import co.gestor.Inventario.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;


import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
class ProductoLogicaTest {

    @InjectMocks
    private ProductoLogica productoLogica;

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        productoLogica = new ProductoLogica(productoRepository);
    }

    @Test
     void testEliminarProducto() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1); // ID válido
        Producto productoExistente = new Producto();
        productoExistente.setId(1);
        when(productoRepository.findById(productoDTO.getId())).thenReturn(Optional.of(productoExistente));
        Producto productoEliminado = productoLogica.eliminarProducto(productoDTO);
        assertNotNull(productoEliminado);
        assertEquals(productoDTO.getId(), productoEliminado.getId());
        Mockito.verify(productoRepository).delete(productoExistente);
    }
    @Test
     void testEliminarProductoNoExistente() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);

        when(productoRepository.findById(productoDTO.getId())).thenReturn(Optional.empty());

        Producto productoEliminado = productoLogica.eliminarProducto(productoDTO);

        assertNull(productoEliminado);


        Mockito.verify(productoRepository, never()).delete(any());
    }

    @Test
     void guardarProducto() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        productoDTO.setNombre("Cuaderno"); // Nombre válido
        productoDTO.setDescripcion("Cuaderno de notas");
        productoDTO.setPrecio(5);
        productoDTO.setCantidad(10);
        productoDTO.setCategoria("Papelería");

        Producto productoGuardado = productoLogica.guardarProducto(productoDTO);

        assertNotNull(productoGuardado);
        assertEquals(productoDTO.getId(), productoGuardado.getId());

        // Verify
        Mockito.verify(productoRepository).save(productoGuardado);
    }
    @Test
     void testGuardarProductoInvalido() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(2);
        productoDTO.setNombre("papel");
        //assert

        assertThrows(IllegalArgumentException.class, () -> {
            productoLogica.guardarProducto(productoDTO);
        });


        verify(productoRepository, never()).save(any());
    }
    @Test
    public void testActualizarProducto() {
        // Datos de prueba
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1);
        productoDTO.setNombre("Nuevo Nombre");
        productoDTO.setDescripcion("Nueva Descripción");
        productoDTO.setCategoria("Nueva Categoría");
        productoDTO.setPrecio(19);
        productoDTO.setCantidad(50);

        Producto productoExistente = new Producto();
        productoExistente.setId(1);
        productoExistente.setNombre("Nombre Antiguo");
        productoExistente.setDescripcion("Descripción Antigua");
        productoExistente.setCategoria("Categoría Antigua");
        productoExistente.setPrecio(15);
        productoExistente.setCantidad(30);

        // Mock del repositorio
        when(productoRepository.findById(1)).thenReturn(Optional.of(productoExistente));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llamada al método
        Producto productoActualizado = productoLogica.actualizarProducto(productoDTO);

        // Verificación de resultados
        assertNotNull(productoActualizado);
        assertEquals(productoDTO.getId(), productoActualizado.getId());
        assertEquals(productoDTO.getNombre(), productoActualizado.getNombre());
        assertEquals(productoDTO.getDescripcion(), productoActualizado.getDescripcion());
        assertEquals(productoDTO.getCategoria(), productoActualizado.getCategoria());
        assertEquals(productoDTO.getPrecio(), productoActualizado.getPrecio());
        assertEquals(productoDTO.getCantidad(), productoActualizado.getCantidad());

        // Verificación de llamadas al repositorio
        verify(productoRepository, times(1)).findById(1);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }
}
