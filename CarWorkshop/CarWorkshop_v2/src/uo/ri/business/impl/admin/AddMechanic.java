package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;

public class AddMechanic {

	private String nombre;
	private String apellidos;

	public AddMechanic(String nombre, String apellidos) {

		this.nombre = nombre;
		this.apellidos = apellidos;

	}

	public void execute() {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_INSERT_MECHANIC"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
