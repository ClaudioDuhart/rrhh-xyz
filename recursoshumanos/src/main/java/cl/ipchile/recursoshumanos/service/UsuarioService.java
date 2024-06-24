package cl.ipchile.recursoshumanos.service;

import cl.ipchile.recursoshumanos.controller.UsuarioController;
import cl.ipchile.recursoshumanos.dto.UsuarioDTO;
import cl.ipchile.recursoshumanos.entity.Usuario;
import cl.ipchile.recursoshumanos.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Logger log = LoggerFactory.getLogger(UsuarioService.class);


    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> getUsuarioByRut(String rut) {
        return usuarioRepository.findById(rut)
                .map(this::convertToDTO);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> verifyUserCredentials(String rut, String contraseña) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(rut);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(contraseña, usuario.getContraseña())) {
                return Optional.of(usuario);
            }
        }


        return Optional.empty();
    }

    public void deleteUsuarioByRut(String rut) {
        usuarioRepository.deleteById(rut);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setRut(usuario.getRut());
        usuarioDTO.setCargo(usuario.getCargo());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setDepto(usuario.getDepto());

        return usuarioDTO;
    }
}
