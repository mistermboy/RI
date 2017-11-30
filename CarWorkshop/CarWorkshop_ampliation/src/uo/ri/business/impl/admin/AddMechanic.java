package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class AddMechanic {

	private String nombre;
	private String apellidos;

	public AddMechanic(String nombre, String apellidos) {

		this.nombre = nombre;
		this.apellidos = apellidos;

	}

	public void execute() throws BusinessException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			MecanicosGateway mGate = PersistenceFactory.getMecanicosGateway();
			mGate.setConnection(c);
			
			mGate.insertMechanic(this.nombre, this.apellidos);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			throw new BusinessException("Error añadiendo un mecánico");
		} finally {
			Jdbc.close(c);
		}
	}

}
