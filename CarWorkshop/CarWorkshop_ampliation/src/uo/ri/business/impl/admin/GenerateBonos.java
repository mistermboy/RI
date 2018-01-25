package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriasGateway;
import uo.ri.persistence.BonosGateway;
import uo.ri.persistence.ClientesGateway;
import uo.ri.persistence.MediospagoGateway;

public class GenerateBonos {

	/**
	 * Genera los bonos
	 * 
	 * @throws BusinessException
	 */
	public void execute() throws BusinessException {

		Connection c = null;
		List<Long> idsClientes = new ArrayList<Long>();
		List<Long> idsVehiculos = new ArrayList<Long>();
		List<Long> idsAverias = new ArrayList<Long>();

		List<Long> acumAverias = null;

		try {
			c = Jdbc.getConnection();

			BonosGateway bGate = PersistenceFactory.getBonosGateway();
			ClientesGateway cGate = PersistenceFactory.getClientesGateway();
			bGate.setConnection(c);
			cGate.setConnection(c);

			idsClientes = cGate.findAllClientsId();
			for (Long idC : idsClientes) {
				acumAverias = new ArrayList<Long>();
				idsVehiculos = bGate.getVehiculosByIdCliente(idC);
				for (Long idV : idsVehiculos) {
					idsAverias = bGate.getAveriasByIdVehiculo(idV);
					for (Long averia : idsAverias) {
						acumAverias.add(averia);
					}
				}
				createBono(acumAverias, idC, c);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

	/**
	 * Genera un nuevo bono en la tabla Mediospago y actualiza el usada_bono de las
	 * averias
	 * 
	 * @param idsAverias
	 * @param idC
	 * @param c
	 * @throws BusinessException
	 */
	private void createBono(List<Long> idsAverias, Long idC, Connection c) throws BusinessException {

		AveriasGateway aGate = PersistenceFactory.getAveriasGateway();
		MediospagoGateway mGate = PersistenceFactory.getMediospagoGateway();
		aGate.setConnection(c);
		mGate.setConnection(c);

		int moduloAverias = idsAverias.size() % 3;
		int nAverias = idsAverias.size() - moduloAverias;
		int nBonos = nAverias / 3;

		for (int i = 0; i < nAverias; i++) {
			aGate.insertBonoAveria(idsAverias.get(i));
		}

		for (int i = 0; i < nBonos; i++) {
			String finalCode = generateNewCodigo(mGate);
			mGate.createBonos(idC, finalCode);
		}

	}

	/**
	 * Genera un código para un nuevo bono. En función del último bono se le sumará
	 * 10 al nuevo código
	 * 
	 * @param mGate
	 * @return
	 * @throws BusinessException
	 */
	private String generateNewCodigo(MediospagoGateway mGate) throws BusinessException {
		String[] codes = mGate.getLastBonoCode().split("");
		String code = "";
		for (int i = 1; i < codes.length; i++) {
			code += codes[i];
		}
		String finalCode = "B" + String.valueOf(Integer.valueOf(code) + 10);
		return finalCode;
	}

}
