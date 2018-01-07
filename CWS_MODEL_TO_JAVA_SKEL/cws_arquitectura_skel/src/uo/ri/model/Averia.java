package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TAverias", uniqueConstraints = { @UniqueConstraint(columnNames = "VEHICULO_ID, FECHA") })
public class Averia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descripcion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private double importe = 0.0;
	@Enumerated(EnumType.STRING)
	private AveriaStatus status = AveriaStatus.ABIERTA;
	private boolean usada_bono;

	@ManyToOne
	private Vehiculo vehiculo;
	@ManyToOne
	private Mecanico mecanico;
	@ManyToOne
	private Factura factura;
	@OneToMany(mappedBy = "averia")
	private Set<Intervencion> intervenciones = new HashSet<>();

	Averia() {
	}

	public Averia(Vehiculo vehiculo) {
		super();
		this.fecha = new Date();
		Association.Averiar.link(vehiculo, this);
	}

	public Averia(Vehiculo vehiculo, String descripcion) {
		this(vehiculo);
		this.descripcion = descripcion;
	}

	/**
	 * Asigna la averia al mecanico
	 * 
	 * @param mecanico
	 */
	public void assignTo(Mecanico mecanico) {
		if (this.status.equals(AveriaStatus.ABIERTA)) {
			Association.Asignar.link(mecanico, this);
			setStatus(AveriaStatus.ASIGNADA);
		}
	}

	public void desassign() {
		if (this.status.equals(AveriaStatus.ASIGNADA)) {
			Association.Asignar.unlink(mecanico, this);
			this.setStatus(AveriaStatus.ABIERTA);
		}
	}

	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el importe
	 * 
	 */
	public void markAsFinished() {
		if (this.status.equals(AveriaStatus.ASIGNADA)) {
			calcularImporteAveria();
			Association.Asignar.unlink(mecanico, this);
			this.setStatus(AveriaStatus.TERMINADA);

		}

	}

	private void calcularImporteAveria() {
		double acum = 0;
		for (Intervencion intervencion : intervenciones) {
			acum += intervencion.getImporte();
		}
		this.importe = acum;

	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (el primero
	 * no ha podido terminar la reparación), pero debe ser pasada a ABIERTA primero
	 */
	public void reopen() {
		if (this.status.equals(AveriaStatus.TERMINADA)) {
			this.setStatus(AveriaStatus.ABIERTA);
		}

	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 */
	public void markBackToFinished() {
		if (this.status.equals(AveriaStatus.FACTURADA)) {
			this.setStatus(AveriaStatus.TERMINADA);
		}

	}

	/**
	 * Se marca una avería como facturada
	 * 
	 * @throws BusinessException
	 */
	public void markAsInvoiced() throws BusinessException {
		if (this.factura == null) {
			throw new BusinessException("Factura sin asignar");
		}

		if (this.status.equals(AveriaStatus.TERMINADA)) {
			this.setStatus(AveriaStatus.FACTURADA);
		} else {
			throw new BusinessException("Avería no terminada");
		}
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getImporte() {
		return importe;
	}

	public Date getFecha() {
		return (Date) fecha.clone();
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public void setStatus(AveriaStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Averia other = (Averia) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Averia [descripcion=" + descripcion + ", fecha=" + fecha + ", importe=" + importe + ", status=" + status
				+ ", vehiculo=" + vehiculo + "]";
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public Factura getFactura() {
		return factura;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}

	public Long getId() {
		return id;
	}
	
	/**
	 * Devuelve si la avería ha sido usada para bono 3
	 * 
	 * @return true en caso afirmativo,false en caso contrario
	 */
	public boolean isUsadaBono3() {
		return usada_bono;
	}

	public void setUsada_bono(boolean usada_bono) {
		this.usada_bono = usada_bono;
	}

	/**
	 * Comprueba si la factura es elegible para el bono 3
	 * 
	 * @return true en caso afirmativo, false en caso contrario
	 */
	public boolean esElegibleParaBono3() {
		if (!isUsadaBono3()) {
			if (!this.status.equals(AveriaStatus.FACTURADA)) {
				return false;
			} else {
				if (factura.getStatus().equals(FacturaStatus.SIN_ABONAR)) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Marca la avería como usada para un bono
	 * 
	 * @throws BusinessException
	 */
	public void markAsBono3Used() throws BusinessException {
		if (esElegibleParaBono3()) {
			this.setUsada_bono(true);
		} else {
			throw new BusinessException("La avería no puede ser marcada para bono 3");
		}

	}

	/**
	 * Devuelve si la factura está facturada
	 * 
	 * @return true en caso afirmativo,false en caso contrario
	 */
	public boolean isInvoiced() {
		if (getStatus() == AveriaStatus.FACTURADA) {
			return true;
		}
		return false;
	}

}
