package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.common.BusinessException;

public interface BonosGateway {

	void setConnection(Connection conection);

	/**
	 * Devuelve una lista con los ids de los vehículos de un cliente que se le pasa
	 * como parámetro
	 * 
	 * @param idCliente
	 * @return
	 * @throws BusinessException
	 */
	List<Long> getVehiculosByIdCliente(Long idCliente) throws BusinessException;

	/**
	 * Devuelve una lista con los ids de las averias de un vehículo que se le pasa
	 * como parámetro
	 * 
	 * @param idVehiculo
	 * @return
	 * @throws BusinessException
	 */
	List<Long> getAveriasByIdVehiculo(Long idVehiculo) throws BusinessException;

}
