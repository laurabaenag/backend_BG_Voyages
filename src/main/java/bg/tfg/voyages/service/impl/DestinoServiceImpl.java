package bg.tfg.voyages.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.tfg.voyages.dao.DestinoDao;
import bg.tfg.voyages.model.Destinos;
import bg.tfg.voyages.service.DestinoService;

@Service
public class DestinoServiceImpl implements DestinoService {
	
	@Autowired
	private DestinoDao destinoDAO;

//	@Override
//	public List<Destinos> findByNombreContainingIgnoreCase(String nombre) {
//		return destinoDAO.findByNombreContainingIgnoreCase(nombre);
//	}

	@Override
	public List<Destinos> findAll() {
		return destinoDAO.findAll();
	}

	@Override
	public List<Destinos> findByProvinciaContainingIgnoreCase(String provincia) {
		return destinoDAO.findByProvinciaContainingIgnoreCase(provincia);
	}

	@Override
	public List<Destinos> findByPaisContainingIgnoreCase(String pais) {
		return destinoDAO.findByPaisContainingIgnoreCase(pais);
	}

	@Override
	public Destinos getById(Long id) {
		return destinoDAO.getById(id);
	}

	@Override
	public void deleteById(Long id) {
		destinoDAO.deleteById(id);
	}

	@Override
	public void delete(Destinos entity) {
		destinoDAO.delete(entity);
	}

	@Override
	public Destinos save(Destinos entity) {
		return destinoDAO.save(entity);
	}

	@Override
	public Optional<Destinos> findById(Long id) {
		return destinoDAO.findById(id);
	}

}
