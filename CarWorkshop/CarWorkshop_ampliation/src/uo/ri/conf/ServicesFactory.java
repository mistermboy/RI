package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.ForemanService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;
import uo.ri.business.impl.ForemanServiceImpl;

public class ServicesFactory {

	
	public ServicesFactory() {}
	
	public static AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	public static CashService getCashService() {
		return new CashServiceImpl();
	}

	public static ForemanService getForemanService() {
		return new ForemanServiceImpl();
	}
	
}
