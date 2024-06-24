package cl.ipchile.recursoshumanos.repository;

import cl.ipchile.recursoshumanos.entity.Asistencia;
import cl.ipchile.recursoshumanos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    @Query("SELECT a FROM Asistencia a WHERE a.usuario.rut = :rut")
    List<Asistencia> findAllByUsuarioRut(@Param("rut") String rut);

    @Query("SELECT a FROM Asistencia a WHERE a.usuario.rut = :rut AND a.fecha = :fecha")
    Asistencia getAsistencia(@Param("rut") String rut, @Param("fecha") String fecha);
}