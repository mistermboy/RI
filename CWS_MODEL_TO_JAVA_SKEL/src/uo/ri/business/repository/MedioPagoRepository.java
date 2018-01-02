package uo.ri.business.repository;

import java.util.List;

import uo.ri.model.MedioPago;

public interface MedioPagoRepository extends Repository<MedioPago> {
	
	List<MedioPago> findPaymentMeansByClientId(Long id);
}
