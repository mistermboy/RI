package uo.ri.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TMetalicos")
@DiscriminatorValue(value = "TMetalicos")
public class Metalico extends MedioPago {

	Metalico() {
	}

	public Metalico(Cliente cliente) {
		Association.Pagar.link(cliente, this);
	}

	/**
	 * Suma el importe al acumulado
	 */
	@Override
	public void pagar(double importe) {
		super.acumulado += importe;

	}

}
