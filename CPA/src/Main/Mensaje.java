package Main;

public class Mensaje {

	private String para;
	private String cc;
	private String asunto;
	private String mensaje;
	private String ruta;
	
	public Mensaje(String para, String cc, String asunto, String mensaje, String ruta) {
		super();
		this.para = para;
		this.cc = cc;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.ruta = ruta;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}
