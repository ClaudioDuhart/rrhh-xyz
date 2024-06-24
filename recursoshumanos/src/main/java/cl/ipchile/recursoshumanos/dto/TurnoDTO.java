package cl.ipchile.recursoshumanos.dto;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class TurnoDTO {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String rutUsuario;
    @Getter @Setter
    private String turnoEntrada;
    @Getter @Setter
    private String turnoSalida;
    @Getter @Setter
    private String fecha;
}
