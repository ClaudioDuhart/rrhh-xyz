package cl.ipchile.recursoshumanos.service;

import cl.ipchile.recursoshumanos.dto.UsuarioDTO;
import cl.ipchile.recursoshumanos.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<UsuarioDTO> getAllUsuarios();
    Optional<UsuarioDTO> getUsuarioByRut(String rut);
    Usuario saveUsuario(Usuario usuario);
    Optional<Usuario> verifyUserCredentials(String rut, String contraseña);
    void deleteUsuarioByRut(String rut);
}
