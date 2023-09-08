package co.gestor.Inventario.controller;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.controller.DTO.RespuestaDTO;
import co.gestor.Inventario.logica.ProductoLogica;
import co.gestor.Inventario.modelo.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoLogica productoLogica;

    public ProductoController(ProductoLogica productoLogica) {
        this.productoLogica = productoLogica;
    }

    @PostMapping("/agregar")
    public ResponseEntity<RespuestaDTO> guardarProducto(@RequestBody ProductoDTO productoDTO) {
        productoLogica.guardarProducto(productoDTO);
        return ResponseEntity.ok(new RespuestaDTO("Producto guardado correctamente"));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<RespuestaDTO> actualizarProducto(@PathVariable int id, @RequestBody ProductoDTO productoDTO) {
        try {
            productoDTO.setId(id);
            Producto productoActualizado = productoLogica.actualizarProducto(productoDTO);
            if (productoActualizado != null) {
                return ResponseEntity.ok(new RespuestaDTO("Producto actualizado correctamente"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new RespuestaDTO("No se pudo actualizar el producto: " + e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<RespuestaDTO> eliminarProducto(@PathVariable int id) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(id);

        Producto productoEliminado = productoLogica.eliminarProducto(productoDTO);

        if (productoEliminado != null) {
            return ResponseEntity.ok(new RespuestaDTO("Producto eliminado correctamente"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
