package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface BonosGateway {

	void setConnection(Connection conection);
	
	List<Map<String, Object>> getVehiculosByClientes();
	
}
