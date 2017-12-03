package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ClientesGateway {

	void setConnection(Connection con);

	void save(String dni, String nombre, String apellidos, int zipcode, int telefono, String correo)
			throws BusinessException;

	void saveWithRecomendator(String dni, String nombre, String apellidos, int zipcode, int telefono, String correo,
			Long idRecomendador) throws BusinessException;

	void delete(long idClient) throws BusinessException;

	void update(long id, String dni, String nombre, String apellidos, int zipcode, int telefono, String correo)
			throws BusinessException;

	String showClient(long idClient) throws BusinessException;

	List<Map<String, Object>> findAllClients() throws BusinessException;

	List<Long> findAllClientsId() throws BusinessException;

	List<Map<String, Object>> findAllClientsByRecomendator(long idRecomendator) throws BusinessException;

}
