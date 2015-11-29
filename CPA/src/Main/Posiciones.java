package Main;

public class Posiciones {

	// ORDEN DE PEDIDO
	private Posicion numAccion = new Posicion(1,1); //B2 
	private Posicion fechaInicio = new Posicion(1,2); //B3 
	private Posicion fechaFin = new Posicion(1,3); //B4 
	private Posicion nombrePieza = new Posicion(1,4); //B5 
	private Posicion referencias = new Posicion(1,6); //B7 
	private Posicion numChasis1 = new Posicion(3,2); //D3 
	private Posicion numChasis2 = new Posicion(4,2); // E3
	private Posicion numChasis3 = new Posicion(3,3); // D4
	private Posicion numChasis4 = new Posicion(4,3); // E4
	private Posicion responsableCPA = new Posicion(5,3); // F4
	private Posicion piezasVerde = new Posicion(6,6); //G7 
	private Posicion piezasBlanco = new Posicion(6,7); // G8
	private Posicion piezasOtros = new Posicion(6,8); // G9
	private Posicion piezasRojo = new Posicion(6,9); // G10
	private Posicion contenedorVerde = new Posicion(7,6); // H7
	private Posicion contenedorRojo = new Posicion(7,9); // H10
	private Posicion personaRecomendador = new Posicion(1,11); //B12 
	private Posicion departamentoRecomendador = new Posicion(1,12); // B13
	private Posicion telefonoRecomendador = new Posicion(1,13); // B14
	private Posicion emailRecomendador = new Posicion(1,14); // B15
	private Posicion fechaSolicitudRecomendador = new Posicion(1,15); // B16
	private Posicion empresaCliente = new Posicion(1,17); // B18
	private Posicion direccionCliente = new Posicion(1,18); // B19
	private Posicion codigoPostalCliente = new Posicion(1,19); // B20
	private Posicion paisCliente = new Posicion(1,20); // B21
	private Posicion poblacionCliente = new Posicion(1,21); // B22
	private Posicion apartadoDeCorreosCliente = new Posicion(1,22); // B23
	private Posicion codigoPostalCliente2 = new Posicion(1,23); // B24
	private Posicion codigoPostalEmpresaCliente = new Posicion(1,24); // B25
	private Posicion personaDeContactoCliente = new Posicion(6,17); // G18
	private Posicion departamentoCliente = new Posicion(6,18); // G19
	private Posicion telefonoCliente = new Posicion(6,19); // G20
	private Posicion emailCliente = new Posicion(6,20); // G21
	private Posicion descripcionInstruccionDelServicio = new Posicion(0,27); // A28
	private Posicion seguridadCalzado = new Posicion(2,33); // C34
	private Posicion seguridadGafas = new Posicion(2,34); // C35
	private Posicion seguridadChaleco = new Posicion(2,35); // C36
	private Posicion seguridadTapones = new Posicion(2,36); // C37
	private Posicion seguridadGuantes = new Posicion(2,37); // C38
	private Posicion informacionResultadosDiaria = new Posicion(7,33); // H34
	private Posicion informacionResultadosSemanal = new Posicion(7,34); // H35
	private Posicion informacionResultadosMensual = new Posicion(7,35); // H36
	private Posicion informacionResultadosOtros = new Posicion(7,36); // H37
	
	
	
	// VALIDACION
	private Posicion imagenOrdenDePedido = new Posicion(10,0); // K1
	
	
	
	// INSTRUCCION DE TRABAJO
	private Posicion operario = new Posicion(7,3); // H4
	private Posicion peticionMaterialInstruccionAplica = new Posicion(7,15); // H16
	private Posicion peticionMaterialInstruccionNoAplica = new Posicion(8,15); // I16
	private Posicion referenciasCorrectasInstruccionAplica = new Posicion(7,16); // H17
	private Posicion referenciasCorrectasInstruccionNoAplica = new Posicion(8,16); // I17
	private Posicion seleccionPiezasInstruccionAplica = new Posicion(7,18); // H19
	private Posicion seleccionPiezasInstruccionNoAplica = new Posicion(8,18); // I19
	private Posicion retrabajoPiezasInstruccionAplica = new Posicion(7,19); // H20
	private Posicion retrabajoPiezasInstruccionNoAplica = new Posicion(8,19); // I20
	private Posicion trasvaseInstruccionAplica = new Posicion(7,20); // H21
	private Posicion trasvaseInstruccionNoAplica = new Posicion(8,20); // I21
	private Posicion otrosInstruccionAplica = new Posicion(7,21); // H22
	private Posicion otrosInstruccionNoAplica = new Posicion(8,21); // I22
	
