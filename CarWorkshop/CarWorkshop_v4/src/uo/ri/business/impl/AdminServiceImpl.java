package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService{

	@Override
	public void addMechanic(String nombre, String apellidos) throws BusinessException {
		AddMechanic a = new AddMechanic(nombre, apellidos);
		a.execute();
		
	}

	@Override
	public void deleteMechanic(Long idMechanic) throws BusinessException {
		DeleteMechanic d = new DeleteMechanic(idMechanic);
		d.execute();
	}

	@Override
	public void updateMechanic(Long idMechanic, String nombre, String apellidos) throws BusinessException {
		UpdateMechanic u = new UpdateMechanic(idMechanic, nombre, apellidos);
		u.execute();
		
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() throws BusinessException {
		FindAllMechanics f = new FindAllMechanics();
		return f.execute();
	}

}
