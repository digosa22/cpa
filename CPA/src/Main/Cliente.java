package Main;

public class Cliente {

	private int nif;
	private String nombre;
	private String tipo;
	
	public Cliente(){}
	
	public Cliente(int nif, String nombre, String tipo) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	public int getNif() {
		return nif;
	}
	public void setNif(int nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