	private Posicion accionesInstruccionDescripcionInicial = new Posicion(1,22); // B23
	private Posicion accionesInstruccionOtros1Inicial = new Posicion(3,22); // D23
	private Posicion accionesInstruccionOtros2Inicial = new Posicion(5,22); // F23
	private Posicion accionesInstruccionAplicaInicial = new Posicion(7,22); // H23
	private Posicion accionesInstruccionNoAplicaInicial = new Posicion(8,22); // I23
	
	
	private Posicion accionesInstruccionDescripcion12 = new Posicion(1,56); // B57
	private Posicion accionesInstruccionOtros121 = new Posicion(3,56); // D57
	private Posicion accionesInstruccionOtro122 = new Posicion(5,56); // F57
	private Posicion accionesInstruccionAplica12 = new Posicion(7,56); // H57
	private Posicion accionesInstruccionNoAplica12 = new Posicion(8,56); // I57
	
	// INFORMACION DE RESULTADOS
	private Posicion defecto1Nombre = new Posicion(1,2); // B3
	private Posicion defecto1Fechas = new Posicion(8,2); // I3
	private Posicion defecto2Nombre = new Posicion(1,3); // B4
	private Posicion defecto2Fechas = new Posicion(8,3); // I4
	private Posicion defecto3Nombre = new Posicion(1,4); // B5
	private Posicion defecto3Fechas = new Posicion(8,4); // I5
	private Posicion defecto4Nombre = new Posicion(1,5); // B6
	private Posicion defecto4Fechas = new Posicion(8,5); // I6
	private Posicion defecto5Nombre = new Posicion(1,6); // B7
	private Posicion defecto5Fechas = new Posicion(8,6); // I7
	private Posicion defecto6Nombre = new Posicion(1,7); // B8
	private Posicion defecto6Fechas = new Posicion(8,7); // I8
	private Posicion piezasOK = new Posicion(8,10); // I11
	private Posicion piezasRecuperadas = new Posicion(8,14); // I15 
	
	
	
	// RECUENTO FINAL	
	private Posicion recuentoFinal = new Posicion(0,3); // A4
	
	
	
	// ESTIMACION DE HORAS Y COSTES
	private Posicion arrayHoraNormal = new Posicion(2,2); // C3
	private Posicion arrayHoraExtra = new Posicion(2,3); // C4
	private Posicion arrayHoraSabado = new Posicion(2,4); // C5
	private Posicion arrayHoraFestivo = new Posicion(2,5); // C6
	private Posicion arrayHoraNocturna = new Posicion(2,6); // C7
	private Posicion arrayHoraEspecialistaNormal = new Posicion(2,7); // C8
	private Posicion arrayHoraEspecialistaExtra = new Posicion(2,8); // C9
	private Posicion arrayHoraEspecialistaSabado = new Posicion(2,9); // C10
	private Posicion arrayHoraEspecialistaFestivo = new Posicion(2,10); // C11
	private Posicion arrayHoraEspecialistaNocturna = new Posicion(2,11); // C12
	private Posicion arrayHoraCoordinacion = new Posicion(2,12); // C13
	private Posicion arrayHoraAdministracion = new Posicion(2,13); // C14
	private Posicion arrayGastosLogisticos = new Posicion(2,14); // C15
	private Posicion arrayOtros1 = new Posicion(2,15); // C16
	private Posicion arrayOtros2 = new Posicion(2,16); // C17
	
	
	
