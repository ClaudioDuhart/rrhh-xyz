package cl.ipchile.recursoshumanos.service;

import cl.ipchile.recursoshumanos.controller.UsuarioController;
import cl.ipchile.recursoshumanos.dto.TurnoDTO;
import cl.ipchile.recursoshumanos.entity.Turno;
import cl.ipchile.recursoshumanos.repository.TurnoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoService implements ITurnoService {
    private Logger log = LoggerFactory.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public List<TurnoDTO> getAllTurnos() {
        return turnoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());    }

    @Override
    public Optional<TurnoDTO> getTurnoById(Long id) {
        return turnoRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public Turno saveTurno(Turno turno) {

        log.info(turno.getRutUsuario());
        try {
            return turnoRepository.save(turno);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("El usuario ya tiene un turno registrado en la fecha especificada");
        }
    }

    @Override
    public void deleteTurnoById(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public List<TurnoDTO> getAllTurnosByRut(String rut) {
        return turnoRepository.findAllTurnosByUsuarioRut(rut).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TurnoDTO convertToDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setRutUsuario(turno.getUsuario().getRut());
        turnoDTO.setTurnoEntrada(turno.getTurnoEntrada());
        turnoDTO.setTurnoSalida(turno.getTurnoSalida());
        turnoDTO.setFecha(turno.getFecha());
        return turnoDTO;
    }
}
