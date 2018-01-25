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
import uo.ri.persistence.MecanicosGateway;

public class MecanicosGatewayImpl implements MecanicosGateway {

	Connection conection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public void setConnection(Connection conection) {
		this.conection = conection;

	}

	@Override
	public void save(String nombre, String apellidos) throws BusinessException {

		try {
			pst = conection.prepareStatement(Conf.get("SQL_INSERT_MECHANIC"));

			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un mecánico");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void delete(long idMechanic) throws BusinessException {

		if (existMechanic(idMechanic)) {
			try {

				pst = conection.prepareStatement(Conf.get("SQL_DELETE_MECHANIC"));
				pst.setLong(1, idMechanic);

				pst.executeUpdate();

			} catch (SQLException e) {
				throw new BusinessException("Error eliminando un mecánico");
			} finally {
				Jdbc.close(pst);
			}
		} else {
			throw new BusinessException("El id del mecánico que se desea borrar no existe");
		}

	}

	@Override
	public List<Map<String, Object>> findAllMechanics() throws BusinessException {

		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

		try {

			pst = conection.prepareStatement(Conf.get("SQL_FIND_ALL_MECHANICS"));

			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", rs.getInt("id"));
				m.put("nombre", rs.getString("nombre"));
				m.put("apellidos", rs.getString("apellidos"));
				map.add(m);

			}

		} catch (SQLException e) {
			throw new BusinessException("Error buscando todos los mecánicos");
		} finally {
			Jdbc.close(rs, pst);
		}

		return map;
	}

	public List<Long> findAllMechanicsID() throws BusinessException {

		List<Long> map = new ArrayList<Long>();

		try {

			pst = conection.prepareStatement(Conf.get("SQL_FIND_ALL_MECHANICS_ID"));

			rs = pst.executeQuery();
			while (rs.next()) {
				map.add(rs.getLong("id"));

			}

		} catch (SQLException e) {
			throw new BusinessException("Error buscando todos los  ids de mecánicos");
		} finally {
			Jdbc.close(rs, pst);
		}

		return map;
	}

	@Override
	public void update(String nombre, String apellidos, long idMechanic) throws BusinessException {

		if (existMechanic(idMechanic)) {

			try {

				pst = conection.prepareStatement(Conf.get("SQL_UPDATE_MECHANIC"));
				pst.setString(1, nombre);
				pst.setString(2, apellidos);
				pst.setLong(3, idMechanic);

				pst.executeUpdate();

			} catch (SQLException e) {
				throw new BusinessException("Error actualizando un mecánico");
			} finally {
				Jdbc.close(pst);
			}
		} else {
			throw new BusinessException("El id del mecánico que se desea actualizar no existe");
		}

	}

	/**
	 * Comprueba si existe el mecánico que se le pasa como parámetro
	 * 
	 * @return true si existe, false en caso contrario
	 * @throws BusinessException
	 */
	private boolean existMechanic(Long idMechanic) throws BusinessException {

		List<Long> idsMecanicos = findAllMechanicsID();
		for (Long l : idsMecanicos) {
			if (l == idMechanic) {
				return true;
			}
		}
		return false;
	}

}