	// GAMA DE RETRABAJOS
	private Posicion realizadoPorRetrabajos = new Posicion(17,0); // R1
	private Posicion fechaRetrabajos = new Posicion(17,1); // R2
	private Posicion fechaLiberacionRetrabajos = new Posicion(17,3); // R4 
	private Posicion numReclamacionRetrabajos = new Posicion(0,5); // A6
	private Posicion fechaReclamacionRetrabajos = new Posicion(4,5); // E6
	private Posicion referenciaPiezaRetrabajos = new Posicion(6,5); // G6
	private Posicion fechaComienzoRetrabajos = new Posicion(18,5); // S6
	private Posicion tiempoRetrabajos = new Posicion(20,5); // U6
	private Posicion clienteRetrabajos = new Posicion(3,6); // D7
	private Posicion firmasRetrabajosNombres = new Posicion(1,9); // B10
	private Posicion firmasRetrabajosFechas = new Posicion(17,9); // R10
	private Posicion imagenRetrabajos = new Posicion(23,0); // X1
	
	
	
	// PERSONAL FORMADO
	private Posicion realizadoPorPersonal = new Posicion(17,0); // R1
	private Posicion fechaPersonal = new Posicion(17,1); // R2
	private Posicion clientePersonal = new Posicion(4,2); // E3
	private Posicion piezaPersonal = new Posicion(4,3); // E4
	private Posicion referenciaPersonal = new Posicion(4,4); // E5
	private Posicion firmasPersonalNombres = new Posicion(1,6); // B7
	private Posicion firmasPersonalFechas = new Posicion(17,6); // R7
	private Posicion imagenPersonal = new Posicion(23,0); // X1
	
	public Posiciones(){}

	public Posicion getNumAccion() {
		return numAccion;
	}

	public Posicion getFechaInicio() {
		return fechaInicio;
	}

	public Posicion getFechaFin() {
		return fechaFin;
	}

	public Posicion getNombrePieza() {
		return nombrePieza;
	}

	public Posicion getReferencias() {
		return referencias;
	}

	public Posicion getNumChasis1() {
		return numChasis1;
	}

	public Posicion getNumChasis2() {
		return numChasis2;
	}

	public Posicion getNumChasis3() {
		return numChasis3;
	}

	public Posicion getNumChasis4() {
		return numChasis4;
	}

	public Posicion getResponsableCPA() {
		return responsableCPA;
	}

	public Posicion getPiezasVerde() {
		return piezasVerde;
	}

	public Posicion getPiezasBlanco() {
		return piezasBlanco;
	}

	public Posicion getPiezasOtros() {
		return piezasOtros;
	}

	public Posicion getPiezasRojo() {
		return piezasRojo;
	}

	public Posicion getContenedorVerde() {
		return contenedorVerde;
	}

	public Posicion getContenedorRojo() {
		return contenedorRojo;
	}

	public Posicion getPersonaRecomendador() {
		return personaRecomendador;
	}

	public Posicion getDepartamentoRecomendador() {
		return departamentoRecomendador;
	}

	public Posicion getTelefonoRecomendador() {
		return telefonoRecomendador;
	}

	public Posicion getEmailRecomendador() {
		return emailRecomendador;
	}

	public Posicion getFechaSolicitudRecomendador() {
		return fechaSolicitudRecomendador;
	}

	public Posicion getEmpresaCliente() {
		return empresaCliente;
	}

	public Posicion getDireccionCliente() {
		return direccionCliente;
	}

	public Posicion getCodigoPostalCliente() {
		return codigoPostalCliente;
	}

	public Posicion getPaisCliente() {
		return paisCliente;
	}

	public Posicion getPoblacionCliente() {
		return poblacionCliente;
	}

	public Posicion getApartadoDeCorreosCliente() {
		return apartadoDeCorreosCliente;
	}

	public Posicion getCodigoPostalCliente2() {
		return codigoPostalCliente2;
	}

	public Posicion getCodigoPostalEmpresaCliente() {
		return codigoPostalEmpresaCliente;
	}

	public Posicion getPersonaDeContactoCliente() {
		return personaDeContactoCliente;
	}

	public Posicion getDepartamentoCliente() {
		return departamentoCliente;
	}

	public Posicion getTelefonoCliente() {
		return telefonoCliente;
	}

	public Posicion getEmailCliente() {
		return emailCliente;
	}

	public Posicion getDescripcionInstruccionDelServicio() {
		return descripcionInstruccionDelServicio;
	}

