package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bin.alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.ClientesGateway;

public class ClientesGatewayImpl implements ClientesGateway {

	Connection conection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public void setConnection(Connection conection) {
		this.conection = conection;

	}

	@Override
	public void save(String dni, String nombre, String apellidos, int zipcode, int telefono, String correo)
			throws BusinessException {

		try {
			pst = conection.prepareStatement(Conf.get("SQL_INSERT_CLIENT"));

			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, apellidos);
			pst.setInt(4, zipcode);
			pst.setInt(5, telefono);
			pst.setString(6, correo);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un cliente");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void delete(long idClient) throws BusinessException {

		try {
			pst = conection.prepareStatement(Conf.get("SQL_DELETE_CLIENT"));

			pst.setLong(1, idClient);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error eliminando un cliente");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void update(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo,
			long idClient) throws BusinessException {

		try {

			pst = conection.prepareStatement(Conf.get("SQL_UPDATE_CLIENT"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, idClient);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error actualizando un cliente");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public String showClient(long idClient) throws BusinessException {
		String client = "";

		try {

			pst = conection.prepareStatement(Conf.get("SQL_SHOW_CLIENT"));
			pst.setLong(1, idClient);

			rs = pst.executeQuery();

			client += rs.getString("dni");
			client += "\t";
			client += rs.getString("nombre");
			client += "\t";
			client += rs.getString("apellidos");
			client += "\t";
			client += rs.getInt("zipcode");
			client += "\t";
			client += rs.getInt("telefono");
			client += "\t";
			client += rs.getInt("correo");

			return client;

		} catch (SQLException e) {
			throw new BusinessException("Error mostrando un cliente");
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public List<Map<String, Object>> findAllClients() throws BusinessException {

		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

		try {

			pst = conection.prepareStatement(Conf.get("SQL_FIND_ALL_CLIENTS"));

			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", rs.getInt("id"));
				m.put("nombre", rs.getInt("nombre"));
				m.put("apellidos", rs.getInt("apellidos"));
				map.add(m);

			}

		} catch (SQLException e) {
			throw new BusinessException("Error buscando todos los clientes");
		} finally {
			Jdbc.close(rs, pst);
		}

		return map;
	}

}
