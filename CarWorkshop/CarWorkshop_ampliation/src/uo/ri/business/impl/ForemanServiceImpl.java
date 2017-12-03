package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.ForemanService;
import uo.ri.business.impl.foreman.AddClient;
import uo.ri.business.impl.foreman.AddClientWithRecomendator;
import uo.ri.business.impl.foreman.FindAllClients;
import uo.ri.business.impl.foreman.DeleteClient;
import uo.ri.business.impl.foreman.ShowClientDetail;
import uo.ri.business.impl.foreman.UpdateClient;
import uo.ri.common.BusinessException;

public class ForemanServiceImpl implements ForemanService {

	@Override
	public void addClient(String dni, String nombre, String apellidos, int zipcode, int telefono, String correo)
			throws BusinessException {

		AddClient a = new AddClient(dni, nombre, apellidos, zipcode, telefono, correo);
		a.execute();

	}
	
	@Override
	public void addClientWithRecomendator(String dni, String nombre, String apellidos, int zipcode, int telefono,
			String correo, long idRecomendador) throws BusinessException {
	
		AddClientWithRecomendator aR = new AddClientWithRecomendator(dni, nombre, apellidos, zipcode, telefono, correo,idRecomendador);
		aR.execute();
		
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
	public void updateClient(long idClient, String dni, String nombre, String apellidos, int zipcode, int telefono,
			String correo) throws BusinessException {

		UpdateClient u = new UpdateClient(idClient, dni, nombre, apellidos, zipcode, telefono, correo);
		u.execute();

	}

	@Override
	public String showDetailClient(Long idClient) throws BusinessException {

		ShowClientDetail s = new ShowClientDetail(idClient);
		return s.execute();
	}

	

}
