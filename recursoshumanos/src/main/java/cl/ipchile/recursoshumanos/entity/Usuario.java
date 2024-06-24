package cl.ipchile.recursoshumanos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id @Getter @Setter
    private String rut;
    @Getter @Setter
    private String contraseña;
    @Getter @Setter
    private String cargo;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String correo;
    @Getter @Setter
    private String depto;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turno> turnos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rendimiento> rendimientos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistencia> asistencias;

    @Override
    public String toString() {
        return "Usuario{" +
                "rut='" + rut + '\'' +
                ", cargo='" + cargo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", depto='" + depto + '\'' +
                ", turnos=" + turnos +
                ", rendimientos=" + rendimientos +
                ", asistencias=" + asistencias +
                '}';
    }
}
