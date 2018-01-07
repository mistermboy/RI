package uo.ri.business.repository;

import java.util.List;

import uo.ri.model.Cliente;

public interface ClienteRepository extends Repository<Cliente> {

	Cliente findByDni(String dni);

	Cliente findById(Long idClient);

	List<Cliente> findWithThreeUnusedBreakdowns();

}
