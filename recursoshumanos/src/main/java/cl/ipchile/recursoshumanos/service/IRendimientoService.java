package cl.ipchile.recursoshumanos.service;

import cl.ipchile.recursoshumanos.dto.RendimientoDTO;
import cl.ipchile.recursoshumanos.entity.Rendimiento;

import java.util.List;
import java.util.Optional;

public interface IRendimientoService {
    List<RendimientoDTO> getAllRendimientos();
    Optional<RendimientoDTO> getRendimientoById(Long id);
    List<RendimientoDTO> getAllRendimientoByRut(String rut);
    Rendimiento saveRendimiento(Rendimiento rendimiento);
    void deleteRendimientoById(Long id);
}
