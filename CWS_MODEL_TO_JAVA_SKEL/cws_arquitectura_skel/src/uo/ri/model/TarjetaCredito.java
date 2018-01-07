package uo.ri.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.date.DateUtil;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TTarjetasCredito")
@DiscriminatorValue(value = "TTarjetasCredito")
public class TarjetaCredito extends MedioPago {

	@Column(unique = true)
	private String numero;
	private String tipo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date validez;

	TarjetaCredito() {
	}

	public TarjetaCredito(String numero) {
		super();
		this.validez = DateUtil.tomorrow();
		this.tipo = "UNKNOWN";
		this.numero = numero;
	}

	public TarjetaCredito(String numero, String tipo, Date validez) {
		this(numero);
		this.tipo = tipo;
		this.validez = validez;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getValidez() {
		return validez;

	}

	public void setValidez(Date validez) {
		this.validez = validez;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		TarjetaCredito other = (TarjetaCredito) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TarjetaCredito [numero=" + numero + ", tipo=" + tipo + ", validez=" + validez + ", acumulado="
				+ acumulado + "]";
	}

	/**
	 * Suma el importe al acumulado si es posible
	 */
	@Override
	public void pagar(double importe) throws BusinessException {
		if (isValidNow()) {
			super.acumulado += importe;
		} else {
			throw new BusinessException("Tarjeta de crédito no válida");
		}

	}

	/**
	 * Comprueba la validez de la tarjeta de crédito
	 * 
	 * @return true si es correcta ,falso en caso contrario
	 */
	public boolean isValidNow() {
		if (getValidez().after(DateUtil.today())) {
			return true;
		}
		return false;
	}

}
