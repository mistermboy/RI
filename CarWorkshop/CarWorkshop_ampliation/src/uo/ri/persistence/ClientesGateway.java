package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ClientesGateway {

	void setConnection(Connection con);

	void save(String dni, String nombre, String apellidos, int zipcode, int telefono, String correo)
			throws BusinessException;

	/**
	 * Registra a un cliente que ha sido recomendado
	 * 
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param zipcode
	 * @param telefono
	 * @param correo
	 * @param idRecomendador
	 * @throws BusinessException
	 */
	void saveWithRecomendator(String dni, String nombre, String apellidos, int zipcode, int telefono, String correo,
			Long idRecomendador) throws BusinessException;

	void delete(long idClient) throws BusinessException;

	void update(long id, String dni, String nombre, String apellidos, int zipcode, int telefono, String correo)
			throws BusinessException;

	/**
	 * Devuelve todos los detalles de un cliente en un String
	 * 
	 * @param idClient
	 * @return
	 * @throws BusinessException
	 */
	String showClient(long idClient) throws BusinessException;

	List<Map<String, Object>> findAllClients() throws BusinessException;

	/**
	 * Devuelve todos los ids de los clientes en una lista
	 * 
	 * @return
	 * @throws BusinessException
	 */
	List<Long> findAllClientsId() throws BusinessException;

	/**
	 * Devuelve una lista con todos los clientes que han sido recomendados por otro
	 * cliente que se le pasa como parámetro
	 * 
	 * @param idRecomendator
	 * @return
	 * @throws BusinessException
	 */
	List<Map<String, Object>> findAllClientsByRecomendator(long idRecomendator) throws BusinessException;

}
