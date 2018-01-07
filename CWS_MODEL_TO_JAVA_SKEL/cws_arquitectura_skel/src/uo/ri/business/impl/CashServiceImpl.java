package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.impl.cash.CreateInvoiceFor;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class CashServiceImpl implements CashService {

	CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public InvoiceDto createInvoiceFor(List<Long> idsAveria) throws BusinessException {

		return executor.execute(new CreateInvoiceFor(idsAveria));
	}

	@Override
	public InvoiceDto findInvoice(Long numeroInvoiceDto) throws BusinessException {

		return null;
	}

	@Override
	public List<PaymentMeanDto> findPayMethodsForInvoice(Long idInvoiceDto) throws BusinessException {

		return null;
	}

	@Override
	public InvoiceDto settleInvoice(Long idInvoiceDto, Map<Long, Double> cargos) throws BusinessException {
		return null;

	}

	@Override
	public List<BreakdownDto> findRepairsByClient(String dni) throws BusinessException {

		return null;
	}

	@Override
	public InvoiceDto findInvoiceByNumber(Long numeroFactura) throws BusinessException {

		return null;
	}

	@Override
	public List<PaymentMeanDto> findPaymentMeansForInvoice(Long idFactura) throws BusinessException {

		return null;
	}

}
