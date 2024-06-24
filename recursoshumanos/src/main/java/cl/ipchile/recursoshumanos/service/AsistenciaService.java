package cl.ipchile.recursoshumanos.service;

import cl.ipchile.recursoshumanos.dto.AsistenciaDTO;
import cl.ipchile.recursoshumanos.dto.RendimientoDTO;
import cl.ipchile.recursoshumanos.entity.Asistencia;
import cl.ipchile.recursoshumanos.entity.Rendimiento;
import cl.ipchile.recursoshumanos.entity.Turno;
import cl.ipchile.recursoshumanos.repository.AsistenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsistenciaService implements IAsistenciaService{
    private final AsistenciaRepository asistenciaRepository;

    private Logger log = LoggerFactory.getLogger(AsistenciaService.class);

    @Autowired
    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }
    @Override
    public List<AsistenciaDTO> getAllAsistencias() {
        return asistenciaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AsistenciaDTO> getAsistenciaById(Long id) {
        return asistenciaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<AsistenciaDTO> getAllAsistenciasByRut(String rut) {
        return asistenciaRepository.findAllByUsuarioRut(rut).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveAsistencia(Asistencia asistencia) {
        List<AsistenciaDTO> asistenciaDTOS = getAllAsistenciasByRut(asistencia.getRutUsuario());

        for (AsistenciaDTO elem : asistenciaDTOS) {
            if (elem.getFecha().equals(asistencia.getFecha())) {
                return false;
            }
        }

        asistenciaRepository.save(asistencia);

        return true;
    }

    @Override
    public void deleteAsistenciaById(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    public boolean signExit(String rut, String fecha, String hora) {
        log.info("rut: " +rut+ "fecha: "+ fecha +", horaa: "+hora);

        Asistencia asistencia = asistenciaRepository.getAsistencia(rut, fecha);

        asistencia.setHoraSalida(hora);
        asistencia.setMarcajeSalida(true);

        asistenciaRepository.save(asistencia);
        return true;
    }


    private AsistenciaDTO convertToDTO(Asistencia asistencia){
        AsistenciaDTO asistenciaDTO = new AsistenciaDTO();

        asistenciaDTO.setId(asistencia.getId());
        asistenciaDTO.setRutUsuario(asistencia.getUsuario().getRut());
        asistenciaDTO.setFecha(asistencia.getFecha());
        asistenciaDTO.setHoraEntrada(asistencia.getHoraEntrada());
        asistenciaDTO.setMarcajeEntrada(asistencia.getMarcajeEntrada());
        asistenciaDTO.setHoraSalida(asistencia.getHoraSalida());
        asistenciaDTO.setMarcajeSalida(asistencia.getMarcajeSalida());

        return asistenciaDTO;
    }
}
