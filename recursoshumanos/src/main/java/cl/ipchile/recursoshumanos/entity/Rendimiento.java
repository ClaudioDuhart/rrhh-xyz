package cl.ipchile.recursoshumanos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "rendimientos")
public class Rendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;
    @Getter @Setter
    private int puntos;
    @Getter @Setter
    private String periodo;
    @ManyToOne
    @JoinColumn(name = "rut_usuario", nullable = false) @Getter @Setter
    private Usuario usuario;
    @Transient @Getter @Setter
    private String rutUsuario;
}
