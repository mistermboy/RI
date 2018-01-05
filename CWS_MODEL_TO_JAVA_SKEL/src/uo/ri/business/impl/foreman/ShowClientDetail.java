package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class ShowClientDetail {

	private Long idClient;

	public ShowClientDetail(Long idClient) {
		this.idClient = idClient;
	}

	public String execute() throws BusinessException {

		Connection c = null;
		String details = "";

		try {
			c = Jdbc.getConnection();

			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			cGate.setConnection(c);

			details = cGate.showClient(idClient);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
		return details;
	}

}
