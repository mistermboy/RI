package uo.ri.persistence;

import java.sql.Connection;

import uo.ri.common.BusinessException;

public interface MediospagoGateway {

	void setConnection(Connection conection);

	/**
	 * Crea un bono de tipo TMetalico en la tabla TMediospago
	 * @param idCLiente
	 * @param codigo
	 * @throws BusinessException
	 */
	void createBonos(Long idCLiente, String codigo) throws BusinessException;

	/**
	 * Devuelve el código del último bono de tipo TMetálico
	 * @return
	 * @throws BusinessException
	 */
	String getLastBonoCode() throws BusinessException;

}
