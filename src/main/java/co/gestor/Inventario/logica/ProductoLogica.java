package co.gestor.Inventario.logica;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.modelo.Producto;
import co.gestor.Inventario.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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


}