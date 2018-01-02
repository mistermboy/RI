package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TMetalicos")
public class Metalico extends MedioPago {

	Metalico(){}
	
	public Metalico(Cliente cliente) {
		Association.Pagar.link(cliente,this);
	}
	
}
