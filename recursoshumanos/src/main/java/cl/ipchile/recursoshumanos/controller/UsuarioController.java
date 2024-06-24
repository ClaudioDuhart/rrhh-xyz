package cl.ipchile.recursoshumanos.controller;

import cl.ipchile.recursoshumanos.dto.LoginRequest;
import cl.ipchile.recursoshumanos.dto.UsuarioDTO;
import cl.ipchile.recursoshumanos.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import cl.ipchile.recursoshumanos.service.IUsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuario")
public class UsuarioController {
    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{rut}")
    public Optional<UsuarioDTO> getUsuarioByRut(@PathVariable String rut) {
        return usuarioService.getUsuarioByRut(rut);
    }

    @PostMapping("/login")
    public ResponseEntity<?> AuthorizeLogin(@RequestBody LoginRequest loginRequest) {
        String rut = loginRequest.getRut();
        String contraseña = loginRequest.getContraseña();

        Optional<Usuario> usuarioOptional = usuarioService.verifyUserCredentials(rut, contraseña);

        if (usuarioOptional.isPresent()) {
            UsuarioDTO usuarioDTO = usuarioService.getUsuarioByRut(rut).orElse(null);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Usuario(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioService.saveUsuario(usuario);
    }

    @DeleteMapping("/{rut}")
    public void deleteUsuarioByRut(@PathVariable String rut) {
        usuarioService.deleteUsuarioByRut(rut);
    }
}