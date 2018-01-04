package uo.ri.persistence.jpa;

import uo.ri.business.repository.FacturaRepository;
import uo.ri.model.Factura;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

public class FacturaJpaRepository 
		extends BaseRepository<Factura> 
		implements FacturaRepository {

	@Override
	public Factura findByNumber(Long numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNextInvoiceNumber() {
		return Jpa.getManager()
				.createNamedQuery("Factura.getNextInvoiceNumber", Long.class)
				.getSingleResult();
	}

}
