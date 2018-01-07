package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class FindClientById implements Command<ClientDto> {

	private Long idClient;

	private ClienteRepository cR = Factory.repository.forCliente();

	public FindClientById(Long idClient) {
		this.idClient = idClient;
	}

	public ClientDto execute() throws BusinessException {

		Cliente client = cR.findById(idClient);
		Check.isNotNull(client, "El cliente no existe");

		return client == null ? null : DtoAssembler.toDto(client);

	}

}
