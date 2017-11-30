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
	public void verificarEstadoAveria(List<Long> idsAveria) throws BusinessException {
		try {
			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("No existe la averia " + idAveria);
				}

				String status = rs.getString(1);
				if (!"TERMINADA".equalsIgnoreCase(status)) {
					throw new BusinessException("No está terminada la avería " + idAveria);
				}
			}

		} catch (SQLException e) {
			throw new BusinessException("Error verificando el estado de una avería");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void actualizarEstadoAveria(List<Long> idsAveria, String status) throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));

			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}

		} catch (SQLException e) {
			throw new BusinessException("Error actualizando el estado de una avería");
		} finally {
			Jdbc.close(rs, pst);
		}

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
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void insertarFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
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
			Jdbc.close(rs, pst);
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
				return rs.getLong(1) + 1; // +1, el siguiente
			} else { // todavía no hay ninguna
				return 1L;
			}

		} catch (SQLException e) {
			throw new BusinessException("Error devolviendo el último numero de factura");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void actualizarImporteAveria(Long idAveria, double totalAveria) throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_AVERIA"));
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new BusinessException("Error actualizando el importe de una avería");
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public double importeRepuestos(Long idAveria) throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_IMPORTE_REPUESTOS"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}

			return rs.getDouble(1);
			
		} catch (SQLException e) {
			throw new BusinessException("Error consultando el importe de repuestos");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public double importeManoObra(Long idAveria) throws BusinessException {
		try {
			pst = conection.prepareStatement(Conf.get("SQL_IMPORTE_MANO_OBRA"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}

			return rs.getDouble(1);
			
		} catch (SQLException e) {
			throw new BusinessException("Error consultando el importe de mano de obra");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
