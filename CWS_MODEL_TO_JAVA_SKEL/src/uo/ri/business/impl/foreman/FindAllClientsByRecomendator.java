package uo.ri.business.impl.foreman;

import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.Recomendacion;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class FindAllClientsByRecomendator implements Command<List<ClientDto>> {

	private ClienteRepository cR = Factory.repository.forCliente();

	private Long idRecomendador;

	public FindAllClientsByRecomendator(Long idRecomendador) {
		this.idRecomendador = idRecomendador;
	}

	public List<ClientDto> execute() throws BusinessException {

		Cliente client = getClient(idRecomendador);
		List<Cliente> clientsRecomendados = new ArrayList<Cliente>();
		for (Recomendacion rec : client.getRecomendacionesHechas()) {
			clientsRecomendados.add(rec.getRecomendado());
		}

		return DtoAssembler.toClientDtoList(clientsRecomendados);
	}

	/**
	 * Comprueba si el id del cliente existe y retorna el cluente al que corresponde
	 * dicho id
	 * 
	 * @param idClient
	 * @return Cliente
	 * @throws BusinessException
	 */
	private Cliente getClient(Long idClient) throws BusinessException {

		Cliente client = cR.findById(idClient);
		Check.isNotNull(client, "El cliente no existe");

		return client;

	}

}
