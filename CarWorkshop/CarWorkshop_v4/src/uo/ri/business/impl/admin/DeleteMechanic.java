package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class DeleteMechanic {

	private long idMechanic;

	public DeleteMechanic(long idMechanic) {
		this.idMechanic = idMechanic;
	}

	public void execute() throws BusinessException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			MecanicosGateway mGate = PersistenceFactory.getMecanicosGateway();
			mGate.setConnection(c);
			
			mGate.delete(idMechanic);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}catch (BusinessException e) {
			throw new BusinessException("Error eliminando un mecánico");
		} finally {
			Jdbc.close(c);
		}
	}

}
