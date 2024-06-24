package cl.ipchile.recursoshumanos.controller;
import cl.ipchile.recursoshumanos.dto.AsistenciaDTO;
import cl.ipchile.recursoshumanos.dto.UsuarioDTO;
import cl.ipchile.recursoshumanos.entity.Asistencia;
import cl.ipchile.recursoshumanos.entity.Usuario;
import cl.ipchile.recursoshumanos.service.IAsistenciaService;
import cl.ipchile.recursoshumanos.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<AsistenciaDTO> getAllAsistencias() {
        return asistenciaService.getAllAsistencias();
    }

    @GetMapping("/por-rut/{rut}")
    public List<AsistenciaDTO> getAllAsistenciasByRut(@PathVariable String rut) {
        return asistenciaService.getAllAsistenciasByRut(rut);
    }

    @GetMapping("/por-id/{id}")
    public Optional<AsistenciaDTO> getAsistenciaById(@PathVariable Long id) {
        return asistenciaService.getAsistenciaById(id);
    }

    @PostMapping("/ingreso")
    public ResponseEntity<?> saveAsistencia(@RequestBody Asistencia asistencia) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.getUsuarioByRut(asistencia.getRutUsuario());
        Usuario usuario = new Usuario();
        boolean band = false;

        if (usuarioOptional.isPresent()) {
            usuario.setRut(usuarioOptional.get().getRut());
            asistencia.setUsuario(usuario);
            band = asistenciaService.saveAsistencia(asistencia);
        }

        if(band)
            return ResponseEntity.ok("Se ha ingresado la asistencia correctamente");

        return new ResponseEntity<>("No se ha podido ingresar la fecha", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/salida")
    public boolean updateAsistencia(@RequestBody Asistencia asistencia) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.getUsuarioByRut(asistencia.getRutUsuario());
        Usuario usuario = new Usuario();

        if (usuarioOptional.isPresent()) {
            usuario.setRut(usuarioOptional.get().getRut());
            return asistenciaService.signExit(usuario.getRut(), asistencia.getFecha(), asistencia.getHoraSalida());
        }

        return false;
    }

    @DeleteMapping("/{id}")
    public void deleteAsistenciaById(@PathVariable Long id) {
        asistenciaService.deleteAsistenciaById(id);
    }
}
