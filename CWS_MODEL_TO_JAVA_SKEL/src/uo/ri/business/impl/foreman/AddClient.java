package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.business.repository.RecomendacionRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.Cliente;
import uo.ri.model.MedioPago;
import uo.ri.model.Metalico;
import uo.ri.model.Recomendacion;
import uo.ri.model.Vehiculo;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class AddClient implements Command<Void> {

	private ClientDto cDto;
	private Long recomenderId;

	private ClienteRepository cR = Factory.repository.forCliente();
	private RecomendacionRepository rR = Factory.repository.forRecomendacion();
	private MedioPagoRepository mR = Factory.repository.forMedioPago();

	public AddClient(ClientDto client, Long recomenderId) {
		this.cDto = client;
		this.recomenderId = recomenderId;
	}

	public Void execute() throws BusinessException {

		assertDniNotRepeated(cDto.dni);

		Cliente cliente = DtoAssembler.toEntity(cDto);
		cR.add(cliente);

		if (recomenderId != null) {
			Cliente recomendador = getClient(recomenderId);
			createRecomendation(recomendador, cliente);
		}

		MedioPago mP = new Metalico(cliente);
		mR.add(mP);

		return null;
	}

	/**
	 * Crea la recomendación
	 */
	private void createRecomendation(Cliente recomendador, Cliente clienteRecomendado) {
		Recomendacion rec = new Recomendacion(recomendador, clienteRecomendado);
		rR.add(rec);
	}

	/**
	 * Comprueba si el id del cliente recomendador existe y retorna el cliente al
	 * que corresponde dicho id
	 * 
	 * @param idClient
	 * @return Cliente
	 * @throws BusinessException
	 */
	private Cliente getClient(Long idClient) throws BusinessException {

		Cliente client = cR.findById(idClient);
		Check.isNotNull(client, "No existe el cliente recomendador");

		return client;

	}

	private void assertDniNotRepeated(String dni) throws BusinessException {

		Cliente client = cR.findByDni(dni);
		Check.isNull(client, "Ya existe un cliente con ese dni");

	}

}
