package uo.ri.persistence.jpa;

import uo.ri.business.repository.ClienteRepository;
import uo.ri.model.Cliente;
import uo.ri.persistence.jpa.util.BaseRepository;

public class ClienteJpaRepository 
		extends BaseRepository<Cliente> 
		implements ClienteRepository {

	@Override
	public Cliente findByDni(String dni) {
		// TODO Auto-generated method stub
		return null;
	}


}
