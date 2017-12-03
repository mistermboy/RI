package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.BonosGateway;
import uo.ri.persistence.ClientesGateway;
import uo.ri.persistence.MediospagoGateway;

public class GenerateBonos {

	public void execute() throws BusinessException {

		Connection c = null;
		List<Long> idsClientes = new ArrayList<Long>();
		List<Long> idsVehiculos = new ArrayList<Long>();
		List<Long> idsAverias = new ArrayList<Long>();
		
		List<Long> acumAverias = new ArrayList<Long>();

		try {
			c = Jdbc.getConnection();

			BonosGateway bGate = PersistenceFactory.getBonosGateway();
			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			bGate.setConnection(c);
			cGate.setConnection(c);

			idsClientes = cGate.findAllClientsId();
			for (Long idC : idsClientes) {
				idsVehiculos = bGate.getVehiculosByIdCliente(idC);
				for (Long idV : idsVehiculos) {
					idsAverias = bGate.getAveriasByIdVehiculo(idV);
					for(Long averia:idsAverias) {
						acumAverias.add(averia);
					}
				}
				createBono(acumAverias, idC, c);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

	private void createBono(List<Long> idsAverias, Long idC, Connection c) throws BusinessException {

		AveriasGateway aGate = PersistenceFactory.getAveriasGateway();
		MediospagoGateway mGate = PersistenceFactory.getMediospagoGateway();
		aGate.setConnection(c);
		mGate.setConnection(c);

		int moduloAverias = idsAverias.size() % 3;
		int nAverias = idsAverias.size() - moduloAverias;
		int nBonos = nAverias / 3;

		if (nAverias > 2) {
			for (int i = 0; i < nAverias; i++) {
				aGate.insertBonoAveria(idsAverias.get(i));
			}
		}

		if (nBonos > 0) {
			for (int i = 0; i < nBonos; i++) {
				mGate.createBonos(idC);
			}
		}

	}

}
