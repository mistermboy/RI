package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.ForemanService;
import uo.ri.business.impl.foreman.AddClient;
import uo.ri.business.impl.foreman.FindAllClients;
import uo.ri.business.impl.foreman.DeleteClient;
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
	public void deleteClient(Long idClient) throws BusinessException {

		DeleteClient r = new DeleteClient(idClient);
		r.execute();

	}

	@Override
	public List<Map<String, Object>> findAllClients() throws BusinessException {

		FindAllClients f = new FindAllClients();
		return f.execute();
	}

	@Override
	public void updateClient(Long idClient, String nombre, String apellidos) throws BusinessException {

		UpdateClient u = new UpdateClient(idClient, nombre, apellidos);
		u.execute();

	}

	@Override
	public String showDetailClient(Long idClient) throws BusinessException {

		ShowClientDetail s = new ShowClientDetail(idClient);
		return s.execute();
	}

}
