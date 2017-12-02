package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.BonosGateway;
import uo.ri.persistence.ClientesGateway;

public class GenerateBonos {
	
	
	public void execute() throws BusinessException {

		Connection c = null;
		List<Long> idsClientes = new ArrayList<Long>();
		List<Long> idsVehiculos = new ArrayList<Long>();
		List<Long> idsAverias = new ArrayList<Long>();

		try {
			c = Jdbc.getConnection();

			BonosGateway bGate = PersistenceFactory.getBonosGateway();
			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			bGate.setConnection(c);
			cGate.setConnection(c);
			
			idsClientes = cGate.findAllClientsId();
			for(Long idC:idsClientes) {
				idsVehiculos = bGate.getVehiculosByIdClientes(idC);
				for(Long idV:idsVehiculos) {
			//		idsAverias=
				}
			}
			
			
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}
	

}
