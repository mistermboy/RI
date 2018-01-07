package uo.ri.persistence.jpa;

import java.util.List;

import uo.ri.business.repository.ClienteRepository;
import uo.ri.model.Cliente;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

public class ClienteJpaRepository extends BaseRepository<Cliente> implements ClienteRepository {

	@Override
	public Cliente findByDni(String dni) {
		return Jpa.getManager().createNamedQuery("Cliente.findByDni", Cliente.class).setParameter(1, dni)
				.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public Cliente findById(Long idClient) {
		return Jpa.getManager().createNamedQuery("Cliente.findById", Cliente.class).setParameter(1, idClient)
				.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<Cliente> findWithThreeUnusedBreakdowns() {
		return Jpa.getManager().createNamedQuery("Cliente.findWithThreeUnusedBreakdowns", Cliente.class)
				.getResultList();
	}

}
