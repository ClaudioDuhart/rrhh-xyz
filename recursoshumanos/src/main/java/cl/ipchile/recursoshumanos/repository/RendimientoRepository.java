package cl.ipchile.recursoshumanos.repository;

import cl.ipchile.recursoshumanos.entity.Rendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RendimientoRepository extends JpaRepository<Rendimiento, Long> {

    @Query("SELECT r FROM Rendimiento r WHERE r.usuario.rut = :rut")
    List<Rendimiento> findAllByUsuarioRut(@Param("rut") String rut);
}