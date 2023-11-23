package co.gestor.Inventario.logica;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.modelo.Producto;
import co.gestor.Inventario.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductoLogica implements IService {

    private final ProductoRepository productoRepository;

    public Producto buscarProducto(int id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        return productoOptional.orElse(null);
    }

    public List<Producto> listarProductos(){
        return productoRepository.findAll();


    }
    public Producto guardarProducto(ProductoDTO productoDTO) {

        Producto productoBD = new Producto();
        productoBD.setId(productoDTO.getId());
        productoBD.setNombre(productoDTO.getNombre());
        productoBD.setDescripcion(productoDTO.getDescripcion());
        productoBD.setPrecio(productoDTO.getPrecio());
        productoBD.setCantidad(productoDTO.getCantidad());
        productoBD.setCategoria(productoDTO.getCategoria());

        productoRepository.save(productoBD);

        return productoBD;
    }

    @Transactional
    public Producto actualizarProducto(ProductoDTO productoDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(productoDTO.getId());

        if (productoOptional.isPresent()) {
            Producto productoExistente = productoOptional.get();

            productoExistente.setId(productoDTO.getId());
            productoExistente.setNombre(productoDTO.getNombre());
            productoExistente.setDescripcion(productoDTO.getDescripcion());
            productoExistente.setCategoria(productoDTO.getCategoria());
            productoExistente.setPrecio(productoDTO.getPrecio());
            productoExistente.setCantidad(productoDTO.getCantidad());
            productoExistente.setCategoria(productoDTO.getCategoria());


            return productoRepository.save(productoExistente);
        } else {
            return null;
        }
    }


    public Producto eliminarProducto(ProductoDTO productoDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(productoDTO.getId());
        if (productoOptional.isPresent()) {
            Producto productoExistente = productoOptional.get();
            productoRepository.delete(productoExistente);
            log.info("Producto eliminado:" + productoOptional.get().getId());
            return productoExistente;
        } else {
            return null;
        }
    }

}