package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class UpdateMechanic {

	private long id;
	private String nombre;
	private String apellidos;

	public UpdateMechanic(long id, String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.id = id;
	}

	public void execute() throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			MecanicosGateway mGate = PersistenceFactory.getMecanicosGateway();
			mGate.setConnection(c);

			mGate.update(nombre, apellidos, id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
