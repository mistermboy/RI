package uo.ri.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import uo.ri.business.repository.MecanicoRepository;
import uo.ri.model.Mecanico;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

public class MechanicJpaRepository extends BaseRepository<Mecanico> implements MecanicoRepository {

	@Override
	public void add(Mecanico m) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carworkshop");
		EntityManager mapper = emf.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		mapper.persist(m);

		trx.commit();
		mapper.close();

	}

	@Override
	public void remove(Mecanico m) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carworkshop");
		EntityManager mapper = emf.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		mapper.remove(m);

		trx.commit();
		mapper.close();

	}

	@Override
	public Mecanico findById(Long id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carworkshop");
		EntityManager mapper = emf.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Mecanico m = mapper.find(Mecanico.class, id);

		trx.commit();
		mapper.close();

		return m;

	}

	@Override
	public List<Mecanico> findAll() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("carworkshop");
		EntityManager mapper = emf.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Mecanico> list = mapper.createQuery("select m from Mecanico m", Mecanico.class).getResultList();

		trx.commit();
		mapper.close();

		return list;
	}

	@Override
	public Mecanico findByDni(String dni) {
		// getResultList porque getSingleResult puede devolver null
		return Jpa.getManager().createNamedQuery("Mecanico.findByDni", Mecanico.class)
				.setParameter(1, dni).getResultList().stream().findFirst().orElse(null);

	}

}
