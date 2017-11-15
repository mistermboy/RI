package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;

public class UpdateMechanic {

	private long id;
	private String nombre;
	private String apellidos;

	public UpdateMechanic(long id, String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.id = id;
	}

	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_UPDATE_MECHANIC"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
