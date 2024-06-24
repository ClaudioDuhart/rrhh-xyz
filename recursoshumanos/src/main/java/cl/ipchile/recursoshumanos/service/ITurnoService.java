package cl.ipchile.recursoshumanos.service;

import cl.ipchile.recursoshumanos.dto.RendimientoDTO;
import cl.ipchile.recursoshumanos.dto.TurnoDTO;
import cl.ipchile.recursoshumanos.entity.Turno;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    List<TurnoDTO> getAllTurnos();
    List<TurnoDTO> getAllTurnosByRut(String rut);
    Optional<TurnoDTO> getTurnoById(Long id);
    Turno saveTurno(Turno turno);
    void deleteTurnoById(Long id);
}
