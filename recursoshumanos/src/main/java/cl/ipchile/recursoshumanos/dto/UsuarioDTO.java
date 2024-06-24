package cl.ipchile.recursoshumanos.dto;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO {

    @Getter @Setter
    private String rut;
    @Getter @Setter
    private String cargo;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String correo;
    @Getter @Setter
    private String depto;

    public UsuarioDTO(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public UsuarioDTO() {
    }
}
