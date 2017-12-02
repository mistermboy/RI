package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class AddClient {

	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private int zipcode;
	private int telefono;

	public AddClient(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.zipcode = cPostal;
		this.telefono = telefono;
		this.correo = correo;
	}

	public void execute() throws BusinessException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			cGate.setConnection(c);

			cGate.save(dni, nombre, apellidos, zipcode, telefono, correo);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
