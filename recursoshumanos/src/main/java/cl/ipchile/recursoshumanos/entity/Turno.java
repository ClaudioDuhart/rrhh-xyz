package cl.ipchile.recursoshumanos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;
    @Getter @Setter
    private String fecha;
    @Getter @Setter
    private String turnoEntrada;
    @Getter @Setter
    private String turnoSalida;
    @ManyToOne
    @JoinColumn(name = "rut_usuario", nullable = false) @Getter @Setter
    private Usuario usuario;
    @Transient @Getter @Setter
    private String rutUsuario;
}
