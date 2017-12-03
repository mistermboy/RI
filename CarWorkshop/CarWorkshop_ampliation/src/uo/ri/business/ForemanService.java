package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ForemanService {

	void addClient(String dni, String nombre, String apellidos, int zipcode, int telefono, String correo)
			throws BusinessException;

	void addClientWithRecomendator(String dni, String nombre, String apellidos, int zipcode, int telefono,
			String correo, long idRecomendador) throws BusinessException;

	void deleteClient(Long idClient) throws BusinessException;

	List<Map<String, Object>> findAllClients() throws BusinessException;
	
	List<Map<String, Object>> findAllClientsByRecomendator(Long idRecomendador) throws BusinessException;

	void updateClient(long idClient, String dni, String nombre, String apellidos, int zipcode, int telefono,
			String correo) throws BusinessException;

	String showDetailClient(Long idClient) throws BusinessException;



}