	public Posicion getSeguridadCalzado() {
		return seguridadCalzado;
	}

	public Posicion getSeguridadGafas() {
		return seguridadGafas;
	}

	public Posicion getSeguridadChaleco() {
		return seguridadChaleco;
	}

	public Posicion getSeguridadTapones() {
		return seguridadTapones;
	}

	public Posicion getSeguridadGuantes() {
		return seguridadGuantes;
	}

	public Posicion getInformacionResultadosDiaria() {
		return informacionResultadosDiaria;
	}

	public Posicion getInformacionResultadosSemanal() {
		return informacionResultadosSemanal;
	}

	public Posicion getInformacionResultadosMensual() {
		return informacionResultadosMensual;
	}

	public Posicion getInformacionResultadosOtros() {
		return informacionResultadosOtros;
	}

	public Posicion getImagenOrdenDePedido() {
		return imagenOrdenDePedido;
	}

	public Posicion getOperario() {
		return operario;
	}

	public Posicion getPeticionMaterialInstruccionAplica() {
		return peticionMaterialInstruccionAplica;
	}

	public Posicion getPeticionMaterialInstruccionNoAplica() {
		return peticionMaterialInstruccionNoAplica;
	}

	public Posicion getReferenciasCorrectasInstruccionAplica() {
		return referenciasCorrectasInstruccionAplica;
	}

	public Posicion getReferenciasCorrectasInstruccionNoAplica() {
		return referenciasCorrectasInstruccionNoAplica;
	}

	public Posicion getSeleccionPiezasInstruccionAplica() {
		return seleccionPiezasInstruccionAplica;
	}

	public Posicion getSeleccionPiezasInstruccionNoAplica() {
		return seleccionPiezasInstruccionNoAplica;
	}

	public Posicion getRetrabajoPiezasInstruccionAplica() {
		return retrabajoPiezasInstruccionAplica;
	}

	public Posicion getRetrabajoPiezasInstruccionNoAplica() {
		return retrabajoPiezasInstruccionNoAplica;
	}

	public Posicion getTrasvaseInstruccionAplica() {
		return trasvaseInstruccionAplica;
	}

	public Posicion getTrasvaseInstruccionNoAplica() {
		return trasvaseInstruccionNoAplica;
	}

	public Posicion getOtrosInstruccionAplica() {
		return otrosInstruccionAplica;
	}

	public Posicion getOtrosInstruccionNoAplica() {
		return otrosInstruccionNoAplica;
	}

	public Posicion getAccionesInstruccionDescripcionInicial() {
		return accionesInstruccionDescripcionInicial;
	}

	public Posicion getAccionesInstruccionOtros1Inicial() {
		return accionesInstruccionOtros1Inicial;
	}

	public Posicion getAccionesInstruccionOtros2Inicial() {
		return accionesInstruccionOtros2Inicial;
	}

	public Posicion getAccionesInstruccionAplicaInicial() {
		return accionesInstruccionAplicaInicial;
	}

	public Posicion getAccionesInstruccionNoAplicaInicial() {
		return accionesInstruccionNoAplicaInicial;
	}

	public Posicion getAccionesInstruccionDescripcion12() {
		return accionesInstruccionDescripcion12;
	}

	public Posicion getAccionesInstruccionOtros121() {
		return accionesInstruccionOtros121;
	}

	public Posicion getAccionesInstruccionOtro122() {
		return accionesInstruccionOtro122;
	}

	public Posicion getAccionesInstruccionAplica12() {
		return accionesInstruccionAplica12;
	}

	public Posicion getAccionesInstruccionNoAplica12() {
		return accionesInstruccionNoAplica12;
	}

	public Posicion getDefecto1Nombre() {
		return defecto1Nombre;
	}

	public Posicion getDefecto1Fechas() {
		return defecto1Fechas;
	}

	public Posicion getDefecto2Nombre() {
		return defecto2Nombre;
	}

	public Posicion getDefecto2Fechas() {
		return defecto2Fechas;
	}

	public Posicion getDefecto3Nombre() {
		return defecto3Nombre;
	}

	public Posicion getDefecto3Fechas() {
		return defecto3Fechas;
	}

