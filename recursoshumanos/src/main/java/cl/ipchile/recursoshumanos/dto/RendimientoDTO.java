package cl.ipchile.recursoshumanos.dto;

import lombok.Getter;
import lombok.Setter;

public class RendimientoDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private Integer puntos;
    @Getter @Setter
    private String periodo;
    @Getter @Setter
    private String rutUsuario;
}
