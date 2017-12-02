package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ForemanService {
	
	 void addClient(String dni,String nombre, String apellidos,int zipcode,int telefono,String correo) throws BusinessException;
	 
	 void deleteClient(Long idClient) throws BusinessException;
	 
	 List<Map<String, Object>> findAllClients() throws BusinessException;
	 
	 void updateClient(Long idClient,String nombre, String apellidos)  throws BusinessException;
	 
	 String showDetailClient(Long idClient)  throws BusinessException;
	
}
