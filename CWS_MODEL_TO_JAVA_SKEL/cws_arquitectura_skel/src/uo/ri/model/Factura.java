package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import alb.util.date.DateUtil;
import alb.util.math.Round;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TFacturas")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private Long numero;
	private Date fecha;
	private double importe;
	private double iva;
	private boolean usada_bono;

	@Enumerated(EnumType.STRING)
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;

	@OneToMany(mappedBy = "factura")
	private Set<Averia> averias = new HashSet<>();
	@OneToMany(mappedBy = "factura")
	private Set<Cargo> cargos = new HashSet<>();

	Factura() {
	}

	public Factura(long numero, Date fecha) {
		this.fecha = fecha;
		this.numero = numero;
		this.iva = getIva();
	}

	public Factura(long numero) {
		this(numero, new Date());
	}

	public Factura(long numero, List<Averia> averias) throws BusinessException {
		this(numero, new Date());
		for (Averia averia : averias) {
			addAveria(averia);
		}
	}

	public Factura(long numero, Date fecha, List<Averia> averias) {
		this(numero, fecha);
		this.averias = new HashSet<Averia>(averias);
	}

	/**
	 * Añade la averia a la factura
	 * 
	 * @param averia
	 * @throws BusinessException
	 */
	public void addAveria(Averia averia) throws BusinessException {
		if (this.getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			if (averia.getStatus().equals(AveriaStatus.TERMINADA)) {
				Association.Facturar.link(this, averia);
				averia.markAsInvoiced();
				calcularImporte();
			} else {
				throw new BusinessException("La averia no está terminada");
			}
		} else {
			throw new BusinessException("La factura ya está abonada");
		}
	}

	/**
	 * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de
	 * factura
	 */
	void calcularImporte() {
		double acum = 0;
		for (Averia averia : averias) {
			acum += averia.getImporte();
		}
		this.iva = getIva();
		this.importe = Round.twoCents((acum * this.iva) + acum);
	}

	/**
	 * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula el
	 * importe
	 * 
	 * @param averia
	 */
	public void removeAveria(Averia averia) {
		if (this.getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			Association.Facturar.unlink(this, averia);
			averia.markBackToFinished();
			calcularImporte();
		}
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		calcularImporte();
		return importe;
	}

	public double getIva() {
		if (fecha.before(DateUtil.fromString("01/07/2012"))) {
			return 0.18;
		} else {
			return 0.21;
		}
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public FacturaStatus getStatus() {
		return status;
	}

	public void setStatus(FacturaStatus status) {
		this.status = status;
	}

	public Long getNumero() {
		return numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Factura other = (Factura) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", importe=" + importe + ", iva=" + iva + ", status="
				+ status + "]";
	}

	Set<Averia> _getAverias() {
		return averias;
	}

	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<>(cargos);
	}

	public Long getId() {
		return id;
	}

	/**
	 * Liquida la factura. Su estado pasa a ABONADA
	 * 
	 * @throws BusinessException
	 */
	public void settle() throws BusinessException {
		if (!hasAverias()) {
			throw new BusinessException("No se puede liquidar la factura");
		} else if (checkSobrePagada()) {
			throw new BusinessException("No se puede liquidar la factura");
		} else {
			this.status = FacturaStatus.ABONADA;
		}
	}

	/**
	 * Comprueba si la avería está sobre pagada
	 * 
	 * @return true si lo está, false en caso contrario
	 */
	private boolean checkSobrePagada() {
		double acum = 0.0;
		for (Cargo c : this.cargos) {
			acum += c.getImporte();
		}
		if (this.importe - acum > 0.01 || this.importe - acum < -0.01) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si la factura tiene averias
	 * 
	 * @return true si las tiene, false en caso contrario
	 */
	private boolean hasAverias() {
		return this.averias.size() > 0;
	}

	public boolean isUsada_bono() {
		return usada_bono;
	}

	public void setUsada_bono(boolean usada_bono) {
		this.usada_bono = usada_bono;
	}

	/**
	 * Comprueba si la factura puede generar Bono500
	 * 
	 * @return true en caso afirmativo, false en caso contrario
	 */
	public boolean puedeGenerarBono500() {
		if (this.importe > 500 && this.status.equals(FacturaStatus.ABONADA) && !isUsada_bono()) {
			return true;
		}
		return false;
	}

	/**
	 * Marca la factura como usada para Bono500
	 * 
	 * @throws BusinessException
	 */
	public void markAsBono500Used() throws BusinessException {
		if (puedeGenerarBono500()) {
			setUsada_bono(true);
		} else {
			throw new BusinessException("No es posible generar bono por factura superior a 500 euros");
		}

	}

	/**
	 * Comprueba si la factura se encuentra liquidada
	 * 
	 * @return true en caso afirmativo, false en caso contrario
	 */
	public boolean isSettled() {
		if (this.status.equals(FacturaStatus.ABONADA)) {
			return true;
		}
		return false;
	}

	public boolean isBono500Used() {
		return this.usada_bono;
	}

}
