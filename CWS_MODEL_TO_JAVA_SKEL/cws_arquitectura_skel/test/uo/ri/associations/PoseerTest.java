package uo.ri.associations;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import uo.ri.model.Association;
import uo.ri.model.Cliente;
import uo.ri.model.Vehiculo;
import uo.ri.util.exception.BusinessException;


public class PoseerTest {
	private Vehiculo vehiculo;
	private Cliente cliente;

	@Before
	public void setUp() throws BusinessException {
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		Association.Poseer.link(cliente, vehiculo);
	}
	
	@Test
	public void testPoseerAdd() throws BusinessException {
		assertTrue( cliente.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getCliente() == cliente );
	}

	@Test
	public void testPoseerRemove() throws BusinessException {
		Association.Poseer.unlink(cliente, vehiculo);

		assertTrue( ! cliente.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getCliente() == null );
	}

	@Test
	public void testSafeReturn() throws BusinessException {
		Set<Vehiculo> vehiculos = cliente.getVehiculos();
		vehiculos.remove( vehiculo );

		assertTrue( vehiculos.size() == 0 );
		assertTrue( "Se debe retornar copia de la coleccion o hacerla de solo lectura", 
			cliente.getVehiculos().size() == 1
		);
	}


}
