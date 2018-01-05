package uo.ri.business.repository;

import uo.ri.model.Cliente;

public interface ClienteRepository extends Repository<Cliente> {

	Cliente findByDni(String dni);

	Cliente findById(Long idClient);

}
