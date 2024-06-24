package cl.ipchile.recursoshumanos.controller;

import cl.ipchile.recursoshumanos.dto.AsistenciaDTO;
import cl.ipchile.recursoshumanos.dto.RendimientoDTO;
import cl.ipchile.recursoshumanos.dto.UsuarioDTO;
import cl.ipchile.recursoshumanos.entity.Rendimiento;
import cl.ipchile.recursoshumanos.entity.Usuario;
import cl.ipchile.recursoshumanos.service.IRendimientoService;
import cl.ipchile.recursoshumanos.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/api/rendimiento")
public class RendimientoController {

    @Autowired
    private IRendimientoService rendimientoService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<RendimientoDTO> getAllRendimientos() {
        return rendimientoService.getAllRendimientos();
    }

    @GetMapping("/{id}")
    public Optional<RendimientoDTO> getRendimientoById(@PathVariable Long id) {
        return rendimientoService.getRendimientoById(id);
    }

    @GetMapping("/por-rut/{rut}")
    public List<RendimientoDTO> getAllRendimientoByRut(@PathVariable String rut) {
        return rendimientoService.getAllRendimientoByRut(rut);
    }

    @PostMapping
    public Rendimiento saveRendimiento(@RequestBody Rendimiento rendimiento) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.getUsuarioByRut(rendimiento.getRutUsuario());
        Usuario usuario = new Usuario();


        if (usuarioOptional.isPresent()) {
            usuario.setRut(usuarioOptional.get().getRut());
            rendimiento.setUsuario(usuario);
            return rendimientoService.saveRendimiento(rendimiento);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRendimientoById(@PathVariable Long id) {
        rendimientoService.deleteRendimientoById(id);
    }
}
