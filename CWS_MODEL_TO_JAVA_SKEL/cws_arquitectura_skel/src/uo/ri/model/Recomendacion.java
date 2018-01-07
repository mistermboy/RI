package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TRecomendaciones")
public class Recomendacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Cliente recomendador;
	@OneToOne
	private Cliente recomendado;

	private boolean usada_bono;

	Recomendacion() {
	}

	public Recomendacion(Cliente recomendador, Cliente recomendado) {
		super();

		Association.Recomendar.link(recomendador, this, recomendado);
	}

	public boolean isUsada_bono() {
		return usada_bono;
	}

	public void setUsada_bono(boolean usada_bono) {
		this.usada_bono = usada_bono;
	}

	public Cliente getRecomendado() {
		return recomendado;
	}

	void _setRecomendado(Cliente cliente) {
		this.recomendado = cliente;
	}

	public Cliente getRecomendador() {
		return recomendador;
	}

	void _setRecomendador(Cliente recomendador) {
		this.recomendador = recomendador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recomendado == null) ? 0 : recomendado.hashCode());
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
		Recomendacion other = (Recomendacion) obj;
		if (recomendado == null) {
			if (other.recomendado != null)
				return false;
		} else if (!recomendado.equals(other.recomendado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recomendacion [cliente=" + recomendado + ", usada_bono=" + usada_bono + "]";
	}

	public Long getId() {
		return id;
	}

	/**
	 * Marca la recomendación como usada para bono por recomendaciones
	 * 
	 * @throws BusinessException
	 */
	public void markAsUsadaBono() throws BusinessException {
		if (this.recomendador.elegibleBonoPorRecomendaciones()) {
			setUsada_bono(true);
		} else {
			throw new BusinessException("No puede ser marcado para bono por recomendaciones");
		}

	}

	/**
	 * Hace el unlinck entre el recomendado y su recomendador
	 */
	public void unlink() {
		Association.Recomendar.unlink(this);

	}
}
