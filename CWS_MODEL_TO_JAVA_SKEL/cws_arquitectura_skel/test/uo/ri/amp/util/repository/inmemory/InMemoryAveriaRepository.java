package uo.ri.amp.util.repository.inmemory;

import java.util.List;

import uo.ri.business.repository.AveriaRepository;
import uo.ri.model.Averia;

public class InMemoryAveriaRepository extends BaseMemoryRepository<Averia> implements AveriaRepository {

	@Override
	public List<Averia> findByIds(List<Long> idsAveria) {
		return null;
	}

	@Override
	public List<Averia> findNoFacturadasByDni(String dni) {
		return null;
	}

	@Override
	public List<Averia> findWithUnusedBono3ByClienteId(Long id) {
		return null;
	}

}
