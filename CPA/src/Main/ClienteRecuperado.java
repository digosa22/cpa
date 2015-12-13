package Main;

public class ClienteRecuperado {

	private int id;
	private String nif;
	private String tipo_num_accion;
	private String empresa;
	private String telefono;
	private String persona_contacto;
	private String web;
	private String departamento;
	private String email;
	private String banco;
	private String num_cc;
	private String iban;
	private String bic;
	private String idioma;
	private int num_proveedor;
	private double hora_normal;	
	private double hora_extra;
	private double hora_sabado;
	private double hora_festiva;
	private double hora_nocturna;
	private double hora_especialista_normal;	
	private double hora_especialista_extra;
	private double hora_especialista_sabado;
	private double hora_especialista_festiva;
	private double hora_especialista_nocturna;
	private double hora_coordinacion;
	private double hora_administracion;
	private double gastos_logisticos;
	private String direccion;
	private String codigo_postal;
	private String pais;
	private String poblacion;
	private String apartado_de_correo;
	private String codigo_postal_2;
	private String codigo_postal_empresa;
	
	public ClienteRecuperado() {
		
	}

	public ClienteRecuperado(int id, String nif, String tipo_num_accion, String empresa, String telefono,
			String persona_contacto, String web, String departamento, String email, String banco, String num_cc,
			String iban, String bic, String idioma, int num_proveedor, double hora_normal, double hora_extra,
			double hora_sabado, double hora_festiva, double hora_nocturna, double hora_especialista_normal,
			double hora_especialista_extra, double hora_especialista_sabado, double hora_especialista_festiva,
			double hora_especialista_nocturna, double hora_coordinacion, double hora_administracion,
			double gastos_logisticos, String direccion, String codigo_postal, String pais, String poblacion,
			String apartado_de_correo, String codigo_postal_2, String codigo_postal_empresa) {
		super();
		this.id = id;
		this.nif = nif;
		this.tipo_num_accion = tipo_num_accion;
		this.empresa = empresa;
		this.telefono = telefono;
		this.persona_contacto = persona_contacto;
		this.web = web;
		this.departamento = departamento;
		this.email = email;
		this.banco = banco;
		this.num_cc = num_cc;
		this.iban = iban;
		this.bic = bic;
		this.idioma = idioma;
		this.num_proveedor = num_proveedor;
		this.hora_normal = hora_normal;
		this.hora_extra = hora_extra;
		this.hora_sabado = hora_sabado;
		this.hora_festiva = hora_festiva;
		this.hora_nocturna = hora_nocturna;
		this.hora_especialista_normal = hora_especialista_normal;
		this.hora_especialista_extra = hora_especialista_extra;
		this.hora_especialista_sabado = hora_especialista_sabado;
		this.hora_especialista_festiva = hora_especialista_festiva;
		this.hora_especialista_nocturna = hora_especialista_nocturna;
		this.hora_coordinacion = hora_coordinacion;
		this.hora_administracion = hora_administracion;
		this.gastos_logisticos = gastos_logisticos;
		this.direccion = direccion;
		this.codigo_postal = codigo_postal;
		this.pais = pais;
		this.poblacion = poblacion;
		this.apartado_de_correo = apartado_de_correo;
		this.codigo_postal_2 = codigo_postal_2;
		this.codigo_postal_empresa = codigo_postal_empresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getApartado_de_correo() {
		return apartado_de_correo;
	}

	public void setApartado_de_correo(String apartado_de_correo) {
		this.apartado_de_correo = apartado_de_correo;
	}

	public String getCodigo_postal_2() {
		return codigo_postal_2;
	}

	public void setCodigo_postal_2(String codigo_postal_2) {
		this.codigo_postal_2 = codigo_postal_2;
	}

	public String getCodigo_postal_empresa() {
		return codigo_postal_empresa;
	}

	public void setCodigo_postal_empresa(String codigo_postal_empresa) {
		this.codigo_postal_empresa = codigo_postal_empresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getTipo_num_accion() {
		return tipo_num_accion;
	}

	public void setTipo_num_accion(String tipo_num_accion) {
		this.tipo_num_accion = tipo_num_accion;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPersona_contacto() {
		return persona_contacto;
	}

	public void setPersona_contacto(String persona_contacto) {
		this.persona_contacto = persona_contacto;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNum_cc() {
		return num_cc;
	}

	public void setNum_cc(String num_cc) {
		this.num_cc = num_cc;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getNum_proveedor() {
		return num_proveedor;
	}

	public void setNum_proveedor(int num_proveedor) {
		this.num_proveedor = num_proveedor;
	}

	public double getHora_normal() {
		return hora_normal;
	}

	public void setHora_normal(double hora_normal) {
		this.hora_normal = hora_normal;
	}

	public double getHora_extra() {
		return hora_extra;
	}

	public void setHora_extra(double hora_extra) {
		this.hora_extra = hora_extra;
	}

	public double getHora_sabado() {
		return hora_sabado;
	}

	public void setHora_sabado(double hora_sabado) {
		this.hora_sabado = hora_sabado;
	}

	public double getHora_festiva() {
		return hora_festiva;
	}

	public void setHora_festiva(double hora_festiva) {
		this.hora_festiva = hora_festiva;
	}

	public double getHora_nocturna() {
		return hora_nocturna;
	}

	public void setHora_nocturna(double hora_nocturna) {
		this.hora_nocturna = hora_nocturna;
	}

	public double getHora_especialista_normal() {
		return hora_especialista_normal;
	}

	public void setHora_especialista_normal(double hora_especialista_normal) {
		this.hora_especialista_normal = hora_especialista_normal;
	}

	public double getHora_especialista_extra() {
		return hora_especialista_extra;
	}

	public void setHora_especialista_extra(double hora_especialista_extra) {
		this.hora_especialista_extra = hora_especialista_extra;
	}

	public double getHora_especialista_sabado() {
		return hora_especialista_sabado;
	}

	public void setHora_especialista_sabado(double hora_especialista_sabado) {
		this.hora_especialista_sabado = hora_especialista_sabado;
	}

	public double getHora_especialista_festiva() {
		return hora_especialista_festiva;
	}

	public void setHora_especialista_festiva(double hora_especialista_festiva) {
		this.hora_especialista_festiva = hora_especialista_festiva;
	}

	public double getHora_especialista_nocturna() {
		return hora_especialista_nocturna;
	}

	public void setHora_especialista_nocturna(double hora_especialista_nocturna) {
		this.hora_especialista_nocturna = hora_especialista_nocturna;
	}

	public double getHora_coordinacion() {
		return hora_coordinacion;
	}

	public void setHora_coordinacion(double hora_coordinacion) {
		this.hora_coordinacion = hora_coordinacion;
	}

	public double getHora_administracion() {
		return hora_administracion;
	}

	public void setHora_administracion(double hora_administracion) {
		this.hora_administracion = hora_administracion;
	}

	public double getGastos_logisticos() {
		return gastos_logisticos;
	}

	public void setGastos_logisticos(double gastos_logisticos) {
		this.gastos_logisticos = gastos_logisticos;
	}
	
	
}
