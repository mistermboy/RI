package uo.ri.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.date.DateUtil;

@Entity
@Table(name = "TTarjetasCredito")
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
		if (validez != null) {
			return validez;
		} else {
			return DateUtil.tomorrow();
		}
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
}
