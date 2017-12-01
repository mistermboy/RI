package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface MecanicosGateway {

	void setConnection(Connection con);

	void save(String nombre, String apellidos) throws BusinessException;

	void delete(long idMechanic) throws BusinessException;

	List<Map<String, Object>> findAllMechanics() throws BusinessException;

	void update(String nombre, String apellidos, long id) throws BusinessException;

}
