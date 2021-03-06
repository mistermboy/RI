package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import bin.alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway {

	Connection conection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public void setConnection(Connection conection) {
		this.conection = conection;

	}

	@Override
	public void vincularAveriaFactura(long idFactura, List<Long> idsAveria) throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_VINCULAR_AVERIA_FACTURA"));

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idFactura);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}

		} catch (SQLException e) {
			throw new BusinessException("Error vinculando una avería a una factura");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void save(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
			throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_INSERTAR_FACTURA"));
			pst.setLong(1, numeroFactura);
			pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
			pst.setDouble(3, iva);
			pst.setDouble(4, totalConIva);
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error insertando una factura");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public long recuperarClaveGenerada(long numeroFactura) throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_RECUPERAR_CLAVE_GENERADA"));
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} catch (SQLException e) {
			throw new BusinessException("Error recuperando la clave generada");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Long ultimoNumeroFactura() throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_ULTIMO_NUMERO_FACTURA"));
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; 
			} else {
				return 1L;
			}

		} catch (SQLException e) {
			throw new BusinessException("Error devolviendo el último numero de factura");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
