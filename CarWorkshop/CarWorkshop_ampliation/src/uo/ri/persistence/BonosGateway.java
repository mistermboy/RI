package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.common.BusinessException;

public interface BonosGateway {

	void setConnection(Connection conection);

	List<Long> getVehiculosByIdCliente(Long idCliente) throws BusinessException;

	List<Long> getAveriasByIdVehiculo(Long idVehiculo) throws BusinessException;

}
