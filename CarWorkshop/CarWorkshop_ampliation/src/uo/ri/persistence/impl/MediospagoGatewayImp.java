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
	public void createBonos(Long idCLiente) throws BusinessException {

		try {
			pst = conection.prepareStatement(Conf.get("SQL_CREATE_BONOS"));

			pst.setString(1,"TBonos");
			pst.setInt(2, 0);
			pst.setInt(3, 20);
			pst.setLong(4, idCLiente);
			pst.setString(5,"Por 3 averias");

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error creando los bonos");
		} finally {
			Jdbc.close(pst);
		}

	}

}
