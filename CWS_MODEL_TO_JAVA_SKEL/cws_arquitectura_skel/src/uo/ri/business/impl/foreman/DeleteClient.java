package uo.ri.business.impl.foreman;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.business.repository.RecomendacionRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.MedioPago;
import uo.ri.model.Recomendacion;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class DeleteClient implements Command<Void> {

	private Long idClient;

	private ClienteRepository cR = Factory.repository.forCliente();
	private RecomendacionRepository rR = Factory.repository.forRecomendacion();
	private MedioPagoRepository mR = Factory.repository.forMedioPago();

	public DeleteClient(Long idClient) {
		this.idClient = idClient;
	}

	public Void execute() throws BusinessException {

		Cliente client = getClient(idClient);
		assertCanBeDeleted(client);

		removeRecomendationsHechas(client);
		removeRecomendationRecibida(client);

		removeMediosPago(client);

		cR.remove(client);

		return null;
	}

	/**
	 * Elimina los medios de pago del cliente
	 * 
	 * @param client
	 */
	private void removeMediosPago(Cliente client) {
		for (MedioPago med : client.getMediosPago()) {
			med.unLink();
			mR.remove(med);

		}

	}

	/**
	 * Elimina la recomendación recibida del cliente
	 * 
	 * @param client
	 */
	private void removeRecomendationRecibida(Cliente client) {
		if (client.getRecomendacionRecibida() != null) {
			rR.remove(client.getRecomendacionRecibida());
			client.getRecomendacionRecibida().unlink();
		}
	}

	/**
	 * Elimina las recomendaciones hechas por el cliente
	 * 
	 * @param client
	 */
	private void removeRecomendationsHechas(Cliente client) {
		for (Recomendacion rec : client.getRecomendacionesHechas()) {
			rec.unlink();
			rR.remove(rec);
		}

	}

	/**
	 * Comprueba si el id del cliente existe y retorna el cliente al que corresponde
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

	/**
	 * Comprueba que el cliente tiene vehículos registrados
	 * 
	 * @param client
	 * @throws BusinessException
	 */
	private void assertCanBeDeleted(Cliente client) throws BusinessException {
		Check.isTrue(client.getVehiculos().isEmpty(),
				"El cliente no puede ser eliminado al tener vehículos registrados");

	}

}
