package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class Mecanico {

	private String dni;
	private String apellidos;
	private String nombre;

	private Set<Averia> asigandas = new HashSet<>();
	private Set<Intervencion> intervenciones = new HashSet<>();

	public Mecanico(String dni) {
		super();
		this.dni = dni;
	}

	public Mecanico(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		Mecanico other = (Mecanico) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	Set<Averia> _getAsignadas() {
		return asigandas;
	}

	public Set<Averia> getAsignadas() {
		return new HashSet<>(asigandas);
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}

}
