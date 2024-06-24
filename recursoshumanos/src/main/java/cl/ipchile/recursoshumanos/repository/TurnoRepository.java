package cl.ipchile.recursoshumanos.repository;

import cl.ipchile.recursoshumanos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    @Query("SELECT t FROM Turno t WHERE t.usuario.rut = :rut")
    List<Turno> findAllTurnosByUsuarioRut(@Param("rut") String rut);



}
