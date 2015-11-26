package Main;

public class Posicion {

	private int columna;
	private int fila;
	public Posicion(int columna, int fila) {
		super();
		this.columna = columna;
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
}
