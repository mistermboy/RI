package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bin.alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.MediospagoGateway;

public class MediospagoGatewayImp implements MediospagoGateway {

	Connection conection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public void setConnection(Connection conection) {
		this.conection = conection;

	}

	@Override
	public void createBonos(Long idCLiente, String codigo) throws BusinessException {

		try {
			pst = conection.prepareStatement(Conf.get("SQL_CREATE_BONOS"));

			pst.setString(1, "TBonos");
			pst.setInt(2, 0);
			pst.setString(3, codigo);
			pst.setInt(4, 20);
			pst.setLong(5, idCLiente);
			pst.setString(6, "Por tres averias");

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error creando los bonos");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public String getLastBonoCode() throws BusinessException {

		String codigo = "";

		try {
			pst = conection.prepareStatement(Conf.get("SQL_GET_ALL_BONOS"));

			rs = pst.executeQuery();

			while (rs.next()) {
				codigo = rs.getString("codigo");
			}

			return codigo;

		} catch (SQLException e) {
			throw new BusinessException("Error sacando el código del último bono");
		} finally {
			Jdbc.close(rs,pst);
		}

	}

}
