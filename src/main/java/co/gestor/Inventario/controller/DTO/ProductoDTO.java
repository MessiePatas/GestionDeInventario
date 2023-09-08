package co.gestor.Inventario.controller.DTO;

import lombok.Data;

@Data
public class ProductoDTO {
    int id;
    String nombre;
    String descripcion;
    int precio;
    int cantidad;
    String categoria;
}
