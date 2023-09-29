package co.gestor.Inventario.logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.modelo.Producto;
import co.gestor.Inventario.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ProductoLogicaTest {
    private ProductoLogica productoLogica;

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
    public void setUp() {
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

        // Verifica que se haya llamado al método delete en el repositorio
        verify(productoRepository).delete(productoExistente);
    }
    @Test
    public void testEliminarProductoNoExistente() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1); // ID válido

        when(productoRepository.findById(productoDTO.getId())).thenReturn(Optional.empty());

        Producto productoEliminado = productoLogica.eliminarProducto(productoDTO);

        assertNull(productoEliminado);

        // Verifica que no se haya llamado al método delete en el repositorio
        verify(productoRepository, never()).delete(any());
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

        // Verifica que se haya llamado al método save en el repositorio
        verify(productoRepository).save(productoGuardado);
    }
    @Test
    public void testGuardarProductoInvalido() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(2);
        productoDTO.setNombre("papel"); // Nombre inválido

        // No necesitamos configurar el repositorio en este caso, ya que no debería llamar a save.

        // Verifica que se lance una excepción cuando se intenta guardar un producto inválido
        assertThrows(IllegalArgumentException.class, () -> {
            productoLogica.guardarProducto(productoDTO);
        });

        // Verifica que nunca se haya llamado al método save en el repositorio
        verify(productoRepository, never()).save(any());
    }

}