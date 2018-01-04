package uo.ri.business.repository;

import java.util.List;

import uo.ri.model.Bono;
import uo.ri.model.MedioPago;
import uo.ri.model.TarjetaCredito;

public interface MedioPagoRepository extends Repository<MedioPago> {
	
	List<MedioPago> findPaymentMeansByClientId(Long id);
	
	Bono findVoucherByCode(String code);
	
	TarjetaCredito findCreditCardByNumber(String pan);
	
	List<Bono> findVouchersByClientId(Long id);
	
	List<MedioPago> findByClientId(Long id);
	
}
