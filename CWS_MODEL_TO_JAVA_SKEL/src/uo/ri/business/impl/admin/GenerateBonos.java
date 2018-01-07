package uo.ri.business.impl.admin;

import java.util.ArrayList;
import java.util.List;

import alb.util.random.Random;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.model.MedioPago;
import uo.ri.util.exception.BusinessException;

public class GenerateBonos implements Command<Integer> {

	private ClienteRepository cR = Factory.repository.forCliente();
	private MedioPagoRepository mR = Factory.repository.forMedioPago();

	public Integer execute() throws BusinessException {

		List<Cliente> clients = cR.findWithThreeUnusedBreakdowns();

		return createBonos(clients);
	}

	/**
	 * Genera los nuevos bonos en la tabla Mediospago y actualiza el usada_bono de
	 * las averias
	 *
	 * @param clients
	 * @throws BusinessException
	 */
	private int createBonos(List<Cliente> clients) throws BusinessException {
		int numBonosGenerados = 0;
		List<String> codes = new ArrayList<String>();
		for (Cliente client : clients) {
			List<Averia> avs = client.getAveriasBono3NoUsadas();

			int moduloAverias = avs.size() % 3;
			int nAverias = avs.size() - moduloAverias;
			int nBonos = nAverias / 3;

			numBonosGenerados += nBonos;

			for (int i = 0; i < nAverias; i++) {
				avs.get(i).markAsBono3Used();
			}

			for (int i = 0; i < nBonos; i++) {
				String finalCode = generateNewCodigo(codes);
				codes.add(finalCode);
				Bono b = new Bono(finalCode, "Por tres averías", 20.0);
				b.link(client);
				mR.add(b);
			}

		}
		return numBonosGenerados;
	}

	/**
	 * Genera un código para un nuevo bono. En función del último bono se le sumará
	 * 10 al nuevo código
	 *
	 * @return
	 */
	private String generateNewCodigo(List<String> codes) {
		String bonoCode = "";
		do {
			bonoCode = "B" + Random.integer(2000, 3000);
		} while (assertBonoNotRepeated(bonoCode, codes));

		return bonoCode;
	}

	/**
	 * Comprueba que el código del bono no esté repetido
	 *
	 * @param bonoCode
	 * @return
	 */
	private boolean assertBonoNotRepeated(String bonoCode, List<String> codes) {
		for (String c : codes) {
			if (c.equals(bonoCode)) {
				return true;
			}
		}
		return false;
	}
}
