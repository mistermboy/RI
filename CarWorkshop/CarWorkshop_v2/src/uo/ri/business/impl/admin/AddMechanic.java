package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;

public class AddMechanic {

	private static String SQL = "insert into TMecanicos(nombre, apellidos) values (?, ?)";

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

			pst = c.prepareStatement(SQL);
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
