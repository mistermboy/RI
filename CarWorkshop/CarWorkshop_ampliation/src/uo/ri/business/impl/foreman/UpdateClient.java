package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientesGateway;

public class UpdateClient {

	private Long idClient;
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private int zipcode;
	private int telefono;

	public UpdateClient(long idClient,String dni, String nombre,String apellidos,int zipcode, int telefono, String correo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.zipcode = zipcode;
		this.telefono = telefono;
		this.correo = correo;
		this.idClient=idClient;
	}

	public void execute() throws BusinessException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			cGate.setConnection(c);

			cGate.update(idClient, dni, nombre, apellidos, zipcode, telefono, correo);;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
