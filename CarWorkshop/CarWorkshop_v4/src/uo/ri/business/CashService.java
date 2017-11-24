package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CashService {

	Map<String,Object> createInvoiceFor(List<Long> list) throws BusinessException;
	
//	Map<String,Object> findInvoice(Long l);
//	
//	List<Map<String,Object>> findPayMethodsForInvoice(Long l);
//	
//	
	
}
