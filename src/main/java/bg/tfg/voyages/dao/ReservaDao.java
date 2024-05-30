package bg.tfg.voyages.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import bg.tfg.voyages.model.Reservas;

public interface ReservaDao extends JpaRepository<Reservas, Long> {
	@Query("SELECT r FROM Reservas r WHERE r.usuarios.id = :usuarioId")
	List<Reservas> findByUsuarioId(@Param("usuarioId") Long usuarioId);

	@Modifying
	@Transactional
	@Query("DELETE FROM Reservas r WHERE r.actividades.idActividad = :actividadId")
	void deleteByActividadId(@Param("actividadId") Long actividadId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Reservas r WHERE r.usuarios.id = :usuarioId")
	void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);
}
