package cl.ipchile.recursoshumanos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;
    @Getter @Setter
    private String fecha;
    @Getter @Setter
    private String horaEntrada;
    @Getter @Setter
    private Boolean marcajeEntrada;
    @Getter @Setter
    private String horaSalida;
    @Getter @Setter
    private Boolean marcajeSalida;
    @ManyToOne
    @JoinColumn(name = "rut_usuario", nullable = false) @Getter @Setter
    private Usuario usuario;
    @Transient @Getter @Setter
    private String rutUsuario;

}
