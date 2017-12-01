package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class FindAllMechanics {

	public List<Map<String, Object>> execute() throws BusinessException {

		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			MecanicosGateway mGate = PersistenceFactory.getMecanicosGateway();
			mGate.setConnection(c);

			map = mGate.findAllMechanics();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			throw new BusinessException("Error sacando todos los mecánicos");
		} finally {
			Jdbc.close(c);
		}

		return map;
	}

}
