package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class UpdateClient {

	private Long idClient;
	private String nombre;
	private String apellidos;

	public UpdateClient(Long idClient, String nombre, String apellidos) {
		this.idClient = idClient;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() throws BusinessException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			cGate.setConnection(c);

			cGate.update(idClient, nombre, apellidos);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
