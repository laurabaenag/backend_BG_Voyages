package bg.tfg.voyages.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.tfg.voyages.dao.ActividadDao;
import bg.tfg.voyages.dao.DestinoDao;
import bg.tfg.voyages.model.Actividades;
import bg.tfg.voyages.model.Destinos;
import bg.tfg.voyages.service.ActividadService;

@Service
public class ActividadServiceImpl implements ActividadService {
	
	@Autowired
	private ActividadDao actividadDAO;
	
	@Autowired
    private DestinoDao destinoDAO;

	@Override
	public List<Actividades> findByFechaActividad(LocalDate fecha) {
		return actividadDAO.findByFechaActividad(fecha);
	}

	@Override
	public List<Actividades> findAll() {
		return actividadDAO.findAll();
	}

	@Override
	public Actividades getById(Long id) {
		return actividadDAO.getById(id);
	}

	@Override
	public void deleteById(Long id) {
		actividadDAO.deleteById(id);
	}

	@Override
	public void delete(Actividades entity) {
		actividadDAO.delete(entity);
	}

	@Override
	public Actividades save(Actividades entity) {
		return actividadDAO.save(entity);
	}

	@Override
	public Optional<Actividades> findById(Long id) {
		return actividadDAO.findById(id);
	}

	@Override
	public List<Actividades> getActividadesByNombreDestinoAndFecha(String nombre_destino, LocalDate fecha) {
		Destinos destino = destinoDAO.findByNombreDestino(nombre_destino);
        if (destino != null) {
            return actividadDAO.findByDestinoIdAndFecha(destino.getId(), fecha);
        } else {
            return List.of(); // Devuelve una lista vacía si no se encuentra el destino
        }
	}

	@Override
	public List<Actividades> getActividadesByDestino(String nombre_destino) {
		Destinos destino = destinoDAO.findByNombreDestino(nombre_destino);
		if (destino != null) {
            return actividadDAO.findByDestino(destino.getId());
        } else {
            return List.of(); // Devuelve una lista vacía si no se encuentra el destino
        }
	}

	@Override
	public List<Actividades> getActividadesByIdDestino(Long idDestino) {
		return actividadDAO.findByDestino(idDestino);
	}

	@Override
	public void deleteByDestinoId(Long destinoId) {
		actividadDAO.deleteByDestinoId(destinoId);		
	}

}
