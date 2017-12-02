package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;

import uo.ri.common.BusinessException;

public interface BonosGateway {

	void setConnection(Connection conection);

	List<Long> getVehiculosByIdClientes(Long idCliente) throws BusinessException;

	List<Long> getAveriasByIdVehiculos(Long idVehiculo) throws BusinessException;

}
