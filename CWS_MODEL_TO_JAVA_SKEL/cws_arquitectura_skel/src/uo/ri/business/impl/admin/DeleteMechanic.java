package uo.ri.business.impl.admin;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class DeleteMechanic implements Command<Void> {

	private Long idMecanico;

	public DeleteMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public Void execute() throws BusinessException {

		MecanicoRepository r = Factory.repository.forMechanic();

		Mecanico m = r.findById(idMecanico);
		Check.isNotNull(m, "El mecánico no existe");
		assertCanBeDeleted(m);
		r.remove(m);
		return null;
	}

	/**
	 * Comprueba si el mecánico puede ser eliminado
	 * 
	 * @param m
	 * @throws BusinessException
	 */
	private void assertCanBeDeleted(Mecanico m) throws BusinessException {
		Check.isTrue(m.getIntervenciones().isEmpty(), "Tiene averias");

		Check.isTrue(m.getAsignadas().isEmpty(), "Tiene intervenciones");

	}

}
