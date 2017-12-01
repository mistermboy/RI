package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.ForemanService;
import uo.ri.business.impl.foreman.AddClient;
import uo.ri.business.impl.foreman.FindAllClients;
import uo.ri.business.impl.foreman.RemoveClient;
import uo.ri.business.impl.foreman.ShowClientDetail;
import uo.ri.business.impl.foreman.UpdateClient;
import uo.ri.common.BusinessException;

public class ForemanServiceImpl implements ForemanService {

	@Override
	public void addClient(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo)
			throws BusinessException {

		AddClient a = new AddClient(dni, nombre, apellidos, cPostal, telefono, correo);
		a.execute();

	}

	@Override
	public void removeClient(String dni) throws BusinessException {

		RemoveClient r = new RemoveClient(dni);
		r.execute();

	}

	@Override
	public List<Map<String, Object>> findAllClients() throws BusinessException {

		FindAllClients f = new FindAllClients();
		return f.execute();
	}

	@Override
	public void updateClient(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo)
			throws BusinessException {

		UpdateClient u = new UpdateClient(dni, nombre, apellidos, cPostal, telefono, correo);
		u.execute();

	}

	@Override
	public String showDetailClient(String dni) throws BusinessException {

		ShowClientDetail s = new ShowClientDetail(dni);
		return s.execute();
	}

}
