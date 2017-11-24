package uo.ri.model;

import uo.ri.model.exception.BusinessException;

public class Cargo {
	
	private Factura factura;
	private MedioPago medioPago;
	private double importe = 0.0;
	
	public Cargo(Factura factura, MedioPago medioPago, double importe) throws BusinessException {
		// incrementar el importe en el acumulado del medio de pago
		// guardar el importe
		// enlazar (link) factura, este cargo y medioDePago
	}
	
	/**
	 * Anula (retrocede) este cargo de la factura y el medio de pago
	 * Solo se puede hacer si la factura no está abonada
	 * Decrementar el acumulado del medio de pago
	 * Desenlazar el cargo de la factura y el medio de pago 
	 * @throws BusinessException
	 */
	public void rewind() throws BusinessException {
		// verificar que la factura no esta ABONADA
		// decrementar acumulado en medio de pago
		// desenlazar factura, cargo y edio de pago
	}
	

}
