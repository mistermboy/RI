package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ClientesGateway {

	void setConnection(Connection con);

	void save(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo)
			throws BusinessException;

	void delete(long idClient) throws BusinessException;

	void update(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo,long idClient)
			throws BusinessException;

	String showClient(long idClient) throws BusinessException;

	List<Map<String, Object>> findAllClients() throws BusinessException;
}
