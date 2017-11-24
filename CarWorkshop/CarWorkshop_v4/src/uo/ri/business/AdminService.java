package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface AdminService {

	void addMechanic(String nombre,String apellidos);
	
	void deleteMechanic(Long idMechanic);
	
	void updateMechanic(Long idMechanic,String nombre,String apellidos);
	
	List<Map<String,Object>> findAllMechanics();
	
}
