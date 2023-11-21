package co.gestor.Inventario.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductoDTO {
    int id;
    String nombre;
    String descripcion;
    int precio;
    int cantidad;
    String categoria;

    public ProductoDTO(int id, String nombre, String descripcion, int precio, int cantidad, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public ProductoDTO() {

    }



}
