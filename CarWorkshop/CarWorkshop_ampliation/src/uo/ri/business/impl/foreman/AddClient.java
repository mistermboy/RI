package uo.ri.business.impl.foreman;

public class AddClient {

	private String dni;
	private String nombre;
	private String apellidos;
	private String correo;
	private int cPostal;
	private int telefono;

	public AddClient(String dni, String nombre, String apellidos, int cPostal, int telefono, String correo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cPostal = cPostal;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	public void execute() {
	
	}
	

}
