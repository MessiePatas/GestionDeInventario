package co.gestor.Inventario.controller;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.controller.DTO.RespuestaDTO;
import co.gestor.Inventario.logica.ProductoLogica;
import co.gestor.Inventario.modelo.Producto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Controller
@RequestMapping("/api/producto")
@Slf4j
public class ProductoController {

    private final ProductoLogica productoLogica;
    private final Context context = new Context();

    private final TemplateEngine htmlTemplateEngine;
    public ProductoController(ProductoLogica productoLogica, TemplateEngine htmlTemplateEngine) {
        this.productoLogica = productoLogica;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    @GetMapping("/listar")
    public ResponseEntity<String> mostrarProductos() {
        List<Producto> productoList = productoLogica.listarProductos();

        context.setVariable("productoList", productoList);

        final String htmlContent = htmlTemplateEngine.process("homescreen.html", context);

        return ResponseEntity.ok().body(htmlContent);
    }


    @PostMapping("/agregar")
    public ResponseEntity<RespuestaDTO> guardarProducto(@RequestBody ProductoDTO productoDTO) {
        log.info("Guardando");
        productoLogica.guardarProducto(productoDTO);

        return ResponseEntity.ok(new RespuestaDTO("Producto guardado correctamente"));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<String> editarProducto(@PathVariable int id, RedirectAttributes re) {
        Producto producto = productoLogica.buscarProducto(id);
        List<Producto> productoList = productoLogica.listarProductos();
        context.setVariable("productoList", productoList);
        if (producto != null) {
            context.setVariable("producto", producto);
            String htmlContent = htmlTemplateEngine.process("homescreen_edit.html", context);
            return ResponseEntity.ok().body(htmlContent);

        } else {
            String htmlContent = htmlTemplateEngine.process("homescreen.html", context);
            return ResponseEntity.unprocessableEntity().body(htmlContent);
        }
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

    @GetMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        log.info("Eliminando");
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(id);

        Producto productoEliminado = productoLogica.eliminarProducto(productoDTO);

        List<Producto> productoList = productoLogica.listarProductos();
        context.setVariable("productoList", productoList);
        final String htmlContent = htmlTemplateEngine.process("homescreen.html", context);

        if (productoEliminado != null) {
            return ResponseEntity.ok().body(htmlContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
