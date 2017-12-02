package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class DeleteClient {

	private Long idClient;

	public DeleteClient(Long idClient) {
		this.idClient = idClient;
	}

	public void execute() throws BusinessException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			cGate.setConnection(c);

			cGate.delete(idClient);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
