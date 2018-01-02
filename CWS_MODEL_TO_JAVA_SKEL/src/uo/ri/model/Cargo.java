package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import alb.util.date.DateUtil;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TCargos", uniqueConstraints = { @UniqueConstraint(columnNames = "FACTURA_ID, MEDIOPAGO_ID") })
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Factura factura;
	@ManyToOne
	private MedioPago medioPago;
	private double importe = 0.0;

	Cargo() {
	}

	public Long getId() {
		return id;
	}

	public Cargo(Factura factura, MedioPago medioPago, double importe) throws BusinessException {
		super();
		Association.Cargar.link(factura, this, medioPago);
		if (medioPago instanceof Bono) {
			((Bono) medioPago).pagar(importe);
		} else if (medioPago instanceof TarjetaCredito) {
			if (checkValidez()) {
				medioPago.acumulado += importe;
			} else {
				throw new BusinessException("La fecha de la tarjeta no es válida");
			}
		} else {
			medioPago.acumulado += importe;
		}
		this.importe = importe;

	}

	/**
	 * Comprueba la validez de la tarjeta de crédito
	 * 
	 * @return true si es correcta ,falso en caso contrario
	 */
	private boolean checkValidez() {
		if (((TarjetaCredito) medioPago).getValidez().after(DateUtil.today())) {
			return true;
		}
		return false;
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
