package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.CreateInvoiceFor;
import uo.ri.common.BusinessException;

public class CashServiceImpl  implements CashService{

	@Override
	public Map<String, Object> createInvoiceFor(List<Long> list) throws BusinessException {
		CreateInvoiceFor c = new CreateInvoiceFor(list);
		return c.execute();
	}

}
