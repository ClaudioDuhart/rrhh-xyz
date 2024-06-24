package cl.ipchile.recursoshumanos.dto;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {

    @Getter @Setter
    private String rut;
    @Getter @Setter
    private String contraseña;

    public LoginRequest(String s, String password) {
    }
}
