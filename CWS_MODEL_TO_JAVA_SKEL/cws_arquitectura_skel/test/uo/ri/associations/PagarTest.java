package uo.ri.associations;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.DateUtil;
import uo.ri.model.Association;
import uo.ri.model.Averia;
import uo.ri.model.Cargo;
import uo.ri.model.Cliente;
import uo.ri.model.Factura;
import uo.ri.model.Intervencion;
import uo.ri.model.Mecanico;
import uo.ri.model.Metalico;
import uo.ri.model.Repuesto;
import uo.ri.model.Sustitucion;
import uo.ri.model.TipoVehiculo;
import uo.ri.model.Vehiculo;
import uo.ri.util.exception.BusinessException;


public class PagarTest {
	private Mecanico mecanico;
	private Averia averia;
	private Intervencion intervencion;
	private Repuesto repuesto;
	private Sustitucion sustitucion;
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;
	private Cliente cliente;
	private Factura factura;
	private Metalico metalico;
	private Cargo cargo;

	@Before
	public void setUp() throws BusinessException {
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		Association.Poseer.link(cliente, vehiculo );

		tipoVehiculo = new TipoVehiculo("coche", 50.0);
		Association.Clasificar.link(tipoVehiculo, vehiculo);
		
		averia = new Averia(vehiculo, "falla la junta la trocla");
		mecanico = new Mecanico("dni-mecanico", "nombre", "apellidos");
		averia.assignTo(mecanico);
	
		intervencion = new Intervencion(mecanico, averia);
		intervencion.setMinutos(60);
		
		repuesto = new Repuesto("R1001", "junta la trocla", 100.0);
		sustitucion = new Sustitucion(repuesto, intervencion);
		sustitucion.setCantidad(2);
		
		averia.markAsFinished();

		factura = new Factura(0L, DateUtil.today());
		factura.addAveria(averia);
		
		metalico = new Metalico( cliente );
		cargo = new Cargo(factura, metalico, 100.0);
	}
	
	@Test
	public void testPagarAdd() throws BusinessException {
		assertTrue( cliente.getMediosPago().contains( metalico ));
		assertTrue( metalico.getCliente() == cliente );
	}

	@Test
	public void testPagarRemove() throws BusinessException {
		Association.Pagar.unlink(cliente, metalico);
		
		assertTrue( ! cliente.getMediosPago().contains( metalico ));
		assertTrue( cliente.getMediosPago().size() == 0 );
		assertTrue( metalico.getCliente() == null );
	}

	@Test
	public void testCargarAdd() throws BusinessException {
		assertTrue( metalico.getCargos().contains( cargo ));
		assertTrue( factura.getCargos().contains( cargo ));
		
		assertTrue( cargo.getFactura() == factura );
		assertTrue( cargo.getMedioPago() == metalico );
		
		assertTrue( metalico.getAcumulado() == 100.0 );
	}

	@Test
	public void testCargarRemove() throws BusinessException {
		cargo.rewind();  // removes this charge: unlink from Invoice and PaymentMean
		
		assertTrue( ! metalico.getCargos().contains( cargo ));
		assertTrue( metalico.getCargos().size() == 0 );

		assertTrue( ! factura.getCargos().contains( cargo ));
		assertTrue( metalico.getCargos().size() == 0 );
		
		assertTrue( cargo.getFactura() == null );
		assertTrue( cargo.getMedioPago() == null );
	}

}
