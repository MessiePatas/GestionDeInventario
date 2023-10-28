package co.gestor.Inventario.controller.DTO;

import lombok.Data;

@Data
public class RespuestaDTO {

    String mensaje;

    public RespuestaDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public RespuestaDTO() {
    }
}
