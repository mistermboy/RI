package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CashService {

	Map<String, Object> createInvoiceFor(List<Long> list) throws BusinessException;

}
