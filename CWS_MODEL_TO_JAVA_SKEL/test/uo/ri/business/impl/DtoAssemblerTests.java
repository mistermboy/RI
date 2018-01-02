package uo.ri.business.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.ri.business.dto.CardDto;
import uo.ri.business.dto.CashDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.VoucherDto;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.model.Association;
import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.model.MedioPago;
import uo.ri.model.Metalico;
import uo.ri.model.TarjetaCredito;

public class DtoAssemblerTests {

	private Cliente c;
	private TarjetaCredito tc;
	private Bono b;
	private Metalico m;

	@Before
	public void setUp() throws Exception {
		c = new Cliente("dni", "n", "a");
		tc = new TarjetaCredito("123");
		m = new Metalico( c );
		b = new Bono("B123");
		
		Association.Pagar.link(c, tc);
		Association.Pagar.link(c, b);
	}

	@Test
	public void testCardDto() {
		CardDto dto = DtoAssembler.toDto( tc );
		
		assertTrue( dto.cardNumber.equals("123") );
	}

	@Test
	public void testVoucherDto() {
		VoucherDto dto = DtoAssembler.toDto( b );
		
		assertTrue( dto.code.equals( b.getCodigo() ) );
	}

	@Test
	public void testToDtoList() {
		List<MedioPago> l = new ArrayList<>();
		l.add( m );
		l.add( b );
		l.add( tc );
		
		List<PaymentMeanDto> dl = DtoAssembler.toPaymentMeanDtoList( l );
		
		assertTrue( dl.size() == 3 );
		assertTrue( dl.stream().anyMatch( d -> d instanceof CashDto) );
		assertTrue( dl.stream().anyMatch( d -> d instanceof CardDto) );
		assertTrue( dl.stream().anyMatch( d -> d instanceof VoucherDto) );
	}

}
