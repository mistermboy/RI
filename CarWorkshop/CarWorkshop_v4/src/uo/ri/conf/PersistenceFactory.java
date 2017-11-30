package uo.ri.conf;

import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.MecanicosGatewayImpl;

public class PersistenceFactory {

	public static MecanicosGateway getMecanicosGateway() {
		return new MecanicosGatewayImpl();
	}

}
