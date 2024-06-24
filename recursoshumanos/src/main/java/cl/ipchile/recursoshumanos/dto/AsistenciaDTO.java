package cl.ipchile.recursoshumanos.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class AsistenciaDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String horaEntrada;
    @Getter @Setter
    private Boolean marcajeEntrada;
    @Getter @Setter
    private String horaSalida;
    @Getter @Setter
    private Boolean marcajeSalida;
    @Getter @Setter
    private String fecha;
    @Getter @Setter
    private String rutUsuario;
}
