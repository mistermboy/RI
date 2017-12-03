package uo.ri.persistence;

import java.sql.Connection;

import uo.ri.common.BusinessException;

public interface MediospagoGateway {

	void setConnection(Connection conection);

	void createBonos(Long idCLiente, String codigo) throws BusinessException;

	String getLastBonoCode() throws BusinessException;

}
