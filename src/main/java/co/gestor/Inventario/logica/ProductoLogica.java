package co.gestor.Inventario.logica;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.modelo.Producto;
import co.gestor.Inventario.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoLogica implements IService {

    private final ProductoRepository productoRepository;

    public Producto guardarProducto(ProductoDTO productoDTO) {
        if ("papel".equals(productoDTO.getNombre())) {
            throw new IllegalArgumentException("Esto no es una papeleria");
        }
        Producto productoBD = new Producto();
        productoBD.setId(productoDTO.getId());
        productoBD.setNombre(productoDTO.getNombre());
        productoRepository.save(productoBD);

        return productoBD;
    }

    @Transactional
    public Producto actualizarProducto(ProductoDTO productoDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(productoDTO.getId());

        if (productoOptional.isPresent()) {
            Producto productoExistente = productoOptional.get();


            productoExistente.setNombre(productoDTO.getNombre());


            Producto productoActualizado = productoRepository.save(productoExistente);

            return productoActualizado;
        } else {
            return null;
        }
    }


}