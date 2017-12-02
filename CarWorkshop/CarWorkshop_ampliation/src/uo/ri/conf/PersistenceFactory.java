package uo.ri.conf;

import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.BonosGateway;
import uo.ri.persistence.ClientesGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.AveriasGatewayImpl;
import uo.ri.persistence.impl.BonosGatewayImpl;
import uo.ri.persistence.impl.ClientesGatewayImpl;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;

public class PersistenceFactory {

	public PersistenceFactory() {
	}

	public static MecanicosGateway getMecanicosGateway() {
		return new MecanicosGatewayImpl();
	}

	public static FacturasGateway getFacturasGateway() {
		return new FacturasGatewayImpl();
	}

	public static AveriasGateway getAveriasGateway() {
		return new AveriasGatewayImpl();
	}

	public static ClientesGateway getClientesGateway() {
		return new ClientesGatewayImpl();
	}

	public static BonosGateway getBonosGateway() {
		return new BonosGatewayImpl();
	}
	
}
