package uo.ri.business.repository;

import uo.ri.model.Factura;

public interface FacturaRepository extends Repository<Factura> {

	Factura findByNumber(Long numero);
	Long getNextInvoiceNumber();
}
