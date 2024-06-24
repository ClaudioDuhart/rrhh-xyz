package cl.ipchile.recursoshumanos.repository;

import cl.ipchile.recursoshumanos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.rut = :rut and u.contraseña = :contraseña")
    Optional<Usuario> findByRutAndContraseña(@Param("rut") String rut, @Param("contraseña") String contraseña);

}