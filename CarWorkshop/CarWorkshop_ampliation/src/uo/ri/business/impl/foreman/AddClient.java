package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class AddClient {

	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private int cPostal;
	private int telefono;

	public AddClient(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cPostal = cPostal;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public void execute() throws BusinessException {
	
		Connection c = null;

		try {
			c = Jdbc.getConnection();

			MecanicosGateway mGate = PersistenceFactory.getMecanicosGateway();
			mGate.setConnection(c);
			
			mGate.save(this.nombre, this.apellidos);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(c);
		}
		
	}
	

}
