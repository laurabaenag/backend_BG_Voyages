package bg.tfg.voyages.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Destinos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_destino;
	private String nombre_destino;
	private String provincia;
	private String pais;

	public Destinos() {
	}

	public Destinos(Long id_destino) {
		super();
		this.id_destino = id_destino;
	}

	public Long getId() {
		return id_destino;
	}

	public void setId(Long id) {
		this.id_destino = id;
	}

	public String getNombre_destino() {
		return nombre_destino;
	}

	public void setNombre_destino(String nombre_destino) {
		this.nombre_destino = nombre_destino;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
