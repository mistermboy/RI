package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uo.ri.model.types.AveriaStatus;

public class Averia {

	private String descripcion;
	private Date fecha;
	private double importe = 0.0;
	private AveriaStatus status = AveriaStatus.ABIERTA;

	private Vehiculo vehiculo;
	private Mecanico mecanico;
	private Factura factura;

	private Set<Intervencion> intervenciones = new HashSet<>();

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
		// Solo se puede asignar una averia que está ABIERTA
		if (this.status.equals(AveriaStatus.ABIERTA)) {
			// linkado de averia y mecanico
			Association.Asignar.link(mecanico, this);
			// la averia pasa a ASIGNADA
			this.setStatus(AveriaStatus.ASIGNADA);
		}

	}

	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el importe
	 * 
	 */
	public void markAsFinished() {
		// Se verifica que está en estado ASIGNADA
		if (this.status.equals(AveriaStatus.ASIGNADA)) {
			// se calcula el importe
			calcularImporteAveria();
			// se desvincula mecanico y averia
			Association.Asignar.unlink(mecanico, this);
			// el status cambia a TERMINADA
			this.setStatus(AveriaStatus.TERMINADA);

		}

	}

	private void calcularImporteAveria() {
		double acum = 0;
		for (Intervencion intervencion:intervenciones) {
			acum += intervencion.getImporte();
		}
		this.importe = acum;

	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (el primero
	 * no ha podido terminar la reparación), pero debe ser pasada a ABIERTA primero
	 */
	public void reopen() {
		// Solo se puede reabrir una averia que está TERMINADA
		if (this.status.equals(AveriaStatus.TERMINADA)) {
			// la averia pasa a ABIERTA
			this.setStatus(AveriaStatus.ABIERTA);
		}

	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 */
	public void markBackToFinished() {
		// verificar que la averia está FACTURADA
		if (this.status.equals(AveriaStatus.FACTURADA)) {
			// cambiar status a TERMINADA
			this.setStatus(AveriaStatus.TERMINADA);
		}

	}

	/**
	 * Se marca una avería como facturada
	 */
	public void markAsInvoiced() {
		// verificar que la averia está TERMINADA
		if (this.status.equals(AveriaStatus.TERMINADA)) {
			// cambiar status a FACTURADA
			this.setStatus(AveriaStatus.FACTURADA);
		}
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public void setStatus(AveriaStatus status) {
		this.status = status;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
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

	public Date getFecha() {
		return fecha;
	}

	public double getImporte() {
		return importe;
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

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}

}
