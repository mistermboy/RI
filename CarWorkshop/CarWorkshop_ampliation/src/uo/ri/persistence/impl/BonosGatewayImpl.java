package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bin.alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.BonosGateway;

public class BonosGatewayImpl implements BonosGateway {

	Connection conection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public void setConnection(Connection conection) {
		this.conection = conection;

	}

	@Override
	public List<Long> getVehiculosByIdCliente(Long idCliente) throws BusinessException {

		List<Long> ids = new ArrayList<Long>();

		try {
			pst = conection.prepareStatement(Conf.get("SQL_FIND_VEHICULOS_BY_ID_CLIENTE"));

			pst.setLong(1, idCliente);

			rs = pst.executeQuery();
			while (rs.next()) {
				ids.add(rs.getLong("id"));
			}

		} catch (SQLException e) {
			throw new BusinessException("Error sacando los vehiculos de los clientes");
		} finally {
			Jdbc.close(pst);
		}

		return ids;
	}

	@Override
	public  List<Long> getAveriasByIdVehiculo(Long idVehiculo) throws BusinessException {
		List<Long> ids = new ArrayList<Long>();

		try {
			pst = conection.prepareStatement(Conf.get("SQL_FIND_AVERIAS_BY_ID_VEHICULO"));

			pst.setLong(1, idVehiculo);

			rs = pst.executeQuery();
			while (rs.next()) {
				ids.add(rs.getLong("id"));
			}

		} catch (SQLException e) {
			throw new BusinessException("Error sacando las averias de los vehículos");
		} finally {
			Jdbc.close(pst);
		}

		return ids;
	}

}
