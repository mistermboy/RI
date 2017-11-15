package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;

public class FindAllMechanics {

	public List<Map<String, Object>> execute() {

		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_FIND_ALL_MECHANICS"));

			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", rs.getInt("id"));
				m.put("nombre", rs.getInt("nombre"));
				m.put("apellidos", rs.getInt("apellidos"));
				map.add(m);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

		return map;
	}

}
