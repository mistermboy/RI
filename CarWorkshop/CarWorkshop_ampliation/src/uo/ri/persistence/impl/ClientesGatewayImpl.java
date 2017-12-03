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

			insertMedioPago(getIdCliente(dni));

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un cliente sin recomendación");
		} finally {
			Jdbc.close(pst);
		}

	}
	

	@Override
	public void saveWithRecomendator(String dni, String nombre, String apellidos, int zipcode, int telefono,
			String correo, Long idRecomendador) throws BusinessException {
		
		try {
			pst = conection.prepareStatement(Conf.get("SQL_INSERT_CLIENT_WITH_RECOMENDATOR"));

			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, apellidos);
			pst.setInt(4, zipcode);
			pst.setInt(5, telefono);
			pst.setString(6, correo);
			pst.setLong(7, idRecomendador);

			pst.executeUpdate();

			insertMedioPago(getIdCliente(dni));

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un cliente con recomendación");
		} finally {
			Jdbc.close(pst);
		}
		
	}
	

	@Override
	public void delete(long idClient) throws BusinessException {

		if (getNumberOfVehiculos(idClient) == 0) {

			try {
				deleteMedioPago(idClient);

				pst = conection.prepareStatement(Conf.get("SQL_DELETE_CLIENT"));

				pst.setLong(1, idClient);

				pst.executeUpdate();

			} catch (SQLException e) {
				throw new BusinessException("Error eliminando un cliente");
			} finally {
				Jdbc.close(pst);
			}
		} else {
			throw new BusinessException(
					"No ha sido posible eliminar dicho cliente ya que dispone de vehículos registrados");
		}

	}

	@Override
	public void update(long idClient, String dni, String nombre, String apellidos, int zipcode, int telefono,
			String correo) throws BusinessException {

		try {

			pst = conection.prepareStatement(Conf.get("SQL_UPDATE_CLIENT"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setString(3, dni);
			pst.setInt(4, zipcode);
			pst.setInt(5, telefono);
			pst.setString(6, correo);
			pst.setLong(7, idClient);

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

			while (rs.next()) {
				client = rs.getString("dni");
				client += "\t";
				client += rs.getString("nombre");
				client += "\t";
				client += rs.getString("apellidos");
				client += "\t";
				client += rs.getInt("zipcode");
				client += "\t";
				client += rs.getInt("telefono");
				client += "\t";
				client += rs.getInt("email");
			}

		} catch (SQLException e) {
			throw new BusinessException("Error mostrando un cliente");
		} finally {
			Jdbc.close(rs, pst);
		}
		return client;
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
				m.put("nombre", rs.getString("nombre"));
				m.put("apellidos", rs.getString("apellidos"));
				map.add(m);

			}

		} catch (SQLException e) {
			throw new BusinessException("Error buscando todos los clientes");
		} finally {
			Jdbc.close(rs, pst);
		}

		return map;
	}

	@Override
	public List<Long> findAllClientsId() throws BusinessException {

		List<Long> ids = new ArrayList<Long>();

		try {

			pst = conection.prepareStatement(Conf.get("SQL_FIND_ALL_CLIENTS_ID"));

			rs = pst.executeQuery();
			while (rs.next()) {
				ids.add(rs.getLong("id"));
			}

		} catch (SQLException e) {
			throw new BusinessException("Error buscando todos los ids de los clientes");
		} finally {
			Jdbc.close(rs, pst);
		}
		return ids;
	}

	private void insertMedioPago(Long idClient) throws BusinessException {

		String dType = "TMetalico";
		int acumulado = 0;

		try {

			pst = conection.prepareStatement(Conf.get("SQL_INSERT_MEDIOPAGO"));

			pst.setString(1, dType);
			pst.setInt(2, acumulado);
			pst.setLong(3, idClient);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error insertando un medio de pago de tipo metálico a un nuevo cliente");
		} finally {
			Jdbc.close(pst);
		}

	}

	private void deleteMedioPago(Long idClient) throws BusinessException {

		long idMedioPago = getIDMedioPago(idClient);

		try {

			pst = conection.prepareStatement(Conf.get("SQL_DELETE_MEDIOPAGO"));

			pst.setLong(1, idMedioPago);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error eliminando el medio de pago de tipo metálico del cliente");
		} finally {
			Jdbc.close(pst);
		}

	}

	private int getNumberOfVehiculos(Long idClient) throws BusinessException {

		int cont = 0;

		try {

			pst = conection.prepareStatement(Conf.get("SQL_FIND_VEHICULOS_BY_ID_CLIENTE"));

			pst.setLong(1, idClient);

			rs = pst.executeQuery();
			while (rs.next()) {
				cont++;
			}

		} catch (SQLException e) {
			throw new BusinessException("Error buscando los vehiculos de un cliente");
		} finally {
			Jdbc.close(rs, pst);
		}
		return cont;
	}

	private long getIdCliente(String dni) throws BusinessException {

		long idCLient = 0;

		try {

			pst = conection.prepareStatement(Conf.get("SQL_FIND_ID_CLIENTE"));

			pst.setString(1, dni);

			rs = pst.executeQuery();
			while (rs.next()) {
				idCLient = rs.getLong("id");
			}

			return idCLient;

		} catch (SQLException e) {
			throw new BusinessException("Error buscando los vehiculos de un cliente");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	private long getIDMedioPago(Long idCliente) throws BusinessException {

		long idMedioPago = 0;

		try {

			pst = conection.prepareStatement(Conf.get("SQL_FIND_ID_MEDIOPAGO"));

			pst.setLong(1, idCliente);

			rs = pst.executeQuery();
			while (rs.next()) {
				idMedioPago = rs.getLong("id");
			}

			return idMedioPago;

		} catch (SQLException e) {
			throw new BusinessException("Error buscando los vehiculos de un cliente");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}