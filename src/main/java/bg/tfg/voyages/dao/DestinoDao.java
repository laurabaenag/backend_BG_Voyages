package bg.tfg.voyages.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bg.tfg.voyages.model.Destinos;

public interface DestinoDao extends JpaRepository<Destinos, Long> {
//	List<Destinos> findByNombreContainingIgnoreCase(String nombre);
	List<Destinos> findByProvinciaContainingIgnoreCase(String provincia);

	List<Destinos> findByPaisContainingIgnoreCase(String pais);

	@Query("SELECT d FROM Destinos d WHERE d.nombre_destino = :nombre")
	Destinos findByNombreDestino(@Param("nombre") String nombre);
}
