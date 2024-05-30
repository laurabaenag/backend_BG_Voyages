package bg.tfg.voyages.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Actividades {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idActividad;

	@ManyToOne
	@JoinColumn(name = "id_destino")
	private Destinos destino;

	private String nombreActividad;
	private String descripcion;
	private LocalDate fechaActividad;
	private LocalTime horaActividad;
	private String urlImagenActividad;

	public Actividades() {
		super();
	}

	public Actividades(Long idActividad, Destinos destino, String nombreActividad, String descripcion,
			LocalDate fechaActividad, LocalTime horaActividad, String urlImagenActividad) {
		super();
		this.idActividad = idActividad;
		this.destino = destino;
		this.nombreActividad = nombreActividad;
		this.descripcion = descripcion;
		this.fechaActividad = fechaActividad;
		this.horaActividad = horaActividad;
		this.urlImagenActividad = urlImagenActividad;
	}

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public Destinos getDestino() {
		return destino;
	}

	public void setDestino(Destinos destino) {
		this.destino = destino;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(LocalDate fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public LocalTime getHoraActividad() {
		return horaActividad;
	}

	public void setHoraActividad(LocalTime horaActividad) {
		this.horaActividad = horaActividad;
	}

	public String getUrlImagenActividad() {
		return urlImagenActividad;
	}

	public void setUrlImagenActividad(String urlImagenActividad) {
		this.urlImagenActividad = urlImagenActividad;
	}

}
