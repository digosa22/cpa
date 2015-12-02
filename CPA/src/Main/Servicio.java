package Main;

import java.util.Date;
import java.util.ArrayList;

public class Servicio {

	// GENERICOS
	private int nifCliente;
	private String nomCliente;
	private String personaCPA;
	private String numAccion;
	private Date fechaInicio;
	
	
	
	// ORDEN DE PEDIDO
	private String nombrePieza;
	private String referencias;
	private String numChasis1;
	private String numChasis2;
	private String numChasis3;
	private String numChasis4;
	private String responsableCPA;
	private boolean piezasVerde;
	private boolean piezasBlanco;
	private boolean piezasOtros;
	private boolean piezasRojo;
	private boolean contenedorVerde;
	private boolean contenedorRojo;
	private String personaRecomendador;
	private String departamentoRecomendador;
	private String telefonoRecomendador;
	private String emailRecomendador;
	private Date fechaSolicitudRecomendador;
	private String descripcionInstruccionDelServicio;
	private boolean seguridadCalzado;
	private boolean seguridadGafas;
	private boolean seguridadChaleco;
	private boolean seguridadTapones;
	private boolean seguridadGuantes;
	private int informacionResultados;
	private String imagenOrdenDePedido;
	
	
	
	// INSTRUCCION DE TRABAJO
	private boolean operarioA1;
	private boolean operarioA2;
	private boolean operarioA3;
	private boolean operarioA4;
	private boolean operarioA5;
	private boolean operarioA6;
	private boolean operarioA7;
	private boolean peticionMaterialInstruccion;
	private boolean referenciasCorrectasInstruccion;
	private boolean seleccionPiezasInstruccion;
	private boolean retrabajoPiezasInstruccion;
	private boolean trasvaseInstruccion;
	private boolean otrosInstruccion;
	private String accionesIntruccion;
	
	
	
	// INFORMACION DE RESULTADOS
	private String tablaDefectos;
	private String piezasOK;
	private String piezasRecuperadas;
	
	
	
	// RECUENTO FINAL
	private String recuentoFinal;
	
	
	
	// ESTIMACION DE HORAS Y COSTES
	private String arrayHoraNormal;
	private String arrayHoraExtra;
	private String arrayHoraSabado;
	private String arrayHoraFestivo;
	private String arrayHoraNocturna;
	private String arrayHoraEspecialistaNormal;
	private String arrayHoraEspecialistaExtra;
	private String arrayHoraEspecialistaSabado;
	private String arrayHoraEspecialistaFestiva;
	private String arrayHoraEspecialistaNocturna;
	private String arrayHoraCoordinacion;
	private String arrayHoraAdministracion;
	private String arrayGastosLogisticos;
	private String arrayOtros1;
	private String arrayOtros2;
	
	
	
	// GAMA RETRABAJOS
	private String realizadoPorRetrabajos;
	private Date fechaRetrabajos;
	private Date fechaLiberacionRetrabajos;
	private String numReclamacionRetrabajos;
	private Date fechaReclamacionRetrabajos;
	private String referenciaPiezaRetrabajos;
	private Date fechaComienzoRetrabajos;
	private String tiempoRetrabajos;
	private String clienteRetrabajos;
	private String firmasRetrabajos;
	private String imagenRetrabajos;
	
	
	
	// FORMACION PERSONAL
	private String realizadoPorPersonal;
	private Date fechaPersonal;
	private String clientePersonal;
	private String piezaPersonal;
	private String referenciaPersonal;
	private String firmasPersonal;
	private String imagenPersonal;
	
