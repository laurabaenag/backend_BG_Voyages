package bg.tfg.voyages.service;

import java.util.List;

import bg.tfg.voyages.model.MensajesContacto;

public interface MensajeContactoService {
	MensajesContacto save(MensajesContacto entity);

	List<MensajesContacto> obtenerTodosLosMensajes();
}
