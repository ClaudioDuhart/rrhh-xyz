package cl.ipchile.recursoshumanos.controller;

import cl.ipchile.recursoshumanos.dto.TurnoDTO;
import cl.ipchile.recursoshumanos.dto.UsuarioDTO;
import cl.ipchile.recursoshumanos.entity.Turno;
import cl.ipchile.recursoshumanos.entity.Usuario;
import cl.ipchile.recursoshumanos.service.ITurnoService;
import cl.ipchile.recursoshumanos.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/turno")
public class TurnoController {

    @Autowired
    private ITurnoService turnoService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<TurnoDTO> getAllTurnos() {
        return turnoService.getAllTurnos();
    }

    @GetMapping("/por-rut/{rut}")
    public List<TurnoDTO> getAllTurnosByRut(@PathVariable String rut) {
        return turnoService.getAllTurnosByRut(rut);
    }

    @GetMapping("/{id}")
    public Optional<TurnoDTO> getTurnoById(@PathVariable Long id) {
        return turnoService.getTurnoById(id);
    }

    @PostMapping
    public Turno saveTurno(@RequestBody Turno turno) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.getUsuarioByRut(turno.getRutUsuario());
        Usuario usuario = new Usuario();

        if (usuarioOptional.isPresent()) {
            usuario.setRut(usuarioOptional.get().getRut());
            turno.setUsuario(usuario);
            return turnoService.saveTurno(turno);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTurnoById(@PathVariable Long id) {
        turnoService.deleteTurnoById(id);
    }
}
