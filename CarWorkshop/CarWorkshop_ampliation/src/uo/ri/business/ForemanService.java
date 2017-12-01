package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ForemanService {
	
	 void addClient(String dni,String nombre, String apellidos,int cPostal,int telefono,String correo) throws BusinessException;
	 
	 void removeClient(String dni) throws BusinessException;
	 
	 List<Map<String, Object>> findAllClients() throws BusinessException;
	 
	 void updateClient(String dni,String nombre, String apellidos,int cPostal,int telefono,String correo)  throws BusinessException;
	 
	 String showDetailClient(String dni)  throws BusinessException;
	
}
