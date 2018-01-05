package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class AddClient implements Command<Void> {

	private ClientDto cDto;
	private Long recomenderId;
	private ClienteRepository cR = Factory.repository.forCliente();

	public AddClient(ClientDto client, Long recomenderId) {
		this.cDto = client;
		this.recomenderId = recomenderId;
	}

	public Void execute() throws BusinessException {

		assertDniNotRepeated(cDto.dni);

		Cliente cliente = DtoAssembler.toEntity(cDto);
//		if (recomenderId != null) {
//			cR.add(cliente);
//		} else {
//			cR.addWithRecomendator(cliente, recomenderId);
//		}
		cR.add(cliente);
		return null;
	}

	private void assertDniNotRepeated(String dni) throws BusinessException {

		Cliente client = cR.findByDni(dni);
		Check.isNull(client, "El dni ya esxiste");

	}

}
