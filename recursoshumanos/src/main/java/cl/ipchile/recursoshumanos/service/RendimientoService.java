package cl.ipchile.recursoshumanos.service;
import cl.ipchile.recursoshumanos.dto.RendimientoDTO;
import cl.ipchile.recursoshumanos.entity.Rendimiento;
import cl.ipchile.recursoshumanos.repository.RendimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RendimientoService implements IRendimientoService {

    private final RendimientoRepository rendimientoRepository;

    @Autowired
    public RendimientoService(RendimientoRepository rendimientoRepository) {
        this.rendimientoRepository = rendimientoRepository;
    }

    @Override
    public List<RendimientoDTO> getAllRendimientos() {
        return rendimientoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RendimientoDTO> getRendimientoById(Long id) {
        return rendimientoRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<RendimientoDTO> getAllRendimientoByRut(String rut) {
        return rendimientoRepository.findAllByUsuarioRut(rut).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Rendimiento saveRendimiento(Rendimiento rendimiento) {
        return rendimientoRepository.save(rendimiento);
    }

    @Override
    public void deleteRendimientoById(Long id) {
        rendimientoRepository.deleteById(id);
    }

    private RendimientoDTO convertToDTO(Rendimiento rendimiento){
        RendimientoDTO rendimientoDTO = new RendimientoDTO();

        rendimientoDTO.setId(rendimiento.getId());
        rendimientoDTO.setRutUsuario(rendimiento.getUsuario().getRut());
        rendimientoDTO.setPeriodo(rendimiento.getPeriodo());
        rendimientoDTO.setPuntos(rendimiento.getPuntos());

        return rendimientoDTO;
    }
}