	//PESTAÑAS AÑADIR
	private boolean retrabajos;
	private boolean formacion;
	
	
	// CONSTRUCTOR
	public Servicio(int nifCliente, String nomCliente, String personaCPA, String numAccion, Date fechaInicio,
			String nombrePieza, String referencias, String numChasis1, String numChasis2, String numChasis3,
			String numChasis4, String responsableCPA, boolean piezasVerde, boolean piezasBlanco, boolean piezasOtros,
			boolean piezasRojo, boolean contenedorVerde, boolean contenedorRojo, String personaRecomendador,
			String departamentoRecomendador, String telefonoRecomendador, String emailRecomendador,
			Date fechaSolicitudRecomendador, String descripcionInstruccionDelServicio, boolean seguridadCalzado,
			boolean seguridadGafas, boolean seguridadChaleco, boolean seguridadTapones, boolean seguridadGuantes,
			int informacionResultados, String imagenOrdenDePedido, boolean operarioA1, boolean operarioA2,
			boolean operarioA3, boolean operarioA4, boolean operarioA5, boolean operarioA6, boolean operarioA7,
			boolean peticionMaterialInstruccion, boolean referenciasCorrectasInstruccion,
			boolean seleccionPiezasInstruccion, boolean retrabajoPiezasInstruccion, boolean trasvaseInstruccion,
			boolean otrosInstruccion, String accionesIntruccion, String tablaDefectos, String piezasOK,
			String piezasRecuperadas, String recuentoFinal, String arrayHoraNormal, String arrayHoraExtra,
			String arrayHoraSabado, String arrayHoraFestivo, String arrayHoraNocturna,
			String arrayHoraEspecialistaNormal, String arrayHoraEspecialistaExtra, String arrayHoraEspecialistaSabado,
			String arrayHoraEspecialistaFestiva, String arrayHoraEspecialistaNocturna, String arrayHoraCoordinacion,
			String arrayHoraAdministracion, String arrayGastosLogisticos, String arrayOtros1, String arrayOtros2,
			String realizadoPorRetrabajos, Date fechaRetrabajos, Date fechaLiberacionRetrabajos,
			String numReclamacionRetrabajos, Date fechaReclamacionRetrabajos, String referenciaPiezaRetrabajos,
			Date fechaComienzoRetrabajos, String tiempoRetrabajos, String clienteRetrabajos, String firmasRetrabajos,
			String imagenRetrabajos, String realizadoPorPersonal, Date fechaPersonal, String clientePersonal,
			String piezaPersonal, String referenciaPersonal, String firmasPersonal, String imagenPersonal, boolean retrabajos, boolean formacion) {
		super();
		this.nifCliente = nifCliente;
		this.nomCliente = nomCliente;
		this.personaCPA = personaCPA;
		this.numAccion = numAccion;
		this.fechaInicio = fechaInicio;
		this.nombrePieza = nombrePieza;
		this.referencias = referencias;
		this.numChasis1 = numChasis1;
		this.numChasis2 = numChasis2;
		this.numChasis3 = numChasis3;
		this.numChasis4 = numChasis4;
		this.responsableCPA = responsableCPA;
		this.piezasVerde = piezasVerde;
		this.piezasBlanco = piezasBlanco;
		this.piezasOtros = piezasOtros;
		this.piezasRojo = piezasRojo;
		this.contenedorVerde = contenedorVerde;
		this.contenedorRojo = contenedorRojo;
		this.personaRecomendador = personaRecomendador;
		this.departamentoRecomendador = departamentoRecomendador;
		this.telefonoRecomendador = telefonoRecomendador;
		this.emailRecomendador = emailRecomendador;
		this.fechaSolicitudRecomendador = fechaSolicitudRecomendador;
		this.descripcionInstruccionDelServicio = descripcionInstruccionDelServicio;
		this.seguridadCalzado = seguridadCalzado;
		this.seguridadGafas = seguridadGafas;
		this.seguridadChaleco = seguridadChaleco;
		this.seguridadTapones = seguridadTapones;
		this.seguridadGuantes = seguridadGuantes;
		this.informacionResultados = informacionResultados;
		this.imagenOrdenDePedido = imagenOrdenDePedido;
		this.operarioA1 = operarioA1;
		this.operarioA2 = operarioA2;
		this.operarioA3 = operarioA3;
		this.operarioA4 = operarioA4;
		this.operarioA5 = operarioA5;
		this.operarioA6 = operarioA6;
		this.operarioA7 = operarioA7;
		this.peticionMaterialInstruccion = peticionMaterialInstruccion;
		this.referenciasCorrectasInstruccion = referenciasCorrectasInstruccion;
		this.seleccionPiezasInstruccion = seleccionPiezasInstruccion;
		this.retrabajoPiezasInstruccion = retrabajoPiezasInstruccion;
		this.trasvaseInstruccion = trasvaseInstruccion;
		this.otrosInstruccion = otrosInstruccion;
		this.accionesIntruccion = accionesIntruccion;
		this.tablaDefectos = tablaDefectos;
		this.piezasOK = piezasOK;
		this.piezasRecuperadas = piezasRecuperadas;
		this.recuentoFinal = recuentoFinal;
		this.arrayHoraNormal = arrayHoraNormal;
		this.arrayHoraExtra = arrayHoraExtra;
		this.arrayHoraSabado = arrayHoraSabado;
		this.arrayHoraFestivo = arrayHoraFestivo;
		this.arrayHoraNocturna = arrayHoraNocturna;
		this.arrayHoraEspecialistaNormal = arrayHoraEspecialistaNormal;
		this.arrayHoraEspecialistaExtra = arrayHoraEspecialistaExtra;
		this.arrayHoraEspecialistaSabado = arrayHoraEspecialistaSabado;
		this.arrayHoraEspecialistaFestiva = arrayHoraEspecialistaFestiva;
		this.arrayHoraEspecialistaNocturna = arrayHoraEspecialistaNocturna;
		this.arrayHoraCoordinacion = arrayHoraCoordinacion;
		this.arrayHoraAdministracion = arrayHoraAdministracion;
		this.arrayGastosLogisticos = arrayGastosLogisticos;
		this.arrayOtros1 = arrayOtros1;
		this.arrayOtros2 = arrayOtros2;
		this.realizadoPorRetrabajos = realizadoPorRetrabajos;
		this.fechaRetrabajos = fechaRetrabajos;
		this.fechaLiberacionRetrabajos = fechaLiberacionRetrabajos;
		this.numReclamacionRetrabajos = numReclamacionRetrabajos;
		this.fechaReclamacionRetrabajos = fechaReclamacionRetrabajos;
		this.referenciaPiezaRetrabajos = referenciaPiezaRetrabajos;
		this.fechaComienzoRetrabajos = fechaComienzoRetrabajos;
		this.tiempoRetrabajos = tiempoRetrabajos;
		this.clienteRetrabajos = clienteRetrabajos;
		this.firmasRetrabajos = firmasRetrabajos;
		this.imagenRetrabajos = imagenRetrabajos;
		this.realizadoPorPersonal = realizadoPorPersonal;
		this.fechaPersonal = fechaPersonal;
		this.clientePersonal = clientePersonal;
		this.piezaPersonal = piezaPersonal;
		this.referenciaPersonal = referenciaPersonal;
		this.firmasPersonal = firmasPersonal;
		this.imagenPersonal = imagenPersonal;
		this.retrabajos = retrabajos;
		this.formacion = formacion;
	}
	public int getNifCliente() {
		return nifCliente;
	}
	public void setNifCliente(int nifCliente) {
		this.nifCliente = nifCliente;
	}
	public String getNomCliente() {
		return nomCliente;
	}
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
	public String getPersonaCPA() {
		return personaCPA;
	}
	public void setPersonaCPA(String personaCPA) {
		this.personaCPA = personaCPA;
	}
	public String getNumAccion() {
		return numAccion;
	}
	public void setNumAccion(String numAccion) {
		this.numAccion = numAccion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getNombrePieza() {
		return nombrePieza;
	}
	public void setNombrePieza(String nombrePieza) {
		this.nombrePieza = nombrePieza;
	}
	public String getReferencias() {
		return referencias;
	}
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	public String getNumChasis1() {
		return numChasis1;
	}
	public void setNumChasis1(String numChasis1) {
		this.numChasis1 = numChasis1;
	}
	public String getNumChasis2() {
		return numChasis2;
	}
	public void setNumChasis2(String numChasis2) {
		this.numChasis2 = numChasis2;
	}
	public String getNumChasis3() {
		return numChasis3;
	}
	public void setNumChasis3(String numChasis3) {
		this.numChasis3 = numChasis3;
	}
	public String getNumChasis4() {
		return numChasis4;
	}
	public void setNumChasis4(String numChasis4) {
		this.numChasis4 = numChasis4;
	}
	public String getResponsableCPA() {
		return responsableCPA;
	}
	public void setResponsableCPA(String responsableCPA) {
		this.responsableCPA = responsableCPA;
	}
	public boolean isPiezasVerde() {
		return piezasVerde;
	}
	public void setPiezasVerde(boolean piezasVerde) {
		this.piezasVerde = piezasVerde;
	}
	public boolean isPiezasBlanco() {
		return piezasBlanco;
	}
	public void setPiezasBlanco(boolean piezasBlanco) {
		this.piezasBlanco = piezasBlanco;
	}
	public boolean isPiezasOtros() {
		return piezasOtros;
	}
	public void setPiezasOtros(boolean piezasOtros) {
		this.piezasOtros = piezasOtros;
	}
	public boolean isPiezasRojo() {
		return piezasRojo;
	}
	public void setPiezasRojo(boolean piezasRojo) {
		this.piezasRojo = piezasRojo;
	}
	public boolean isContenedorVerde() {
		return contenedorVerde;
	}
	public void setContenedorVerde(boolean contenedorVerde) {
		this.contenedorVerde = contenedorVerde;
	}
	public boolean isContenedorRojo() {
		return contenedorRojo;
	}
	public void setContenedorRojo(boolean contenedorRojo) {
		this.contenedorRojo = contenedorRojo;
	}
	public String getPersonaRecomendador() {
		return personaRecomendador;
	}
	public void setPersonaRecomendador(String personaRecomendador) {
		this.personaRecomendador = personaRecomendador;
	}
	public String getDepartamentoRecomendador() {
		return departamentoRecomendador;
	}
	public void setDepartamentoRecomendador(String departamentoRecomendador) {
		this.departamentoRecomendador = departamentoRecomendador;
	}
	public String getTelefonoRecomendador() {
		return telefonoRecomendador;
	}
	public void setTelefonoRecomendador(String telefonoRecomendador) {
		this.telefonoRecomendador = telefonoRecomendador;
	}
	public String getEmailRecomendador() {
		return emailRecomendador;
	}
	public void setEmailRecomendador(String emailRecomendador) {
		this.emailRecomendador = emailRecomendador;
	}
	public Date getFechaSolicitudRecomendador() {
		return fechaSolicitudRecomendador;
	}
	public void setFechaSolicitudRecomendador(Date fechaSolicitudRecomendador) {
		this.fechaSolicitudRecomendador = fechaSolicitudRecomendador;
	}
	public String getDescripcionInstruccionDelServicio() {
		return descripcionInstruccionDelServicio;
	}
	public void setDescripcionInstruccionDelServicio(String descripcionInstruccionDelServicio) {
		this.descripcionInstruccionDelServicio = descripcionInstruccionDelServicio;
	}
	public boolean isSeguridadCalzado() {
		return seguridadCalzado;
	}
	public void setSeguridadCalzado(boolean seguridadCalzado) {
		this.seguridadCalzado = seguridadCalzado;
	}
	public boolean isSeguridadGafas() {
		return seguridadGafas;
	}
	public void setSeguridadGafas(boolean seguridadGafas) {
		this.seguridadGafas = seguridadGafas;
	}
	public boolean isSeguridadChaleco() {
		return seguridadChaleco;
	}
	public void setSeguridadChaleco(boolean seguridadChaleco) {
		this.seguridadChaleco = seguridadChaleco;
	}
	public boolean isSeguridadTapones() {
		return seguridadTapones;
	}
	public void setSeguridadTapones(boolean seguridadTapones) {
		this.seguridadTapones = seguridadTapones;
	}
	public boolean isSeguridadGuantes() {
		return seguridadGuantes;
	}
	public void setSeguridadGuantes(boolean seguridadGuantes) {
		this.seguridadGuantes = seguridadGuantes;
	}
	public int getInformacionResultados() {
		return informacionResultados;
	}
	public void setInformacionResultados(int informacionResultados) {
		this.informacionResultados = informacionResultados;
	}
	public String getImagenOrdenDePedido() {
		return imagenOrdenDePedido;
	}
	public void setImagenOrdenDePedido(String imagenOrdenDePedido) {
		this.imagenOrdenDePedido = imagenOrdenDePedido;
	}
	public boolean isOperarioA1() {
		return operarioA1;
	}
	public void setOperarioA1(boolean operarioA1) {
		this.operarioA1 = operarioA1;
	}
	public boolean isOperarioA2() {
		return operarioA2;
	}
	public void setOperarioA2(boolean operarioA2) {
		this.operarioA2 = operarioA2;
	}
	public boolean isOperarioA3() {
		return operarioA3;
	}
	public void setOperarioA3(boolean operarioA3) {
		this.operarioA3 = operarioA3;
	}
	public boolean isOperarioA4() {
		return operarioA4;
	}
	public void setOperarioA4(boolean operarioA4) {
		this.operarioA4 = operarioA4;
	}
	public boolean isOperarioA5() {
		return operarioA5;
	}
	public void setOperarioA5(boolean operarioA5) {
		this.operarioA5 = operarioA5;
	}
	public boolean isOperarioA6() {
		return operarioA6;
	}
	public void setOperarioA6(boolean operarioA6) {
		this.operarioA6 = operarioA6;
	}
	public boolean isOperarioA7() {
		return operarioA7;
	}
	public void setOperarioA7(boolean operarioA7) {
		this.operarioA7 = operarioA7;
	}
	public boolean isPeticionMaterialInstruccion() {
		return peticionMaterialInstruccion;
	}
	public void setPeticionMaterialInstruccion(boolean peticionMaterialInstruccion) {
		this.peticionMaterialInstruccion = peticionMaterialInstruccion;
	}
	public boolean isReferenciasCorrectasInstruccion() {
		return referenciasCorrectasInstruccion;
	}
	public void setReferenciasCorrectasInstruccion(boolean referenciasCorrectasInstruccion) {
		this.referenciasCorrectasInstruccion = referenciasCorrectasInstruccion;
	}
	public boolean isSeleccionPiezasInstruccion() {
		return seleccionPiezasInstruccion;
	}
	public void setSeleccionPiezasInstruccion(boolean seleccionPiezasInstruccion) {
		this.seleccionPiezasInstruccion = seleccionPiezasInstruccion;
	}
	public boolean isRetrabajoPiezasInstruccion() {
		return retrabajoPiezasInstruccion;
	}
	public void setRetrabajoPiezasInstruccion(boolean retrabajoPiezasInstruccion) {
		this.retrabajoPiezasInstruccion = retrabajoPiezasInstruccion;
	}
	public boolean isTrasvaseInstruccion() {
		return trasvaseInstruccion;
	}
	public void setTrasvaseInstruccion(boolean trasvaseInstruccion) {
		this.trasvaseInstruccion = trasvaseInstruccion;
	}
	public boolean isOtrosInstruccion() {
		return otrosInstruccion;
	}
	public void setOtrosInstruccion(boolean otrosInstruccion) {
		this.otrosInstruccion = otrosInstruccion;
	}
	public String getAccionesIntruccion() {
		return accionesIntruccion;
	}
	public void setAccionesIntruccion(String accionesIntruccion) {
		this.accionesIntruccion = accionesIntruccion;
	}
	public String getTablaDefectos() {
		return tablaDefectos;
	}
	public void setTablaDefectos(String tablaDefectos) {
		this.tablaDefectos = tablaDefectos;
	}
	public String getPiezasOK() {
		return piezasOK;
	}
	public void setPiezasOK(String piezasOK) {
		this.piezasOK = piezasOK;
	}
	public String getPiezasRecuperadas() {
		return piezasRecuperadas;
	}
	public void setPiezasRecuperadas(String piezasRecuperadas) {
		this.piezasRecuperadas = piezasRecuperadas;
	}
	public String getRecuentoFinal() {
		return recuentoFinal;
	}
	public void setRecuentoFinal(String recuentoFinal) {
		this.recuentoFinal = recuentoFinal;
	}
	public String getArrayHoraNormal() {
		return arrayHoraNormal;
	}
	public void setArrayHoraNormal(String arrayHoraNormal) {
		this.arrayHoraNormal = arrayHoraNormal;
	}
	public String getArrayHoraExtra() {
		return arrayHoraExtra;
	}
	public void setArrayHoraExtra(String arrayHoraExtra) {
		this.arrayHoraExtra = arrayHoraExtra;
	}
	public String getArrayHoraSabado() {
		return arrayHoraSabado;
	}
	public void setArrayHoraSabado(String arrayHoraSabado) {
		this.arrayHoraSabado = arrayHoraSabado;
	}
	public String getArrayHoraFestivo() {
		return arrayHoraFestivo;
	}
	public void setArrayHoraFestivo(String arrayHoraFestivo) {
		this.arrayHoraFestivo = arrayHoraFestivo;
	}
	public String getArrayHoraNocturna() {
		return arrayHoraNocturna;
	}
	public void setArrayHoraNocturna(String arrayHoraNocturna) {
		this.arrayHoraNocturna = arrayHoraNocturna;
	}
	public String getArrayHoraEspecialistaNormal() {
		return arrayHoraEspecialistaNormal;
	}
	public void setArrayHoraEspecialistaNormal(String arrayHoraEspecialistaNormal) {
		this.arrayHoraEspecialistaNormal = arrayHoraEspecialistaNormal;
	}
	public String getArrayHoraEspecialistaExtra() {
		return arrayHoraEspecialistaExtra;
	}
	public void setArrayHoraEspecialistaExtra(String arrayHoraEspecialistaExtra) {
		this.arrayHoraEspecialistaExtra = arrayHoraEspecialistaExtra;
	}
	public String getArrayHoraEspecialistaSabado() {
		return arrayHoraEspecialistaSabado;
	}
	public void setArrayHoraEspecialistaFestivo(String arrayHoraEspecialistaSabado) {
		this.arrayHoraEspecialistaSabado = arrayHoraEspecialistaSabado;
	}
	public String getArrayHoraEspecialistaFestiva() {
		return arrayHoraEspecialistaFestiva;
	}
	public void setArrayHoraEspecialistaFestiva(String arrayHoraEspecialistaFestiva) {
		this.arrayHoraEspecialistaFestiva = arrayHoraEspecialistaFestiva;
	}
	public String getArrayHoraEspecialistaNocturna() {
		return arrayHoraEspecialistaNocturna;
	}
	public void setArrayHoraEspecialistaNocturna(String arrayHoraEspecialistaNocturna) {
		this.arrayHoraEspecialistaNocturna = arrayHoraEspecialistaNocturna;
	}
	public String getArrayHoraCoordinacion() {
		return arrayHoraCoordinacion;
	}
	public void setArrayHoraCoordinacion(String arrayHoraCoordinacion) {
		this.arrayHoraCoordinacion = arrayHoraCoordinacion;
	}
	public String getArrayHoraAdministracion() {
		return arrayHoraAdministracion;
	}
	public void setArrayHoraAdministracion(String arrayHoraAdministracion) {
		this.arrayHoraAdministracion = arrayHoraAdministracion;
	}
	public String getArrayGastosLogisticos() {
		return arrayGastosLogisticos;
	}
	public void setArrayGastosLogisticos(String arrayGastosLogisticos) {
		this.arrayGastosLogisticos = arrayGastosLogisticos;
	}
	public String getArrayOtros1() {
		return arrayOtros1;
	}
	public void setArrayOtros1(String arrayOtros1) {
		this.arrayOtros1 = arrayOtros1;
	}
	public String getArrayOtros2() {
		return arrayOtros2;
	}
	public void setArrayOtros2(String arrayOtros2) {
		this.arrayOtros2 = arrayOtros2;
	}
	public String getRealizadoPorRetrabajos() {
		return realizadoPorRetrabajos;
	}
	public void setRealizadoPorRetrabajos(String realizadoPorRetrabajos) {
		this.realizadoPorRetrabajos = realizadoPorRetrabajos;
	}
	public Date getFechaRetrabajos() {
		return fechaRetrabajos;
	}
	public void setFechaRetrabajos(Date fechaRetrabajos) {
		this.fechaRetrabajos = fechaRetrabajos;
	}
	public Date getFechaLiberacionRetrabajos() {
		return fechaLiberacionRetrabajos;
	}
	public void setFechaLiberacionRetrabajos(Date fechaLiberacionRetrabajos) {
		this.fechaLiberacionRetrabajos = fechaLiberacionRetrabajos;
	}
	public String getNumReclamacionRetrabajos() {
		return numReclamacionRetrabajos;
	}
	public void setNumReclamacionRetrabajos(String numReclamacionRetrabajos) {
		this.numReclamacionRetrabajos = numReclamacionRetrabajos;
	}
	public Date getFechaReclamacionRetrabajos() {
		return fechaReclamacionRetrabajos;
	}
	public void setFechaReclamacionRetrabajos(Date fechaReclamacionRetrabajos) {
		this.fechaReclamacionRetrabajos = fechaReclamacionRetrabajos;
	}
	public String getReferenciaPiezaRetrabajos() {
		return referenciaPiezaRetrabajos;
	}
	public void setReferenciaPiezaRetrabajos(String referenciaPiezaRetrabajos) {
		this.referenciaPiezaRetrabajos = referenciaPiezaRetrabajos;
	}
	public Date getFechaComienzoRetrabajos() {
		return fechaComienzoRetrabajos;
	}
	public void setFechaComienzoRetrabajos(Date fechaComienzoRetrabajos) {
		this.fechaComienzoRetrabajos = fechaComienzoRetrabajos;
	}
	public String getTiempoRetrabajos() {
		return tiempoRetrabajos;
	}
	public void setTiempoRetrabajos(String tiempoRetrabajos) {
		this.tiempoRetrabajos = tiempoRetrabajos;
	}
	public String getClienteRetrabajos() {
		return clienteRetrabajos;
	}
	public void setClienteRetrabajos(String clienteRetrabajos) {
		this.clienteRetrabajos = clienteRetrabajos;
	}
	public String getFirmasRetrabajos() {
		return firmasRetrabajos;
	}
	public void setFirmasRetrabajos(String firmasRetrabajos) {
		this.firmasRetrabajos = firmasRetrabajos;
	}
	public String getImagenRetrabajos() {
		return imagenRetrabajos;
	}
	public void setImagenRetrabajos(String imagenRetrabajos) {
		this.imagenRetrabajos = imagenRetrabajos;
	}
	public String getRealizadoPorPersonal() {
		return realizadoPorPersonal;
	}
	public void setRealizadoPorPersonal(String realizadoPorPersonal) {
		this.realizadoPorPersonal = realizadoPorPersonal;
	}
	public Date getFechaPersonal() {
		return fechaPersonal;
	}
	public void setFechaPersonal(Date fechaPersonal) {
		this.fechaPersonal = fechaPersonal;
	}
	public String getClientePersonal() {
		return clientePersonal;
	}
	public void setClientePersonal(String clientePersonal) {
		this.clientePersonal = clientePersonal;
	}
	public String getPiezaPersonal() {
		return piezaPersonal;
	}
	public void setPiezaPersonal(String piezaPersonal) {
		this.piezaPersonal = piezaPersonal;
	}
	public String getReferenciaPersonal() {
		return referenciaPersonal;
	}
	public void setReferenciaPersonal(String referenciaPersonal) {
		this.referenciaPersonal = referenciaPersonal;
	}
	public String getFirmasPersonal() {
		return firmasPersonal;
	}
	public void setFirmasPersonal(String firmasPersonal) {
		this.firmasPersonal = firmasPersonal;
	}
	public String getImagenPersonal() {
		return imagenPersonal;
	}
	public void setImagenPersonal(String imagenPersonal) {
		this.imagenPersonal = imagenPersonal;
	}
	public boolean isRetrabajos() {
		return retrabajos;
	}
	public void setRetrabajos(boolean retrabajos) {
		this.retrabajos = retrabajos;
	}
	public boolean isFormacion() {
		return formacion;
	}
	public void setFormacion(boolean formacion) {
		this.formacion = formacion;
	}
	public void setArrayHoraEspecialistaSabado(String arrayHoraEspecialistaSabado) {
		this.arrayHoraEspecialistaSabado = arrayHoraEspecialistaSabado;
	}
	
}
