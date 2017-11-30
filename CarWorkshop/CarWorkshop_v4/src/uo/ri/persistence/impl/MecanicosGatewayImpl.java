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
	public void insertMechanic(String nombre, String apellidos) throws BusinessException {

		try {
			pst = conection.prepareStatement(Conf.get("SQL_INSERT_MECHANIC"));

			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un mecánico");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void deleteMechanic(long idMechanic) throws BusinessException {
		try {

			pst = conection.prepareStatement(Conf.get("SQL_DELETE_MECHANIC"));
			pst.setLong(1, idMechanic);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un mecánico");
		} finally {
			Jdbc.close(rs, pst);
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
				m.put("nombre", rs.getInt("nombre"));
				m.put("apellidos", rs.getInt("apellidos"));
				map.add(m);

			}

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un mecánico");
		} finally {
			Jdbc.close(rs, pst);
		}

		return map;
	}
	
	@Override
	public void update(String nombre, String apellidos, long id) throws BusinessException {
		try {

			pst = conection.prepareStatement(Conf.get("SQL_UPDATE_MECHANIC"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error añadiendo un mecánico");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}
