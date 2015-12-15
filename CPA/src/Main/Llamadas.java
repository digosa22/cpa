package Main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Llamadas {

	public List<String> recuperarListaServiciosEnLinea() {
		Database database = new Database();

		List<String> listaServiciosEnLinea = new ArrayList<String>();
		try {
			Connection connection = database.Get_Connection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT num_accion FROM servicios_en_linea");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listaServiciosEnLinea.add(rs.getString("num_accion"));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaServiciosEnLinea;
	}

	public ClienteRecuperado recuperarCliente(int id) {

		Database database = new Database();

		ClienteRecuperado cliente = null;
		try {
			Connection connection = database.Get_Connection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM clientes WHERE id=" + id );
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cliente = new ClienteRecuperado(rs.getInt("id"), rs.getString("nif"), rs.getString("tipo_num_accion"), rs.getString("empresa"), rs.getString("tel"), rs.getString("persona_contacto"), rs.getString("web"), rs.getString("departamento"), 
						rs.getString("email"), rs.getString("banco"), rs.getString("num_ccc"), rs.getString("IBAN"), rs.getString("BIC"), rs.getString("idioma"), rs.getInt("num_proveedor"), rs.getDouble("hora_normal"), rs.getDouble("hora_extra"), 
						rs.getDouble("hora_sabado"), rs.getDouble("hora_festiva"), rs.getDouble("hora_nocturna"), rs.getDouble("hora_especialista_normal"), rs.getDouble("hora_especialista_extra"), rs.getDouble("hora_especialista_sabado"), 
						rs.getDouble("hora_especialista_festiva"), rs.getDouble("hora_especialista_nocturna"), rs.getDouble("hora_coordinacion"), rs.getDouble("hora_administracion"), rs.getDouble("gastos_logisticos"), rs.getString("direccion"), 
						rs.getString("codigo_postal"), rs.getString("pais"), rs.getString("poblacion"), rs.getString("apartado_de_correo"), rs.getString("codigo_postal_2"), rs.getString("codigo_postal_empresa"));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	

	public Servicio recuperarServicio(String num_accion) {

		Database database = new Database();

		Servicio servicio = null;
		try {
			Connection connection = database.Get_Connection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM servicios_en_linea WHERE num_accion='" + num_accion + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				servicio = new Servicio(rs.getInt("nif_cliente"), rs.getString("nom_cliente"), rs.getString("persona_cpa"),
						rs.getString("num_accion"), rs.getDate("fecha_inicio"), rs.getString("nombre_pieza"), rs.getString("referencias"),
						rs.getString("num_chasis_1"), rs.getString("num_chasis_2"), rs.getString("num_chasis_3"),rs.getString("num_chasis_4"),
						rs.getString("responsable_cpa"), rs.getBoolean("piezas_verde"), rs.getBoolean("piezas_blanco"), rs.getBoolean("piezas_otros"),
						rs.getBoolean("piezas_rojo"),  rs.getBoolean("contenedor_verde"),  rs.getBoolean("contenedor_rojo"), rs.getString("persona_recomendador"),
						rs.getString("departamento_recomendador"), rs.getString("telefono_recomendador"), rs.getString("email_recomendador"),
						rs.getDate("fecha_solicitud_recomendador"), rs.getString("descripcion_instruccion_del_servicio"), rs.getBoolean("seguridad_calzado"),
						rs.getBoolean("seguridad_gafas"), rs.getBoolean("seguridad_chaleco"), rs.getBoolean("seguridad_tapones"), rs.getBoolean("seguridad_guantes"),
						rs.getInt("informacion_resultados"), rs.getString("imagen_orden_de_pedido"), rs.getBoolean("operario_a1"),
						rs.getBoolean("operario_a2"), rs.getBoolean("operario_a3"), rs.getBoolean("operario_a4"), rs.getBoolean("operario_a5"),
						rs.getBoolean("operario_a6"), rs.getBoolean("operario_a7"), rs.getBoolean("peticion_material_instruccion"),
						rs.getBoolean("referencias_correctas_instruccion"),  rs.getBoolean("seleccion_piezas_instruccion"),
						rs.getBoolean("retrabajo_piezas_instruccion"), rs.getBoolean("trasvase_instruccion"),
						rs.getBoolean("otros_instruccion"), rs.getString("acciones_instruccion"), rs.getString("array_defectos"), rs.getString("array_piezas_ok"),
						rs.getString("array_piezas_recuperadas"), rs.getString("recuento_final"), rs.getString("array_hora_normal"),
						rs.getString("array_hora_extra"), rs.getString("array_hora_sabado"), rs.getString("array_hora_festivo"),
						rs.getString("array_hora_nocturna"), rs.getString("array_hora_especialista_normal"), rs.getString("array_hora_especialista_extra"),
						rs.getString("array_hora_especialista_sabado"), rs.getString("array_hora_especialista_festivo"),
						rs.getString("array_hora_especialista_nocturna"), rs.getString("array_hora_coordinacion"),
						rs.getString("array_hora_administracion"), rs.getString("array_gastos_logisticos"),
						rs.getString("array_otros1"), rs.getString("array_otros2"),
						rs.getString("realizado_por_retrabajos"), rs.getDate("fecha_retrabajos"), rs.getDate("fecha_liberacion_retrabajos"),
						rs.getString("num_reclamacion_retrabajos"), rs.getDate("fecha_reclamacion_retrabajos"), rs.getString("referencia_pieza_retrabajos"),
						rs.getDate("fecha_comienzo_retrabajos"), rs.getString("tiempo_retrabajos"), rs.getString("clientes_retrabajos"),
						rs.getString("firmas_retrabajos"), rs.getString("imagen_retrabajos"), rs.getString("realizado_por_personal"),
						rs.getDate("fecha_personal"), rs.getString("cliente_personal"), rs.getString("pieza_personal"),
						rs.getString("referencia_personal"), rs.getString("firmas_personal"), rs.getString("imagen_personal"), 
						rs.getBoolean("retrabajos"), rs.getBoolean("formacion"), rs.getString("nombre_carpeta"));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicio;
	}

	public boolean introducirServicio(Servicio servicio) {

		Database database = new Database();
		Connection connection;
		try {
			connection = database.Get_Connection();
			PreparedStatement preparedStatement;

			preparedStatement = connection
					.prepareStatement("REPLACE INTO servicios_en_linea (nif_cliente, nom_cliente, persona_cpa,"
							+ " num_accion, fecha_inicio, nombre_pieza, referencias,"
							+ " num_chasis_1, num_chasis_2, num_chasis_3, num_chasis_4,"
							+ " responsable_cpa, piezas_verde, piezas_blanco, piezas_otros,"
							+ " piezas_rojo, contenedor_verde, contenedor_rojo, persona_recomendador,"
							+ " departamento_recomendador, telefono_recomendador, email_recomendador,"
							+ " fecha_solicitud_recomendador, descripcion_instruccion_del_servicio,"
							+ " seguridad_calzado, seguridad_gafas, seguridad_chaleco, seguridad_tapones,"
							+ " seguridad_guantes, informacion_resultados, imagen_orden_de_pedido,"
							+ " operario_a1, operario_a2, operario_a3, operario_a4, operario_a5,"
							+ " operario_a6, operario_a7, peticion_material_instruccion,"
							+ " referencias_correctas_instruccion, seleccion_piezas_instruccion,"
							+ " retrabajo_piezas_instruccion, trasvase_instruccion, otros_instruccion,"
							+ " acciones_instruccion, array_defectos, array_piezas_ok,"
							+ " array_piezas_recuperadas, recuento_final, array_hora_normal,"
							+ " array_hora_extra, array_hora_sabado, array_hora_festivo, array_hora_nocturna,"
							+ " array_hora_especialista_normal, array_hora_especialista_extra, array_hora_especialista_sabado,"
							+ " array_hora_especialista_festivo, array_hora_especialista_nocturna,"
							+ " array_hora_coordinacion, array_hora_administracion, array_gastos_logisticos,"
							+ " array_otros1, array_otros2, realizado_por_retrabajos, fecha_retrabajos,"
							+ " fecha_liberacion_retrabajos, num_reclamacion_retrabajos, fecha_reclamacion_retrabajos,"
							+ " referencia_pieza_retrabajos, fecha_comienzo_retrabajos, tiempo_retrabajos,"
							+ " clientes_retrabajos, firmas_retrabajos, imagen_retrabajos, realizado_por_personal,"
							+ " fecha_personal, cliente_personal, pieza_personal, referencia_personal, firmas_personal,"
							+ " imagen_personal, retrabajos, formacion, nombre_carpeta"
							+ ") VALUES(?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?,?,?,?,?,?,"
							+ "?,?,?,?,?"
							+ ")");

			preparedStatement.setInt(1, servicio.getIdCliente());
			preparedStatement.setString(2, servicio.getNomCliente());
			preparedStatement.setString(3, servicio.getPersonaCPA());
			preparedStatement.setString(4, servicio.getNumAccion());
			preparedStatement.setDate(5, new java.sql.Date(servicio.getFechaInicio().getTime()));

			preparedStatement.setString(6, servicio.getNombrePieza());
			preparedStatement.setString(7, servicio.getReferencias());
			preparedStatement.setString(8, servicio.getNumChasis1());
			preparedStatement.setString(9, servicio.getNumChasis2());
			preparedStatement.setString(10, servicio.getNumChasis3());
			preparedStatement.setString(11, servicio.getNumChasis4());
			preparedStatement.setString(12, servicio.getResponsableCPA());
			preparedStatement.setBoolean(13, servicio.isPiezasVerde());
			preparedStatement.setBoolean(14, servicio.isPiezasBlanco());
			preparedStatement.setBoolean(15, servicio.isPiezasOtros());
			preparedStatement.setBoolean(16, servicio.isPiezasRojo());
			preparedStatement.setBoolean(17, servicio.isContenedorVerde());
			preparedStatement.setBoolean(18, servicio.isContenedorRojo());
			preparedStatement.setString(19, servicio.getPersonaRecomendador());
			preparedStatement.setString(20, servicio.getDepartamentoRecomendador());
			preparedStatement.setString(21, servicio.getTelefonoRecomendador());
			preparedStatement.setString(22, servicio.getEmailRecomendador());
			java.sql.Date fechaSolicitudRecomendador = null;
			if (servicio.getFechaSolicitudRecomendador() != null) {
				fechaSolicitudRecomendador = new java.sql.Date(servicio.getFechaSolicitudRecomendador().getTime());
			}
			preparedStatement.setDate(23, fechaSolicitudRecomendador);
			preparedStatement.setString(24, servicio.getDescripcionInstruccionDelServicio());
			preparedStatement.setBoolean(25, servicio.isSeguridadCalzado());
			preparedStatement.setBoolean(26, servicio.isSeguridadGafas());
			preparedStatement.setBoolean(27, servicio.isSeguridadChaleco());
			preparedStatement.setBoolean(28, servicio.isSeguridadTapones());
			preparedStatement.setBoolean(29, servicio.isSeguridadGuantes());
			preparedStatement.setInt(30, servicio.getInformacionResultados());
			preparedStatement.setString(31, servicio.getImagenOrdenDePedido());

			preparedStatement.setBoolean(32, servicio.isOperarioA1());
			preparedStatement.setBoolean(33, servicio.isOperarioA2());
			preparedStatement.setBoolean(34, servicio.isOperarioA3());
			preparedStatement.setBoolean(35, servicio.isOperarioA4());
			preparedStatement.setBoolean(36, servicio.isOperarioA5());
			preparedStatement.setBoolean(37, servicio.isOperarioA6());
			preparedStatement.setBoolean(38, servicio.isOperarioA7());
			preparedStatement.setBoolean(39, servicio.isPeticionMaterialInstruccion());
			preparedStatement.setBoolean(40, servicio.isReferenciasCorrectasInstruccion());
			preparedStatement.setBoolean(41, servicio.isSeleccionPiezasInstruccion());
			preparedStatement.setBoolean(42, servicio.isRetrabajoPiezasInstruccion());
			preparedStatement.setBoolean(43, servicio.isTrasvaseInstruccion());
			preparedStatement.setBoolean(44, servicio.isOtrosInstruccion());
			preparedStatement.setString(45, servicio.getAccionesIntruccion());

			preparedStatement.setString(46, servicio.getTablaDefectos());
			preparedStatement.setString(47, servicio.getPiezasOK());
			preparedStatement.setString(48, servicio.getPiezasRecuperadas());
			preparedStatement.setString(49, servicio.getRecuentoFinal());
			preparedStatement.setString(50, servicio.getArrayHoraNormal());
			preparedStatement.setString(51, servicio.getArrayHoraExtra());
			preparedStatement.setString(52, servicio.getArrayHoraSabado());
			preparedStatement.setString(53, servicio.getArrayHoraFestivo());
			preparedStatement.setString(54, servicio.getArrayHoraNocturna());
			preparedStatement.setString(55, servicio.getArrayHoraEspecialistaNormal());
			preparedStatement.setString(56, servicio.getArrayHoraEspecialistaExtra());
			preparedStatement.setString(57, servicio.getArrayHoraEspecialistaSabado());
			preparedStatement.setString(58, servicio.getArrayHoraEspecialistaFestiva());
			preparedStatement.setString(59, servicio.getArrayHoraEspecialistaNocturna());
			preparedStatement.setString(60, servicio.getArrayHoraCoordinacion());
			preparedStatement.setString(61, servicio.getArrayHoraAdministracion());
			preparedStatement.setString(62, servicio.getArrayGastosLogisticos());
			preparedStatement.setString(63, servicio.getArrayOtros1());
			preparedStatement.setString(64, servicio.getArrayOtros2());

			if (servicio.getRealizadoPorRetrabajos() != null)
				preparedStatement.setString(65, servicio.getRealizadoPorRetrabajos());
			else
				preparedStatement.setString(65, null);
			java.sql.Date FechaRetrabajos = null;
			if (servicio.getFechaRetrabajos() != null) {
				FechaRetrabajos = new java.sql.Date(servicio.getFechaRetrabajos().getTime());
			}
			preparedStatement.setDate(66, FechaRetrabajos);
			java.sql.Date FechaLiberacionRetrabajos = null;
			if (servicio.getFechaLiberacionRetrabajos() != null) {
				FechaLiberacionRetrabajos = new java.sql.Date(servicio.getFechaLiberacionRetrabajos().getTime());
			}
			preparedStatement.setDate(67, FechaLiberacionRetrabajos);
			if (servicio.getNumReclamacionRetrabajos() != null)
				preparedStatement.setString(68, servicio.getNumReclamacionRetrabajos());
			else
				preparedStatement.setString(68, null);
			java.sql.Date FechaReclamacionRetrabajos = null;
			if (servicio.getFechaReclamacionRetrabajos() != null) {
				FechaReclamacionRetrabajos = new java.sql.Date(servicio.getFechaReclamacionRetrabajos().getTime());
			}
			preparedStatement.setDate(69, FechaReclamacionRetrabajos);
			if (servicio.getReferenciaPiezaRetrabajos() != null)
				preparedStatement.setString(70, servicio.getReferenciaPiezaRetrabajos());
			else
				preparedStatement.setString(70, null);
			java.sql.Date FechaComienzoRetrabajos = null;
			if (servicio.getFechaComienzoRetrabajos() != null) {
				FechaComienzoRetrabajos = new java.sql.Date(servicio.getFechaComienzoRetrabajos().getTime());
			}
			preparedStatement.setDate(71, FechaComienzoRetrabajos);
			if (servicio.getTiempoRetrabajos() != null)
				preparedStatement.setString(72, servicio.getTiempoRetrabajos());
			else
				preparedStatement.setString(72, null);
			if (servicio.getClienteRetrabajos() != null)
				preparedStatement.setString(73, servicio.getClienteRetrabajos());
			else
				preparedStatement.setString(73, null);
			if (servicio.getFirmasRetrabajos() != null)
				preparedStatement.setString(74, servicio.getFirmasRetrabajos());
			else
				preparedStatement.setString(74, null);
			if (servicio.getImagenRetrabajos() != null)
				preparedStatement.setString(75, servicio.getImagenRetrabajos());
			else
				preparedStatement.setString(75, null);


			if (servicio.getRealizadoPorPersonal() != null)
				preparedStatement.setString(76, servicio.getRealizadoPorPersonal());
			else
				preparedStatement.setString(76, null);
			java.sql.Date FechaPersonal = null;
			if (servicio.getFechaPersonal() != null) {
				FechaPersonal = new java.sql.Date(servicio.getFechaPersonal().getTime());
			}
			preparedStatement.setDate(77, FechaPersonal);
			if (servicio.getClientePersonal() != null)
				preparedStatement.setString(78, servicio.getClientePersonal());
			else
				preparedStatement.setString(78, null);
			if (servicio.getPiezaPersonal() != null)
				preparedStatement.setString(79, servicio.getPiezaPersonal());
			else
				preparedStatement.setString(79, null);
			if (servicio.getReferenciaPersonal() != null)
				preparedStatement.setString(80, servicio.getReferenciaPersonal());
			else
				preparedStatement.setString(80, null);
			if (servicio.getFirmasPersonal() != null)
				preparedStatement.setString(81, servicio.getFirmasPersonal());
			else
				preparedStatement.setString(81, null);
			if (servicio.getImagenPersonal() != null)
				preparedStatement.setString(82, servicio.getImagenPersonal());
			else
				preparedStatement.setString(82, null);
			
			preparedStatement.setBoolean(83, servicio.isRetrabajos());
			preparedStatement.setBoolean(84, servicio.isFormacion());
			preparedStatement.setString(85, servicio.getNombreCarpeta());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean introducirServicioTerminado(Servicio servicio) {

		Database database = new Database();
		Connection connection;
		try {
			connection = database.Get_Connection();
			PreparedStatement preparedStatement;

			preparedStatement = connection
					.prepareStatement("INSERT INTO servicios_terminados (nif_cliente, nom_cliente, persona_cpa,"
							+ " num_accion, fecha_inicio, nombre_pieza, referencias,"
							+ " num_chasis_1, num_chasis_2, num_chasis_3, num_chasis_4,"
							+ " responsable_cpa, piezas_verde, piezas_blanco, piezas_otros,"
							+ " piezas_rojo, contenedor_verde, contenedor_rojo, persona_recomendador,"
							+ " departamento_recomendador, telefono_recomendador, email_recomendador,"
							+ " fecha_solicitud_recomendador, descripcion_instruccion_del_servicio,"
							+ " seguridad_calzado, seguridad_gafas, seguridad_chaleco, seguridad_tapones,"
							+ " seguridad_guantes, informacion_resultados, imagen_orden_de_pedido,"
							+ " operario_a1, operario_a2, operario_a3, operario_a4, operario_a5,"
							+ " operario_a6, operario_a7, peticion_material_instruccion,"
							+ " referencias_correctas_instruccion, seleccion_piezas_instruccion,"
							+ " retrabajo_piezas_instruccion, trasvase_instruccion, otros_instruccion,"
							+ " acciones_instruccion, array_defectos, array_piezas_ok,"
							+ " array_piezas_recuperadas, recuento_final, array_hora_normal,"
							+ " array_hora_extra, array_hora_sabado, array_hora_festivo, array_hora_nocturna,"
							+ " array_hora_especialista_normal, array_hora_especialista_extra, array_hora_especialista_sabado,"
							+ " array_hora_especialista_fesctivo, array_hora_especialista_nocturna,"
							+ " array_hora_coordinacion, array_hora_administracion, array_gastos_logisticos,"
							+ " array_otros1, array_otros2, realizado_por_retrabajos, fecha_retrabajos,"
							+ " fecha_liberacion_retrabajos, num_reclamacion_retrabajos, fecha_reclamacion_retrabajos,"
							+ " referencia_pieza_retrabajos, fecha_comienzo_retrabajos, tiempo_retrabajos,"
							+ " clientes_retrabajos, firmas_retrabajos, imagen_retrabajos, realizado_por_personal,"
							+ " fecha_personal, cliente_personal, pieza_personal, referencia_personal, firmas_personal,"
							+ " imagen_personal, retrabajos, formacion, nombre_carpeta"
							+ ") VALUES(?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ ",?,?,?,?,?,?,?,?,?,?"
							+ "?,?,?,?,?)");

			preparedStatement.setInt(1, servicio.getIdCliente());
			preparedStatement.setString(2, servicio.getNomCliente());
			preparedStatement.setString(3, servicio.getPersonaCPA());
			preparedStatement.setString(4, servicio.getNumAccion());
			preparedStatement.setDate(5, new java.sql.Date(servicio.getFechaInicio().getTime()));

			preparedStatement.setString(6, servicio.getNombrePieza());
			preparedStatement.setString(7, servicio.getReferencias());
			preparedStatement.setString(8, servicio.getNumChasis1());
			preparedStatement.setString(9, servicio.getNumChasis2());
			preparedStatement.setString(10, servicio.getNumChasis3());
			preparedStatement.setString(11, servicio.getNumChasis4());
			preparedStatement.setString(12, servicio.getResponsableCPA());
			preparedStatement.setBoolean(13, servicio.isPiezasVerde());
			preparedStatement.setBoolean(14, servicio.isPiezasBlanco());
			preparedStatement.setBoolean(15, servicio.isPiezasOtros());
			preparedStatement.setBoolean(16, servicio.isPiezasRojo());
			preparedStatement.setBoolean(17, servicio.isContenedorVerde());
			preparedStatement.setBoolean(18, servicio.isContenedorRojo());
			preparedStatement.setString(19, servicio.getPersonaRecomendador());
			preparedStatement.setString(20, servicio.getDepartamentoRecomendador());
			preparedStatement.setString(21, servicio.getTelefonoRecomendador());
			preparedStatement.setString(22, servicio.getEmailRecomendador());
			preparedStatement.setDate(23, new java.sql.Date(servicio.getFechaSolicitudRecomendador().getTime()));
			preparedStatement.setString(24, servicio.getDescripcionInstruccionDelServicio());
			preparedStatement.setBoolean(25, servicio.isSeguridadCalzado());
			preparedStatement.setBoolean(26, servicio.isSeguridadGafas());
			preparedStatement.setBoolean(27, servicio.isSeguridadChaleco());
			preparedStatement.setBoolean(28, servicio.isSeguridadTapones());
			preparedStatement.setBoolean(29, servicio.isSeguridadGuantes());
			preparedStatement.setInt(30, servicio.getInformacionResultados());
			preparedStatement.setString(31, servicio.getImagenOrdenDePedido());

			preparedStatement.setBoolean(32, servicio.isOperarioA1());
			preparedStatement.setBoolean(33, servicio.isOperarioA2());
			preparedStatement.setBoolean(34, servicio.isOperarioA3());
			preparedStatement.setBoolean(35, servicio.isOperarioA4());
			preparedStatement.setBoolean(36, servicio.isOperarioA5());
			preparedStatement.setBoolean(37, servicio.isOperarioA6());
			preparedStatement.setBoolean(38, servicio.isOperarioA7());
			preparedStatement.setBoolean(39, servicio.isPeticionMaterialInstruccion());
			preparedStatement.setBoolean(40, servicio.isReferenciasCorrectasInstruccion());
			preparedStatement.setBoolean(41, servicio.isSeleccionPiezasInstruccion());
			preparedStatement.setBoolean(42, servicio.isRetrabajoPiezasInstruccion());
			preparedStatement.setBoolean(43, servicio.isTrasvaseInstruccion());
			preparedStatement.setBoolean(44, servicio.isOtrosInstruccion());
			preparedStatement.setString(45, servicio.getAccionesIntruccion());

			preparedStatement.setString(46, servicio.getTablaDefectos());
			preparedStatement.setString(47, servicio.getPiezasOK());
			preparedStatement.setString(48, servicio.getPiezasRecuperadas());
			preparedStatement.setString(49, servicio.getRecuentoFinal());
			preparedStatement.setString(50, servicio.getArrayHoraNormal());
			preparedStatement.setString(51, servicio.getArrayHoraExtra());
			preparedStatement.setString(52, servicio.getArrayHoraSabado());
			preparedStatement.setString(53, servicio.getArrayHoraFestivo());
			preparedStatement.setString(54, servicio.getArrayHoraNocturna());
			preparedStatement.setString(55, servicio.getArrayHoraEspecialistaNormal());
			preparedStatement.setString(56, servicio.getArrayHoraEspecialistaExtra());
			preparedStatement.setString(57, servicio.getArrayHoraEspecialistaSabado());
			preparedStatement.setString(58, servicio.getArrayHoraEspecialistaFestiva());
			preparedStatement.setString(59, servicio.getArrayHoraEspecialistaNocturna());
			preparedStatement.setString(60, servicio.getArrayHoraCoordinacion());
			preparedStatement.setString(61, servicio.getArrayHoraAdministracion());
			preparedStatement.setString(62, servicio.getArrayGastosLogisticos());
			preparedStatement.setString(63, servicio.getArrayOtros1());
			preparedStatement.setString(64, servicio.getArrayOtros2());

			preparedStatement.setString(65, servicio.getRealizadoPorRetrabajos());
			preparedStatement.setDate(66, new java.sql.Date(servicio.getFechaRetrabajos().getTime()));
			preparedStatement.setDate(67, new java.sql.Date(servicio.getFechaLiberacionRetrabajos().getTime()));
			preparedStatement.setString(68, servicio.getNumReclamacionRetrabajos());
			preparedStatement.setDate(69, new java.sql.Date(servicio.getFechaReclamacionRetrabajos().getTime()));
			preparedStatement.setString(70, servicio.getReferenciaPiezaRetrabajos());
			preparedStatement.setDate(71, new java.sql.Date(servicio.getFechaComienzoRetrabajos().getTime()));
			preparedStatement.setString(72, servicio.getTiempoRetrabajos());
			preparedStatement.setString(73, servicio.getClienteRetrabajos());
			preparedStatement.setString(74, servicio.getFirmasRetrabajos());
			preparedStatement.setString(75, servicio.getImagenRetrabajos());

			preparedStatement.setString(76, servicio.getRealizadoPorPersonal());
			preparedStatement.setDate(77, new java.sql.Date(servicio.getFechaPersonal().getTime()));
			preparedStatement.setString(78, servicio.getClientePersonal());
			preparedStatement.setString(79, servicio.getPiezaPersonal());
			preparedStatement.setString(80, servicio.getReferenciaPersonal());
			preparedStatement.setString(81, servicio.getFirmasPersonal());
			preparedStatement.setString(82, servicio.getImagenPersonal());
			
			preparedStatement.setBoolean(83, servicio.isRetrabajos());
			preparedStatement.setBoolean(84, servicio.isFormacion());
			preparedStatement.setString(85, servicio.getNombreCarpeta());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean actualizarImagen(String columnaAActualizar, String imagen, String numAccion) {

		Database database = new Database();
		Connection connection;
		try {
			connection = database.Get_Connection();
			Statement statement = connection.createStatement();

			statement
			.executeUpdate("UPDATE servicios_en_linea SET "+columnaAActualizar+"='"
					+ imagen
					+ "' where num_accion='"
					+ numAccion + "'");
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean actualizarImagenes(String imagenes, String numAccion) {

		Database database = new Database();
		Connection connection;
		try {
			connection = database.Get_Connection();
			Statement statement = connection.createStatement();

			statement
			.executeUpdate("UPDATE servicios_en_linea SET acciones_instruccion='"
					+ imagenes
					+ "' where num_accion='"
					+ numAccion + "'");
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean borrarServicio(String num_accion) {

		Database database = new Database();
		Connection connection;
		try {
			connection = database.Get_Connection();
			Statement statement = connection.createStatement();
			statement
			.executeUpdate("DELETE FROM servicios_en_linea WHERE num_accion='"
					+ num_accion + "'");
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}


	public boolean servicioTerminado(String num_accion) {

		Servicio servicio = recuperarServicio(num_accion);

		introducirServicioTerminado(servicio);

		borrarServicio(num_accion);

		return true;
	}
	
	public ArrayList<Cliente> recuperarClientes() {

		Database database = new Database();

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Connection connection = database.Get_Connection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM clientes ORDER BY empresa");
			Cliente cliente = new Cliente();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cliente = new Cliente(rs.getInt("id"), rs.getString("empresa"), rs.getString("tipo_num_accion"));
				clientes.add(cliente);
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public ArrayList<String> recuperarPersonalCPA() {

		Database database = new Database();

		ArrayList<String> personasCPA = new ArrayList<String>();
		try {
			Connection connection = database.Get_Connection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM personal_cpa ORDER BY nombre");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				personasCPA.add(rs.getString("nombre"));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personasCPA;
	}
	
	public String generarNumAccion(String tipo) {

		Database database = new Database();
		String numero = "";
		try {
			Connection connection = database.Get_Connection();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM generar_num_accion WHERE  tipo='" + tipo +"'");
			ResultSet rs = ps.executeQuery();
			Statement statement = null;
			if (rs.next()) {
				
				int anyo = rs.getInt("anyo");
				int num = rs.getInt("num");
				Calendar cal = Calendar.getInstance();
				if (anyo == cal.get(Calendar.YEAR)) {
					
					anyo = cal.get(Calendar.YEAR);
					String year = anyo + "";
					year = year.substring(2);
					
					String longitud = num + "";					
					if(longitud.length() == 1) {
						numero = tipo + "000"+ longitud+ "-" + year;
					} else if(longitud.length() == 2) {
						numero = tipo + "00"+ longitud+ "-" + year;
					} else if(longitud.length() == 3) {
						numero = tipo + "0"+ longitud+ "-" + year;
					} else {
						numero = tipo + ""+ longitud+ "-" + year;
					}
					
					statement = connection.createStatement();
					
					num++;

					statement
					.executeUpdate("UPDATE generar_num_accion SET num='"
							+ num
							+ "' WHERE tipo = '" + tipo + "'");
					
				} else {
					
					anyo = cal.get(Calendar.YEAR);
					String year = anyo + "";
					year = year.substring(2);
					
					numero = tipo + "0001-" + year;
					
					statement = connection.createStatement();

					statement
					.executeUpdate("UPDATE generar_num_accion SET anyo='"
							+ anyo
							+ "',num='2' WHERE tipo = '" + tipo + "'");
				}
				
			}
			statement.close();
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numero;
	}
	
	public boolean nuevoMensaje(Mensaje mensaje) {

		Database database = new Database();
		Connection connection;
		try {
			connection = database.Get_Connection();
			PreparedStatement preparedStatement;

			preparedStatement = connection
					.prepareStatement("REPLACE INTO mensajes (para, cc, asunto, mensaje, ruta) VALUES (?,?,?,?,?)");

			preparedStatement.setString(1, mensaje.getPara());
			preparedStatement.setString(2, mensaje.getCc());
			preparedStatement.setString(3, mensaje.getAsunto());
			preparedStatement.setString(4, mensaje.getMensaje());
			preparedStatement.setString(5, mensaje.getRuta());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
