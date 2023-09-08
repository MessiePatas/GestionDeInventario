package co.gestor.Inventario.logica;

import co.gestor.Inventario.controller.DTO.ProductoDTO;
import co.gestor.Inventario.modelo.Producto;

public interface IService {
    Producto guardarProducto(ProductoDTO productoDTO);

    Producto actualizarProducto(ProductoDTO productoDTO);

}
