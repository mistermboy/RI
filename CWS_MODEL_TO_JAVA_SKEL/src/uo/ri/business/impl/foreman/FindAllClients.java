package uo.ri.business.impl.foreman;

import java.util.List;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;

public class FindAllClients implements Command<List<ClientDto>> {

	private ClienteRepository cR = Factory.repository.forCliente();;

	public List<ClientDto> execute() {

		List<Cliente> listClients = cR.findAll();
		return DtoAssembler.toClientDtoList(listClients);
	}

}
