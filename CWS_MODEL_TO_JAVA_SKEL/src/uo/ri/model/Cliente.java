package uo.ri.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.model.types.Address;

@Entity
@Table(name = "TClientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String dni;
	private String nombre;
	private String apellidos;
	private Address address;

	@OneToMany(mappedBy = "cliente")
	private Set<Vehiculo> vehiculos = new HashSet<>();
	@OneToMany(mappedBy = "cliente")
	private Set<MedioPago> mediosPago = new HashSet<>();

	Cliente() {
	}

	public Cliente(String dni) {
		super();
		this.dni = dni;
	}

	public Cliente(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", address=" + address + "]";
	}

	Set<Vehiculo> _getVehiculos() {
		return vehiculos;
	}

	public Set<Vehiculo> getVehiculos() {
		return new HashSet<>(vehiculos);
	}

	Set<MedioPago> _getMediosPago() {
		return mediosPago;
	}

	public Set<MedioPago> getMediosPago() {
		return new HashSet<>(mediosPago);
	}

	/**
	 * Devuelve las averías que puede usar un cliente para un bono por 3 averias
	 * 
	 * @return List<Averia>
	 */
	public List<Averia> getAveriasBono3NoUsadas() {
		List<Averia> avs = new ArrayList<Averia>();
		for (Vehiculo v : vehiculos) {
			for (Averia a : v.getAverias()) {
				if (a.esElegibleParaBono3()) {
					avs.add(a);
				}
			}
		}
		return avs;
	}

	/**
	 * Deveulve si un cliente tiene derecho a un bono por recomendacioens
	 * 
	 * @return true en caso afirmativo, false en caso contrario
	 */
	public boolean elegibleBonoPorRecomendaciones() {
		boolean ret = true;
		;
		if (esRecienRegistrado()) {
			ret = false;
		} else if (vehiculoSinAverias()) {
			ret = false;
		}
		return ret;
	}

	/**
	 * Comprueba si el cliente tiene algún vehículo con averias
	 * 
	 * @return
	 */
	private boolean vehiculoSinAverias() {
		for (Vehiculo v : this.getVehiculos()) {
			if (v.getAverias().size() > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Comprueba si el cliente está recién registrado o no
	 * 
	 * @return true si está recién registrado, false en caso contrario
	 */
	private boolean esRecienRegistrado() {
		return getVehiculos().size() == 0;
	}

}
