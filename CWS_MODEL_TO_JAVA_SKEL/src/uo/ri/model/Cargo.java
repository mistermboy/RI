package uo.ri.model;

import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.FacturaStatus;

public class Cargo {

	private Factura factura;
	private MedioPago medioPago;
	private double importe = 0.0;

	public Cargo(Factura factura, MedioPago medioPago, double importe) throws BusinessException {
		// incrementar el importe en el acumulado del medio de pago
		// guardar el importe
		// enlazar (link) factura, este cargo y medioDePago
		super();
		medioPago.acumulado += importe;
		this.importe = importe;
		Association.Cargar.link(factura, this, medioPago);

	}

	/**
	 * Anula (retrocede) este cargo de la factura y el medio de pago Solo se puede
	 * hacer si la factura no está abonada Decrementar el acumulado del medio de
	 * pago Desenlazar el cargo de la factura y el medio de pago
	 * 
	 * @throws BusinessException
	 */
	public void rewind() throws BusinessException {
		// verificar que la factura no esta ABONADA
		if (this.factura.getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			// decrementar acumulado en medio de pago
			medioPago.acumulado -= this.importe;
			// desenlazar factura, cargo y edio de pago
			Association.Cargar.unlink(this);
		}

	}

	public Factura getFactura() {
		return factura;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	void _setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result + ((medioPago == null) ? 0 : medioPago.hashCode());
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
		Cargo other = (Cargo) obj;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (medioPago == null) {
			if (other.medioPago != null)
				return false;
		} else if (!medioPago.equals(other.medioPago))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cargo [factura=" + factura + ", medioPago=" + medioPago + ", importe=" + importe + "]";
	}

}
