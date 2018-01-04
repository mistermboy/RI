package uo.ri.business.repository;

public interface RepositoryFactory {

	MecanicoRepository forMechanic();

	AveriaRepository forAveria();

	MedioPagoRepository forMedioPago();

	FacturaRepository forFactura();

	ClienteRepository forCliente();

	RepuestoRepository forRepuesto();

	VehiculoRepository forVehiculo();

	RecomendacionRepository forRecomendacion();

	CargoRepository forCargo();

}
