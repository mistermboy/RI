package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uo.ri.model.types.FacturaStatus;

public class Factura {

	private Long numero;
	private Date fecha;
	private double importe;
	private double iva;
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;

	private Set<Averia> averias = new HashSet<>();

	public Factura(Long numero) {
		super();
		this.numero = numero;
	}

	public Factura(Long numero, Date fecha) {
		this(numero);
		this.fecha = fecha;
	}

	/**
	 * Añade la averia a la factura
	 * 
	 * @param averia
	 */
	public void addAveria(Averia averia) {
		// Verificar que la factura está en estado SIN_ABONAR
		// Verificar que La averia está TERMINADA
		// linkar factura y averia
		// marcar la averia como FACTURADA ( averia.markAsInvoiced() )
		// calcular el importe
	}

	/**
	 * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de
	 * factura
	 */
	void calcularImporte() {
		// iva = ...
		// importe = ...
	}

	/**
	 * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula el
	 * importe
	 * 
	 * @param averia
	 */
	public void removeAveria(Averia averia) {
		// verificar que la factura está sin abonar
		// desenlazar factura y averia
		// la averia vuelve al estado FINALIZADA ( averia.markBackToFinished() )
		// TODO Auto-generated method stub
		// volver a calcular el importe
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public FacturaStatus getStatus() {
		return status;
	}

	public void setStatus(FacturaStatus status) {
		this.status = status;
	}

	public Long getNumero() {
		return numero;
	}

	public double getImporte() {
		return importe;
	}

	Set<Averia> _getAverias() {
		return averias;
	}

	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

}
