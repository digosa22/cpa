package Main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		new VentanaPrincipal().setVisible(true);
//		Calendar c = Calendar.getInstance();
//		c.set(2015, 3, 5);
//		Date fecha1 = c.getTime();
//		Date fecha2 = c.getTime();
//		Date fecha3 = c.getTime();
//		Date fecha4 = c.getTime();
//		Date fecha5 = c.getTime();
//		Date fecha6 = c.getTime();
//		Date fecha7 = c.getTime();
//		
//		String tablaDefectos = "dsvgfvgds@;@1@;@2@;@3@;@4@;@5@;@6@;@6@;@0@;@1@;@1@;@1@;@1@;@2@;@4@;@5@;@5@;@5@;@4@;@7@;@7@;@8@;@8@;@9@;@8@;@8@;@7@;@2@;@1@;@1@;@2@;@jthyjygyj@;@1@;@44";
//		String piezasOK = "5@;@7@;@4@;@8@;@4@;@2@;@6";
//		String piezasRecuperadas = "7@;@6@;@4@;@9@;@5@;@5";
//		String recuentoFinal = "hgjh@;@jhgjhg@;@jgjhg@;@jhgjh@;@8@;@7@;@6@;@jhgjkh@;@jhgjh@;@jhgjh@;@hjgjh@;@5@;@4@;@2@;@jghjh@;@jhghj@;@jhgjh@;@gjhjh@;@4@;@3@;@4@;@hgjhg@;@jhgjhg@;@jhgjhg";
//		String arrayHoraNormal = "1@;@2@;@3@;@4@;@5@;@6@;@6@;@1@;@1@;@1@;@1@;@1@;@2@;@4@;@5@;@5@;@5@;@4@;@7@;@7@;@8@;@8@;@9@;@8@;@8@;@7@;@2@;@1@;@1@;@2";
//		String arrayHoraExtra = "8@;@7@;@8@;@6@;@7@;@4";
//		String arrayHoraSabado = "1@;@2@;@3@;@4@;@5@;@6@;@6@;@1@;@1@;@1@;@1@;@1@;@2@;@4@;@5@;@5@;@5@;@4@;@7@;@7@;@8@;@8@;@9@;@8@;@8@;@7@;@2@;@1@;@1@;@2";
//		String arrayHoraFestivo = "5@;@4@;@3@;@1@;@2@;@2";
//		String arrayHoraNocturna = "5@;@4@;@6@;@5@;@3";
//		String arrayHoraEspecialistaNormal = "1@;@2@;@3@;@4@;@5@;@6@;@6@;@1@;@1@;@1@;@1@;@1@;@2@;@4@;@5@;@5@;@5@;@4@;@7@;@7@;@8@;@8@;@9@;@8@;@8@;@7@;@2@;@1@;@1@;@2";
//		String arrayHoraEspecialistaExtra = "8@;@9@;@7@;@5@;@7@;@4";
//		String arrayHoraEspecialistaSabado = "8@;@9@;@7@;@5@;@7@;@4";
//		String arrayHoraEspecialistaFestiva = "9@;@8@;@7@;@6@;@7@;@6@;@5";
//		String arrayHoraEspecialistaNocturna = "8@;@7@;@6@;@5@;@4@;@7@;@8@;@5";
//		String arrayHoraCoordinacion = "9@;@8@;@0@;@7@;@0@;@7@;@5@;@0@;@4";
//		String arrayHoraAdministracion = "1@;@2@;@3@;@4@;@5@;@6@;@6@;@1@;@1@;@1@;@1@;@1@;@2@;@4@;@5@;@5@;@5@;@4@;@7@;@7@;@8@;@8@;@9@;@8@;@8@;@7@;@2@;@1@;@1@;@2";
//		String arrayGastosLogisticos = "7@;@6@;@8@;@2";
//		String arrayOtros1 = "8@;@7@;@5@;@4";
//		String arrayOtros2 = "7@;@5@;@4@;@2";
//		String firmasPersonal = "jghjghjghjgy@;@13-nov-2015@;@ghghjkh@;@10-mar-2015";
//		String firmasRetrabajos = "bjkhjh@;@20-mar-2015@;@746dsf@;@23-nov-2015";
//		String accionesIntruccion = "aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@1@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@0@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@1@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@1@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@0@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@0@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@1@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@1@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@0@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@0@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@1@;@aeiou@;@img/cuadrado.jpg#;#img/prueba.jpg@;@1";
		
//		Servicio serv = new Servicio("B4356756", "nomCliente", "personaCPA", "numAccion", fecha1, "nombrePieza", "referencias", "numChasis1", "numChasis2", "numChasis3", "numChasis4", "responsableCPA", true, true, false, false, true, false, "personaRecomendador", "departamentoRecomendador", "telefonoRecomendador", "emailRecomendador", fecha2, "descripcionInstruccionDelServicio", true, true, false, false, false, 1, "img/cpa.jpg", true, true, true, false, true, true, false, true, true, false, false, true, false, accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas, recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, "realizadoPorRetrabajos", fecha3, fecha4, "numReclamacionRetrabajos", fecha5, "referenciaPiezaRetrabajos", fecha6, "tiempoRetrabajos", "clienteRetrabajos", firmasRetrabajos, "img/cpa.jpg", "realizadoPorPersonal", fecha7, "clientePersonal", "piezaPersonal", "referenciaPersonal", firmasPersonal, "img/cpa.jpg");
		
//		Llamadas ll = new Llamadas();
//		
//		Servicio serv = ll.recuperarServicio("numAccion");
//		
//		new VentanaNuevo2(null, true, true, serv).setVisible(true);
	}
}