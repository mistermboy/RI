package uo.ri.persistence;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import uo.ri.common.BusinessException;

public interface FacturasGateway {

	void setConnection(Connection conection);

	void verificarEstadoAveria(List<Long> idsAveria) throws BusinessException;

	void actualizarEstadoAveria(List<Long> idsAveria, String status) throws BusinessException;

	void vincularAveriaFactura(long idFactura, List<Long> idsAveria) throws BusinessException;

	void insertarFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva) throws BusinessException;

	long recuperarClaveGenerada(long numeroFactura) throws BusinessException;

	Long ultimoNumeroFactura() throws BusinessException;

	void actualizarImporteAveria(Long idAveria, double totalAveria) throws BusinessException;

	double importeRepuestos(Long idAveria) throws BusinessException;

	double importeManoObra(Long idAveria) throws BusinessException;

}
