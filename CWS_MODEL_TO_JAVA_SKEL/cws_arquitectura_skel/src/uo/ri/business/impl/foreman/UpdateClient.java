package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.types.Address;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class UpdateClient implements Command<Void> {

	private ClientDto dtoClient;

	private ClienteRepository cR = Factory.repository.forCliente();

	public UpdateClient(ClientDto dtoClient) {
		this.dtoClient = dtoClient;
	}

	public Void execute() throws BusinessException {

		Cliente client = cR.findById(dtoClient.id);
		Check.isNotNull(client, "El cliente no existe");
		
		client.setNombre(dtoClient.name);
		client.setApellidos(dtoClient.surname);
		client.setAddress(new Address(dtoClient.addressStreet, dtoClient.addressCity, dtoClient.addressZipcode));
		client.setEmail(dtoClient.email);
		client.setPhone(dtoClient.phone);

		return null;
	}

}
