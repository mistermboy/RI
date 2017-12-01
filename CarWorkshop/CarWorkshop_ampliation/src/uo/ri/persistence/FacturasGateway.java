package uo.ri.persistence;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import uo.ri.common.BusinessException;

public interface FacturasGateway {

	void setConnection(Connection conection);

	void vincularAveriaFactura(long idFactura, List<Long> idsAveria) throws BusinessException;

	void insertarFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
			throws BusinessException;

	long recuperarClaveGenerada(long numeroFactura) throws BusinessException;

	Long ultimoNumeroFactura() throws BusinessException;

}