	public Posicion getDefecto4Nombre() {
		return defecto4Nombre;
	}

	public Posicion getDefecto4Fechas() {
		return defecto4Fechas;
	}

	public Posicion getDefecto5Nombre() {
		return defecto5Nombre;
	}

	public Posicion getDefecto5Fechas() {
		return defecto5Fechas;
	}

	public Posicion getDefecto6Nombre() {
		return defecto6Nombre;
	}

	public Posicion getDefecto6Fechas() {
		return defecto6Fechas;
	}

	public Posicion getPiezasOK() {
		return piezasOK;
	}

	public Posicion getPiezasRecuperadas() {
		return piezasRecuperadas;
	}

	public Posicion getRecuentoFinal() {
		return recuentoFinal;
	}

	public Posicion getArrayHoraNormal() {
		return arrayHoraNormal;
	}

	public Posicion getArrayHoraExtra() {
		return arrayHoraExtra;
	}

	public Posicion getArrayHoraSabado() {
		return arrayHoraSabado;
	}

	public Posicion getArrayHoraFestivo() {
		return arrayHoraFestivo;
	}

	public Posicion getArrayHoraNocturna() {
		return arrayHoraNocturna;
	}

	public Posicion getArrayHoraEspecialistaNormal() {
		return arrayHoraEspecialistaNormal;
	}

	public Posicion getArrayHoraEspecialistaExtra() {
		return arrayHoraEspecialistaExtra;
	}

	public Posicion getArrayHoraEspecialistaSabado() {
		return arrayHoraEspecialistaSabado;
	}

	public Posicion getArrayHoraEspecialistaFestivo() {
		return arrayHoraEspecialistaFestivo;
	}

	public Posicion getArrayHoraEspecialistaNocturna() {
		return arrayHoraEspecialistaNocturna;
	}

	public Posicion getArrayHoraCoordinacion() {
		return arrayHoraCoordinacion;
	}

	public Posicion getArrayHoraAdministracion() {
		return arrayHoraAdministracion;
	}

	public Posicion getArrayGastosLogisticos() {
		return arrayGastosLogisticos;
	}

	public Posicion getArrayOtros1() {
		return arrayOtros1;
	}

	public Posicion getArrayOtros2() {
		return arrayOtros2;
	}

	public Posicion getRealizadoPorRetrabajos() {
		return realizadoPorRetrabajos;
	}

	public Posicion getFechaRetrabajos() {
		return fechaRetrabajos;
	}

	public Posicion getFechaLiberacionRetrabajos() {
		return fechaLiberacionRetrabajos;
	}

	public Posicion getNumReclamacionRetrabajos() {
		return numReclamacionRetrabajos;
	}

	public Posicion getFechaReclamacionRetrabajos() {
		return fechaReclamacionRetrabajos;
	}

	public Posicion getReferenciaPiezaRetrabajos() {
		return referenciaPiezaRetrabajos;
	}

	public Posicion getFechaComienzoRetrabajos() {
		return fechaComienzoRetrabajos;
	}

	public Posicion getTiempoRetrabajos() {
		return tiempoRetrabajos;
	}

	public Posicion getClienteRetrabajos() {
		return clienteRetrabajos;
	}

	public Posicion getFirmasRetrabajosNombres() {
		return firmasRetrabajosNombres;
	}

	public Posicion getFirmasRetrabajosFechas() {
		return firmasRetrabajosFechas;
	}

	public Posicion getImagenRetrabajos() {
		return imagenRetrabajos;
	}

	public Posicion getRealizadoPorPersonal() {
		return realizadoPorPersonal;
	}

	public Posicion getFechaPersonal() {
		return fechaPersonal;
	}

	public Posicion getClientePersonal() {
		return clientePersonal;
	}

	public Posicion getPiezaPersonal() {
		return piezaPersonal;
	}

	public Posicion getReferenciaPersonal() {
		return referenciaPersonal;
	}

	public Posicion getFirmasPersonalNombres() {
		return firmasPersonalNombres;
	}

	public Posicion getFirmasPersonalFechas() {
		return firmasPersonalFechas;
	}

	public Posicion getImagenPersonal() {
		return imagenPersonal;
	}
	
}
