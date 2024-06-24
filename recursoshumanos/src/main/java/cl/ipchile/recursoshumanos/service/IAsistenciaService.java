package cl.ipchile.recursoshumanos.service;

import cl.ipchile.recursoshumanos.dto.AsistenciaDTO;
import cl.ipchile.recursoshumanos.entity.Asistencia;

import java.util.List;
import java.util.Optional;

public interface IAsistenciaService {
    List<AsistenciaDTO> getAllAsistencias();
    Optional<AsistenciaDTO> getAsistenciaById(Long id);
    List<AsistenciaDTO> getAllAsistenciasByRut(String rut);
    boolean signExit(String rut, String fecha, String hora);
    boolean saveAsistencia(Asistencia asistencia);
    void deleteAsistenciaById(Long id);
}
