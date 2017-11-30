package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AdminService {

	void addMechanic(String nombre, String apellidos) throws BusinessException;
	
	void deleteMechanic(Long idMechanic) throws BusinessException;
	
	void updateMechanic(Long idMechanic,String nombre,String apellidos) throws BusinessException;
	
	List<Map<String,Object>> findAllMechanics() throws BusinessException;
	
}
