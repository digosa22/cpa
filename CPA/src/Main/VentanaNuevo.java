package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;

public class VentanaNuevo extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private JPanel ordenDePedido;
	private JButton confirmacionValidacion;
	private JTextField nombrePieza;
	private JTextField referencias;
	private JTextField numChasis1;
	private JTextField numChasis2;
	private JTextField numChasis3;
	private JTextField numChasis4;
	private JTextField responsable;
	private JCheckBox piezasVerde;
	private JCheckBox piezasBlanco;
	private JCheckBox piezasOtros;
	private JCheckBox piezasRojo;
	private JCheckBox contenedorVerde;
	private JCheckBox contenedorRojo;
	private JTextField recomPersonaContacto;
	private JTextField recomDepartamento;
	private JTextField recomTelefono;
	private JTextField recomEmail;
	private JDateChooser recomFechaSolicitud;
	private JTextField descripcionServicio;
	private JCheckBox calzado;
	private JCheckBox gafas;
	private JCheckBox chaleco;
	private JCheckBox tapones;
	private JCheckBox guantes;
	private JRadioButton diaria;
	private JRadioButton semanal;
	private JRadioButton mensual;
	private JRadioButton otros;


	private JPanel instruccionDeTrabajo;
	private JCheckBox operarioA1;
	private JCheckBox operarioA2;
	private JCheckBox operarioA3;
	private JCheckBox operarioA4;
	private JCheckBox operarioA5;
	private JCheckBox operarioA6;
	private JCheckBox operarioA7;
	private JCheckBox peticionMaterial;
	private JCheckBox referenciasPiezas;
	private JCheckBox seleccion;
	private JCheckBox retrabajo;
	private JCheckBox trasvase;
	private JCheckBox otrosPiezas;
	private JTable trabajos;
	private String [] arrayImagenes;


	private JPanel informacion;
	private JTable defectos;
	private JTable tablaHeaders2;
	private JTable piezas;


	private JPanel recuentoFinal;
	private JTable recuento;


	private JPanel estimacionHorasCostes;
	private JTable horas;


	private JPanel gamaRetrabajos;
	private JButton confirmacionGamaRetrabajos;
	private JTextField realizadoRetrabajos;
	private JDateChooser fechaRetrabajos;
	private JDateChooser fechaLiberacion;
	private JTextField numReclamacion;
	private JDateChooser fechaReclamacion;
	private JTextField referenciaRetrabajos;
	private JDateChooser fechaComienzo;
	private JTextField tiempo;
	private JTextField clienteRetrabajos;
	private ArrayList<JDateChooser> arrayFechasRetrabajos;
	private ArrayList<JTextField> arrayNombresRetrabajos;


	private JPanel formacionPersonal;
	private JButton confirmacionFormacionPersonal;
	private JTextField realizadoFormado;
	private JTextField clienteFormado;
	private JTextField piezaFormado;
	private JTextField referenciaFormado;
	private JDateChooser fechaFormado;
	private ArrayList<JDateChooser> arrayFechasPersonal;
	private ArrayList<JTextField> arrayNombresPersonal;

	private JLabel fechaLabel;
	private JLabel numAccionLabel;
	private JLabel personalCPALabel;
	private JLabel clienteLabel;
	private JLabel fechaLabel2;
	private JLabel numAccionLabel2;
	private JLabel personalCPALabel2;
	private JLabel clienteLabel2;
	private JLabel tituloLabel;
	private JButton cambiarCliente;

	private VentanaPrincipal ventanaPrincipal;
	private boolean anadirRetrabajos;
	private boolean anadirFormacion;
	private String persona;
	private Llamadas llamadas;
	private Cliente cliente;
	private String numAccion;
	private String nombreCarpeta;
	
	private Utilidades utilidades;

	public VentanaNuevo(VentanaPrincipal ventPrin, boolean anadirRetra, boolean anadirForma, Cliente cli, String pers, String numA, Llamadas llam) {

		super(ventPrin, true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}
		
		utilidades = new Utilidades();

		panel = new JPanel();
		panel.setLayout(null);

		JTabbedPane panelDePestanas = new JTabbedPane(JTabbedPane.TOP);
		panelDePestanas.setBounds(20, 100, 800, 420);
		panel.add(panelDePestanas);

		tablaHeaders2 = new JTable();

		cliente = cli;
		numAccion = numA;
		ventanaPrincipal = ventPrin;
		llamadas = llam;
		anadirRetrabajos = anadirRetra;
		anadirFormacion = anadirForma;
		persona = pers;
		Calendar fechaNomCarpeta = Calendar.getInstance();
		SimpleDateFormat sdfNomCarpeta = new SimpleDateFormat("HHmmss");
		nombreCarpeta = numAccion + sdfNomCarpeta.format(fechaNomCarpeta.getTime());

		//TODO PESTAÑA ORDEN DE PEDIDO
		ordenDePedido = new JPanel();
		panelDePestanas.addTab("Orden de pedido", null, ordenDePedido, null);
		ordenDePedido.setLayout(null);
		confirmacionValidacion = new JButton("Añadir imagen");
		confirmacionValidacion.setBounds(40, 20, 100, 20);
		ordenDePedido.add(confirmacionValidacion);

		confirmacionValidacion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JPanel panelGenerales = new JPanel();
		panelGenerales.setBorder(BorderFactory.createTitledBorder("Datos generales"));
		panelGenerales.setBounds(10, 50, 450, 160);
		panelGenerales.setLayout(null);
		ordenDePedido.add(panelGenerales);
		JPanel panelControlesCalidad = new JPanel();
		panelControlesCalidad.setBorder(BorderFactory.createTitledBorder("Controles de calidad"));
		panelControlesCalidad.setBounds(470, 50, 315, 160);
		panelControlesCalidad.setLayout(null);
		ordenDePedido.add(panelControlesCalidad);
		JPanel panelRecomendador = new JPanel();
		panelRecomendador.setBorder(BorderFactory.createTitledBorder("Recomendador"));
		panelRecomendador.setBounds(10, 220, 320, 160);
		panelRecomendador.setLayout(null);
		ordenDePedido.add(panelRecomendador);
		JPanel panelInstruccionServicio = new JPanel();
		panelInstruccionServicio.setBorder(BorderFactory.createTitledBorder("Instrucción del servicio"));
		panelInstruccionServicio.setBounds(490, 220, 295, 80);
		panelInstruccionServicio.setLayout(null);
		ordenDePedido.add(panelInstruccionServicio);
		JPanel panelInformacionResultados = new JPanel();
		panelInformacionResultados.setBorder(BorderFactory.createTitledBorder("Información de los resultados"));
		panelInformacionResultados.setBounds(490, 315, 295, 65);
		panelInformacionResultados.setLayout(null);
		ordenDePedido.add(panelInformacionResultados);
		JPanel panelSeguridad = new JPanel();
		panelSeguridad.setBorder(BorderFactory.createTitledBorder("Seguridad/EPI's"));
		panelSeguridad.setBounds(335, 220, 155, 160);
		panelSeguridad.setLayout(null);
		ordenDePedido.add(panelSeguridad);
		JLabel nombrePiezaLabel = new JLabel("Nombre de la pieza:");
		nombrePiezaLabel.setBounds(10, 25, 95, 20);
		panelGenerales.add(nombrePiezaLabel);
		nombrePieza = new JTextField();
		nombrePieza.setBounds(115, 25, 150, 20);
		panelGenerales.add(nombrePieza);
		JLabel referenciasLabel = new JLabel("Referencias:");
		referenciasLabel.setBounds(10, 60, 95, 20);
		panelGenerales.add(referenciasLabel);
		referencias = new JTextField();
		referencias.setBounds(115, 60, 150, 20);
		panelGenerales.add(referencias);
		JLabel responsableLabel = new JLabel("Responsable CPA:");
		responsableLabel.setBounds(10, 95, 95, 20);
		panelGenerales.add(responsableLabel);
		responsable = new JTextField();
		responsable.setBounds(115, 95, 150, 20);
		panelGenerales.add(responsable);
		JLabel numChasis1Label = new JLabel("Num. Chasis 1:");
		numChasis1Label.setBounds(285, 20, 80, 20);
		panelGenerales.add(numChasis1Label);
		JLabel numChasis2Label = new JLabel("Num. Chasis 2:");
		numChasis2Label.setBounds(285, 50, 80, 20);
		panelGenerales.add(numChasis2Label);
		JLabel numChasis3Label = new JLabel("Num. Chasis 3:");
		numChasis3Label.setBounds(285, 80, 80, 20);
		panelGenerales.add(numChasis3Label);
		JLabel numChasis4Label = new JLabel("Num. Chasis 4:");
		numChasis4Label.setBounds(285, 110, 80, 20);
		panelGenerales.add(numChasis4Label);
		numChasis1 = new JTextField();
		numChasis1.setBounds(365, 20, 60, 20);
		panelGenerales.add(numChasis1);
		numChasis2 = new JTextField();
		numChasis2.setBounds(365, 50, 60, 20);
		panelGenerales.add(numChasis2);
		numChasis3 = new JTextField();
		numChasis3.setBounds(365, 80, 60, 20);
		panelGenerales.add(numChasis3);
		numChasis4 = new JTextField();
		numChasis4.setBounds(365, 110, 60, 20);
		panelGenerales.add(numChasis4);
		piezasVerde = new JCheckBox();
		piezasVerde.setBounds(110, 50, 30, 20);
		panelControlesCalidad.add(piezasVerde);
		piezasBlanco = new JCheckBox();
		piezasBlanco.setBounds(110, 75, 30, 20);
		panelControlesCalidad.add(piezasBlanco);
		piezasOtros = new JCheckBox();
		piezasOtros.setBounds(110, 100, 30, 20);
		panelControlesCalidad.add(piezasOtros);
		piezasRojo = new JCheckBox();
		piezasRojo.setBounds(110, 125, 30, 20);
		panelControlesCalidad.add(piezasRojo);
		contenedorVerde = new JCheckBox();
		contenedorVerde.setBounds(170, 50, 30, 20);
		panelControlesCalidad.add(contenedorVerde);
		contenedorRojo = new JCheckBox();
		contenedorRojo.setBounds(170, 125, 30, 20);
		panelControlesCalidad.add(contenedorRojo);
		JLabel piezasLabel = new JLabel("Piezas");
		piezasLabel.setBounds(110, 25, 60, 20);
		panelControlesCalidad.add(piezasLabel);
		JLabel contenedorLabel = new JLabel("Contenedor");
		contenedorLabel.setBounds(160, 25, 60, 20);
		panelControlesCalidad.add(contenedorLabel);
		JLabel contenedorVerdeLabel = new JLabel("Verde OK:");
		contenedorVerdeLabel.setBounds(30, 50, 60, 20);
		panelControlesCalidad.add(contenedorVerdeLabel);
		JLabel contenedorBlancoLabel = new JLabel("Blanco OK:");
		contenedorBlancoLabel.setBounds(30, 75, 60, 20);
		panelControlesCalidad.add(contenedorBlancoLabel);
		JLabel contenedorOtrosLabel = new JLabel("Otros OK:");
		contenedorOtrosLabel.setBounds(30, 100, 60, 20);
		panelControlesCalidad.add(contenedorOtrosLabel);
		JLabel contenedorRojoLabel = new JLabel("Rojo NOK:");
		contenedorRojoLabel.setBounds(30, 125, 60, 20);
		panelControlesCalidad.add(contenedorRojoLabel);
		JLabel recomPersonaContactoLabel = new JLabel("Persona de contacto:");
		recomPersonaContactoLabel.setBounds(10, 25, 110, 20);
		panelRecomendador.add(recomPersonaContactoLabel);
		recomPersonaContacto = new JTextField();
		recomPersonaContacto.setBounds(120, 25, 170, 20);
		panelRecomendador.add(recomPersonaContacto);
		JLabel recomDepartamentoLabel = new JLabel("Departamento:");
		recomDepartamentoLabel.setBounds(10, 50, 95, 20);
		panelRecomendador.add(recomDepartamentoLabel);
		recomDepartamento = new JTextField();
		recomDepartamento.setBounds(120, 50, 170, 20);
		panelRecomendador.add(recomDepartamento);
		JLabel recomTelefonoLabel = new JLabel("Teléfono:");
		recomTelefonoLabel.setBounds(10, 75, 95, 20);
		panelRecomendador.add(recomTelefonoLabel);
		recomTelefono = new JTextField();
		recomTelefono.setBounds(120, 75, 170, 20);
		panelRecomendador.add(recomTelefono);
		JLabel recomEmailLabel = new JLabel("E-Mail:");
		recomEmailLabel.setBounds(10, 100, 95, 20);
		panelRecomendador.add(recomEmailLabel);
		recomEmail = new JTextField();
		recomEmail.setBounds(120, 100, 170, 20);
		panelRecomendador.add(recomEmail);
		JLabel recomFechaSolicitudLabel = new JLabel("Fecha de solicitud:");
		recomFechaSolicitudLabel.setBounds(10, 125, 95, 20);
		panelRecomendador.add(recomFechaSolicitudLabel);
		recomFechaSolicitud = new JDateChooser();
		recomFechaSolicitud.setBounds(120, 125, 170, 20);
		panelRecomendador.add(recomFechaSolicitud);
		JLabel descripcionServicioLabel = new JLabel("Descripción:");
		descripcionServicioLabel.setBounds(10, 35, 95, 20);
		panelInstruccionServicio.add(descripcionServicioLabel);
		descripcionServicio = new JTextField();
		descripcionServicio.setBounds(110, 35, 170, 20);
		panelInstruccionServicio.add(descripcionServicio);
		calzado = new JCheckBox("Calzado de seguridad");
		calzado.setBounds(10, 25, 135, 20);
		panelSeguridad.add(calzado);
		gafas = new JCheckBox("Gafas de seguridad");
		gafas.setBounds(10, 50, 125, 20);
		panelSeguridad.add(gafas);
		chaleco = new JCheckBox("Chaleco reflectante");
		chaleco.setBounds(10, 75, 135, 20);
		panelSeguridad.add(chaleco);
		tapones = new JCheckBox("Tapones");
		tapones.setBounds(10, 100, 80, 20);
		panelSeguridad.add(tapones);
		guantes = new JCheckBox("Guantes");
		guantes.setBounds(10, 125, 70, 20);
		panelSeguridad.add(guantes);
		diaria = new JRadioButton("Diaria");
		diaria.setBounds(20, 25, 55, 20);
		panelInformacionResultados.add(diaria);
		diaria.setSelected(true);
		semanal = new JRadioButton("Semanal");
		semanal.setBounds(75, 25, 70, 20);
		panelInformacionResultados.add(semanal);
		mensual = new JRadioButton("Mensual");
		mensual.setBounds(145, 25, 65, 20);
		panelInformacionResultados.add(mensual);
		otros = new JRadioButton("Otros");
		otros.setBounds(210, 25, 55, 20);
		panelInformacionResultados.add(otros);
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(diaria);
		grupo.add(semanal);
		grupo.add(mensual);
		grupo.add(otros);
		JLabel labelOrdenPedido = new JLabel("<html><h2>ORDEN DE PEDIDO</h2></html>");
		labelOrdenPedido.setBounds(310, 15, 300, 25);
		ordenDePedido.add(labelOrdenPedido);
		JLabel labelVersionOrdenPedido = new JLabel("<html><h5>PC-01-FOR-04 REV.01</h5></html>");
		labelVersionOrdenPedido.setBounds(660, 5, 140, 25);
		ordenDePedido.add(labelVersionOrdenPedido);



		//TODO PESTAÑA INSTRUCCION DE TRABAJO
		instruccionDeTrabajo = new JPanel();
		panelDePestanas.addTab("Instrucción de trabajo", null, instruccionDeTrabajo, null);
		instruccionDeTrabajo.setLayout(null);
		JPanel panelOperarios = new JPanel();
		panelOperarios.setBorder(BorderFactory.createTitledBorder("Operarios"));
		panelOperarios.setBounds(10, 50, 200, 110);
		panelOperarios.setLayout(null);
		instruccionDeTrabajo.add(panelOperarios);
		JPanel panelControl = new JPanel();
		panelControl.setBorder(BorderFactory.createTitledBorder("Control"));
		panelControl.setBounds(10, 180, 200, 200);
		panelControl.setLayout(null);
		instruccionDeTrabajo.add(panelControl);

		String[] columnNames = { "Descripción", "Otros", "Aplica"};

		Object[][] data = new Object[12][3];
		arrayImagenes = new String[12];

		String strTmp = "";
		for (int i=0; i<12; i++) {
			strTmp += "@;@@;@#;#@;@0";
		}
		strTmp = strTmp.substring(3);

		String[] arr = strTmp.split("@;@");
		int row1 = 0;
		int col1 = 0;
		for(int i=0; i<arr.length; i++) {
			if (col1 == 0) {
				data[row1][col1] = arr[i];
			} else if(col1 == 1) {
				data[row1][col1] = "Imágenes";
				arrayImagenes[row1] = arr[i];
			} else {
				if (arr[i].equalsIgnoreCase("1")) {
					data[row1][col1] = true;
				} else {
					data[row1][col1] = false;
				}
			}
			col1++;
			if (col1 > 2) {
				col1 = 0;
				row1++;
			}
		}


		TableModel model = new DefaultTableModel(data, columnNames);
		trabajos = new JTable(model) {

			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		trabajos.getColumnModel().getColumn(1).setCellRenderer(new ClientsTableButtonRenderer());
		trabajos.getColumnModel().getColumn(1).setCellEditor(new ClientsTableRenderer(new JCheckBox(), arrayImagenes, this, 0));
		trabajos.getTableHeader().setReorderingAllowed(false);
		trabajos.setRowSelectionAllowed(false);
		JScrollPane scrollTrabajos = new JScrollPane();
		scrollTrabajos.setViewportView(trabajos);
		scrollTrabajos.setBounds(220, 130, 550, 220);
		instruccionDeTrabajo.add(scrollTrabajos);
		JLabel labelInstruccion = new JLabel("<html><h2>INSTRUCCIÓN DE TRABAJO</h2></html>");
		labelInstruccion.setBounds(310, 32, 300, 25);
		instruccionDeTrabajo.add(labelInstruccion);
		JLabel labelInstruccionTabla = new JLabel("<html><h3>Acciones</h3></html>");
		labelInstruccionTabla.setBounds(470, 85, 200, 25);
		instruccionDeTrabajo.add(labelInstruccionTabla);
		JLabel labelVersionInstruccion = new JLabel("<html><h5>PC-01-FOR-05 REV.02</h5></html>");
		labelVersionInstruccion.setBounds(660, 5, 140, 25);
		instruccionDeTrabajo.add(labelVersionInstruccion);
		operarioA1 = new JCheckBox("A1");
		operarioA1.setBounds(10, 20, 40, 20);
		panelOperarios.add(operarioA1);
		operarioA2 = new JCheckBox("A2");
		operarioA2.setBounds(60, 20, 40, 20);
		panelOperarios.add(operarioA2);
		operarioA3 = new JCheckBox("A3");
		operarioA3.setBounds(110, 20, 40, 20);
		panelOperarios.add(operarioA3);
		operarioA4 = new JCheckBox("A4");
		operarioA4.setBounds(10, 50, 40, 20);
		panelOperarios.add(operarioA4);
		operarioA5 = new JCheckBox("A5");
		operarioA5.setBounds(60, 50, 40, 20);
		panelOperarios.add(operarioA5);
		operarioA6 = new JCheckBox("A6");
		operarioA6.setBounds(110, 50, 40, 20);
		panelOperarios.add(operarioA6);
		operarioA7 = new JCheckBox("A7");
		operarioA7.setBounds(10, 80, 40, 20);
		panelOperarios.add(operarioA7);
		JLabel aplicaLabel = new JLabel("Aplica");
		aplicaLabel.setBounds(145, 15, 50, 20);
		panelControl.add(aplicaLabel);
		JLabel materialLabel = new JLabel("Petición del material");
		materialLabel.setBounds(10, 45, 100, 20);
		panelControl.add(materialLabel);
		JLabel refCorrectasLabel = new JLabel("Referencias correctas");
		refCorrectasLabel.setBounds(10, 70, 120, 20);
		panelControl.add(refCorrectasLabel);
		JLabel seleccionLabel = new JLabel("Selección de piezas");
		seleccionLabel.setBounds(10, 95, 110, 20);
		panelControl.add(seleccionLabel);
		JLabel retrabajoLabel = new JLabel("Retrabajo en piezas");
		retrabajoLabel.setBounds(10, 120, 100, 20);
		panelControl.add(retrabajoLabel);
		JLabel trasvaseLabel = new JLabel("Trasvase");
		trasvaseLabel.setBounds(10, 145, 100, 20);
		panelControl.add(trasvaseLabel);
		JLabel otrosLabel = new JLabel("Otros");
		otrosLabel.setBounds(10, 170, 100, 20);
		panelControl.add(otrosLabel);
		peticionMaterial = new JCheckBox();
		peticionMaterial.setBounds(150, 45, 30, 20);
		panelControl.add(peticionMaterial);
		referenciasPiezas = new JCheckBox();
		referenciasPiezas.setBounds(150, 70, 30, 20);
		panelControl.add(referenciasPiezas);
		seleccion = new JCheckBox();
		seleccion.setBounds(150, 95, 30, 20);
		panelControl.add(seleccion);
		retrabajo = new JCheckBox();
		retrabajo.setBounds(150, 120, 30, 20);
		panelControl.add(retrabajo);
		trasvase = new JCheckBox();
		trasvase.setBounds(150, 145, 30, 20);
		panelControl.add(trasvase);
		otrosPiezas = new JCheckBox();
		otrosPiezas.setBounds(150, 170, 30, 20);
		panelControl.add(otrosPiezas);



		//TODO PESTAÑA INFORMACION
		informacion = new JPanel();
		panelDePestanas.addTab("Información", null, informacion, null);
		informacion.setLayout(null);
		JTable tablaHeaders = new JTable();
		DefaultTableModel modelo1 = (DefaultTableModel) tablaHeaders2.getModel();
		modelo1.addColumn("Tipo de defectos");
		String[] filaVacia = new String[1];
		filaVacia[0] = "";
		for (int i=0; i<6; i++) {
			modelo1.addRow(filaVacia);
		}
		tablaHeaders2.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaHeaders2.getColumnModel().getColumn(0).setResizable(false);
		Calendar c = Calendar.getInstance();
		defectos = new JTable() {

			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				default:
					return Integer.class;
				}
			}
		};
		defectos.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		DefaultTableModel modeloDefectos = (DefaultTableModel) defectos.getModel();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MMM");
		for (int i=0; i<30; i++) {
			modeloDefectos.addColumn(formateador.format(c.getTime()));
			c.add(Calendar.DATE, 1);
		}
		filaVacia = new String[30];
		for (int i=0; i<30; i++) {
			defectos.getColumnModel().getColumn(i).setPreferredWidth(40);
			defectos.getColumnModel().getColumn(i).setResizable(false);
			filaVacia[i] = "";
		}
		for (int i=0; i<6; i++) {
			modeloDefectos.addRow(filaVacia);
		}

		JScrollPane scrollDefectos = new JScrollPane(defectos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		defectos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		defectos.getTableHeader().setReorderingAllowed(false);
		defectos.setRowSelectionAllowed(false);
		defectos.setCellSelectionEnabled(true);
		new ExcelAdapter(defectos);
		tablaHeaders2.getTableHeader().setReorderingAllowed(false);
		tablaHeaders2.setCellSelectionEnabled(true);
		tablaHeaders2.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		new ExcelAdapter(tablaHeaders2);
		JScrollPane scroll1 = new JScrollPane(tablaHeaders2);
		scroll1.setBounds(10, 90, 120, 125);
		informacion.add(scroll1);
		scrollDefectos.setBounds(128, 90, 660, 145);
		informacion.add(scrollDefectos);
		modelo1 = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaHeaders = new JTable(modelo1);
		modelo1.addColumn("Piezas");
		filaVacia = new String[1];
		String[] tiposPieza = new String[] {"Piezas OK","Piezas recuperadas"};
		for (int i=0; i<tiposPieza.length; i++) {
			modelo1.addRow(filaVacia);
			modelo1.setValueAt(tiposPieza[i], i, 0);
		}
		tablaHeaders.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaHeaders.getColumnModel().getColumn(0).setResizable(false);
		piezas = new JTable() {

			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				default:
					return Integer.class;
				}
			}
		};
		piezas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		DefaultTableModel modeloPiezas = (DefaultTableModel) piezas.getModel();
		c = Calendar.getInstance();
		for (int i=0; i<30; i++) {
			modeloPiezas.addColumn(formateador.format(c.getTime()));
			c.add(Calendar.DATE, 1);
		}
		filaVacia = new String[30];
		for (int i=0; i<30; i++) {
			piezas.getColumnModel().getColumn(i).setPreferredWidth(40);
			piezas.getColumnModel().getColumn(i).setResizable(false);
			filaVacia[i] = "";
		}
		for (int i=0; i<2; i++) {
			modeloPiezas.addRow(filaVacia);
		}

		JScrollPane scrollPiezas = new JScrollPane(piezas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		piezas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		piezas.getTableHeader().setReorderingAllowed(false);
		piezas.setRowSelectionAllowed(false);
		piezas.setCellSelectionEnabled(true);
		new ExcelAdapter(piezas);
		tablaHeaders.getTableHeader().setReorderingAllowed(false);
		JScrollPane scroll2 = new JScrollPane(tablaHeaders);
		scroll2.setBounds(10, 290, 120, 60);
		informacion.add(scroll2);
		scrollPiezas.setBounds(128, 290, 660, 80);
		informacion.add(scrollPiezas);
		JLabel labelInformacion = new JLabel("<html><h2>INFORMACIÓN DE RESULTADOS</h2></html>");
		labelInformacion.setBounds(300, 40, 300, 25);
		informacion.add(labelInformacion);
		JLabel labelVersionInformacion = new JLabel("<html><h5>PC-01-FOR-06 REV.01</h5></html>");
		labelVersionInformacion.setBounds(660, 5, 140, 25);
		informacion.add(labelVersionInformacion);


		//TODO PESTAÑA RECUENTO FINAL
		recuentoFinal = new JPanel();
		panelDePestanas.addTab("Recuento final", null, recuentoFinal, null);
		recuentoFinal.setLayout(null);
		recuento = new JTable() {

			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return Integer.class;
				case 5:
					return Integer.class;
				case 6:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		recuento.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		DefaultTableModel modeloRecuento = (DefaultTableModel)recuento.getModel();
		modeloRecuento.addColumn("Fecha");
		modeloRecuento.addColumn("Nº Lote");
		modeloRecuento.addColumn("Referencias");
		modeloRecuento.addColumn("Color");
		modeloRecuento.addColumn("OK");
		modeloRecuento.addColumn("NOK");
		modeloRecuento.addColumn("Retrabajado");
		Object [] filaRecuento = new Object[7];
		filaRecuento[0] = "";
		filaRecuento[1] = "";
		filaRecuento[2] = "";
		filaRecuento[3] = "";
		filaRecuento[4] = "";
		filaRecuento[5] = "";
		filaRecuento[6] = "";
		for (int i=0; i<27; i++)
			modeloRecuento.addRow(filaRecuento);
		recuento.getTableHeader().setReorderingAllowed(false);
		recuento.setRowSelectionAllowed(false);
		recuento.setCellSelectionEnabled(true);
		new ExcelAdapter(recuento);
		JScrollPane scrollRecuento = new JScrollPane();
		scrollRecuento.setViewportView(recuento);
		scrollRecuento.setBounds(150, 100, 550, 250);
		recuentoFinal.add(scrollRecuento);
		JLabel labelRecuentoFinal = new JLabel("<html><h2>RECUENTO FINAL</h2></html>");
		labelRecuentoFinal.setBounds(350, 40, 300, 25);
		recuentoFinal.add(labelRecuentoFinal);
		JLabel labelVersionRecuentoFinal = new JLabel("<html><h5>PC-01-FOR-08 REV.01</h5></html>");
		labelVersionRecuentoFinal.setBounds(660, 5, 140, 25);
		recuentoFinal.add(labelVersionRecuentoFinal);


		//TODO PESTAÑA ESTIMACION DE HORAS Y COSTES
		estimacionHorasCostes = new JPanel();
		panelDePestanas.addTab("Estimación de horas y costes", null, estimacionHorasCostes, null);
		estimacionHorasCostes.setLayout(null);
		modelo1 = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaHeaders = new JTable(modelo1);
		modelo1.addColumn("Tipo Hora");
		filaVacia = new String[1];
		String[] tiposHora = new String[] {"Hora Normal","Hora extra","Hora sábados","Hora festivos","Hora Nocturna",
				"H. Especialista Normal", "H. Especialista Extra", "H. Especialista Sabado", "H. Especialista Festiva",
				"H. Especial. Nocturna","Hora de Coordinación", "H. de Administración","Gastos logisticos","Otros 1","Otros 2"};
		for (int i=0; i<tiposHora.length; i++) {
			modelo1.addRow(filaVacia);
			modelo1.setValueAt(tiposHora[i], i, 0);
		}
		tablaHeaders.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaHeaders.getColumnModel().getColumn(0).setResizable(false);

		horas = new JTable() {

			private static final long serialVersionUID = 1L;
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				default:
					return Integer.class;
				}
			}
		};
		horas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		DefaultTableModel modeloHoras = (DefaultTableModel) horas.getModel();
		formateador = new SimpleDateFormat("dd-MMM");
		c = Calendar.getInstance();
		for (int i=0; i<30; i++) {
			modeloHoras.addColumn(formateador.format(c.getTime()));
			c.add(Calendar.DATE, 1);
		}
		filaVacia = new String[30];

		for (int i=0; i<30; i++) {
			horas.getColumnModel().getColumn(i).setPreferredWidth(40);
			horas.getColumnModel().getColumn(i).setResizable(false);
			filaVacia[i] = "";
		}
		for (int i=0; i<tiposHora.length; i++) {
			modeloHoras.addRow(filaVacia);
		}

		JScrollPane scrollHoras = new JScrollPane(horas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		horas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		horas.getTableHeader().setReorderingAllowed(false);
		horas.setRowSelectionAllowed(false);
		horas.setCellSelectionEnabled(true);
		new ExcelAdapter(horas);
		tablaHeaders.getTableHeader().setReorderingAllowed(false);
		scroll1 = new JScrollPane(tablaHeaders);
		scroll1.setBounds(10, 90, 120, 267);
		estimacionHorasCostes.add(scroll1);
		scrollHoras.setBounds(128, 90, 660, 290);
		estimacionHorasCostes.add(scrollHoras);
		JLabel labelEstimacionHoras = new JLabel("<html><h2>TIEMPO INVERTIDO Y COSTES</h2></html>");
		labelEstimacionHoras.setBounds(280, 40, 300, 25);
		estimacionHorasCostes.add(labelEstimacionHoras);
		JLabel labelVersionEstimacionHoras = new JLabel("<html><h5>PC-01-FOR-09 REV.01</h5></html>");
		labelVersionEstimacionHoras.setBounds(660, 5, 140, 25);
		estimacionHorasCostes.add(labelVersionEstimacionHoras);



		//TODO PESTAÑA GAMA RETRABAJOS
		if (anadirRetrabajos) {
			gamaRetrabajos = new JPanel();
			panelDePestanas.addTab("Gama de retrabajos", null, gamaRetrabajos, null);
			gamaRetrabajos.setLayout(null);

			arrayFechasRetrabajos = new ArrayList<>();
			arrayNombresRetrabajos = new ArrayList<>();

			JPanel panelFirmasRetrabajos = new JPanel();
			panelFirmasRetrabajos.setBorder(BorderFactory.createTitledBorder("Firmas"));
			panelFirmasRetrabajos.setBounds(450, 90, 250, 290);
			panelFirmasRetrabajos.setLayout(null);
			gamaRetrabajos.add(panelFirmasRetrabajos);

			for (int i=0; i<11; i++) {
				arrayNombresRetrabajos.add(new JTextField());
				arrayFechasRetrabajos.add(new JDateChooser());
				arrayNombresRetrabajos.get(i).setBounds(10, 50+20*i, 120, 20);
				arrayFechasRetrabajos.get(i).setBounds(130, 50+20*i, 100, 20);
				panelFirmasRetrabajos.add(arrayNombresRetrabajos.get(i));
				panelFirmasRetrabajos.add(arrayFechasRetrabajos.get(i));
			}

			JLabel label1 = new JLabel("Nombre persona");
			label1.setBounds(25, 20, 150, 20);
			panelFirmasRetrabajos.add(label1);
			JLabel label2 = new JLabel("Fecha");
			label2.setBounds(145, 20, 80, 20);
			panelFirmasRetrabajos.add(label2);

			JLabel labelGamaRetrabajos = new JLabel("<html><h2>GAMA RETRABAJOS<h2></html>");
			labelGamaRetrabajos.setBounds(420, 25, 300, 25);
			gamaRetrabajos.add(labelGamaRetrabajos);
			JLabel labelVersionGamaRetrabajos = new JLabel("<html><h5>PC-01-FOR-08 REV.01</h5></html>");
			labelVersionGamaRetrabajos.setBounds(660, 5, 140, 25);
			gamaRetrabajos.add(labelVersionGamaRetrabajos);
			confirmacionGamaRetrabajos = new JButton("Añadir imagen");
			confirmacionGamaRetrabajos.setBounds(650, 50, 100, 20);
			gamaRetrabajos.add(confirmacionGamaRetrabajos);

			confirmacionGamaRetrabajos.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});

			JPanel panelDatosRetrabajo = new JPanel();
			panelDatosRetrabajo.setBorder(BorderFactory.createTitledBorder("Datos generales"));
			panelDatosRetrabajo.setBounds(10, 40, 400, 340);
			panelDatosRetrabajo.setLayout(null);			
			gamaRetrabajos.add(panelDatosRetrabajo);
			JLabel realizadoPorLabel = new JLabel("Realizado por:");
			realizadoPorLabel.setBounds(10, 30, 100, 20);
			panelDatosRetrabajo.add(realizadoPorLabel);
			JLabel fechaLabel = new JLabel("Fecha:");
			fechaLabel.setBounds(10, 65, 100, 20);
			panelDatosRetrabajo.add(fechaLabel);
			JLabel fechaLiberacionLabel = new JLabel("Fecha de liberación:");
			fechaLiberacionLabel.setBounds(10, 100, 100, 20);
			panelDatosRetrabajo.add(fechaLiberacionLabel);
			JLabel numReclamacionLabel = new JLabel("Nº reclamación:");
			numReclamacionLabel.setBounds(10, 135, 100, 20);
			panelDatosRetrabajo.add(numReclamacionLabel);
			JLabel fechaReclamacionLabel = new JLabel("Fecha reclamación:");
			fechaReclamacionLabel.setBounds(10, 170, 100, 20);
			panelDatosRetrabajo.add(fechaReclamacionLabel);
			JLabel referenciaLabel = new JLabel("Referencia pieza:");
			referenciaLabel.setBounds(10, 205, 100, 20);
			panelDatosRetrabajo.add(referenciaLabel);
			JLabel fechaComienzoLabel = new JLabel("Fecha comienzo:");
			fechaComienzoLabel.setBounds(10, 240, 100, 20);
			panelDatosRetrabajo.add(fechaComienzoLabel);
			JLabel tiempoLabel = new JLabel("Tiempo:");
			tiempoLabel.setBounds(10, 275, 100, 20);
			panelDatosRetrabajo.add(tiempoLabel);
			JLabel clienteLabel = new JLabel("Cliente:");
			clienteLabel.setBounds(10, 310, 100, 20);
			panelDatosRetrabajo.add(clienteLabel);
			realizadoRetrabajos = new JTextField();
			realizadoRetrabajos.setBounds(130, 30, 230, 20);
			panelDatosRetrabajo.add(realizadoRetrabajos);
			fechaRetrabajos = new JDateChooser();
			fechaRetrabajos.setBounds(130, 65, 230, 20);
			panelDatosRetrabajo.add(fechaRetrabajos);
			fechaLiberacion = new JDateChooser();
			fechaLiberacion.setBounds(130, 100, 230, 20);
			panelDatosRetrabajo.add(fechaLiberacion);
			numReclamacion = new JTextField();
			numReclamacion.setBounds(130, 135, 230, 20);
			panelDatosRetrabajo.add(numReclamacion);
			fechaReclamacion = new JDateChooser();
			fechaReclamacion.setBounds(130, 170, 230, 20);
			panelDatosRetrabajo.add(fechaReclamacion);
			referenciaRetrabajos = new JTextField();
			referenciaRetrabajos.setBounds(130, 205, 230, 20);
			panelDatosRetrabajo.add(referenciaRetrabajos);
			fechaComienzo = new JDateChooser();
			fechaComienzo.setBounds(130, 240, 230, 20);
			panelDatosRetrabajo.add(fechaComienzo);
			tiempo = new JTextField();
			tiempo.setBounds(130, 275, 230, 20);
			panelDatosRetrabajo.add(tiempo);
			clienteRetrabajos = new JTextField();
			clienteRetrabajos.setBounds(130, 310, 230, 20);
			panelDatosRetrabajo.add(clienteRetrabajos);
		}



		//TODO PESTAÑA FORMACION DE PERSONAL
		if (anadirFormacion) {
			formacionPersonal = new JPanel();
			panelDePestanas.addTab("Formación de personal", null, formacionPersonal, null);
			formacionPersonal.setLayout(null);

			arrayFechasPersonal = new ArrayList<>();
			arrayNombresPersonal = new ArrayList<>();

			JPanel panelFirmasPersonal = new JPanel();
			panelFirmasPersonal.setBorder(BorderFactory.createTitledBorder("Firmas"));
			panelFirmasPersonal.setBounds(450, 90, 250, 290);
			panelFirmasPersonal.setLayout(null);
			formacionPersonal.add(panelFirmasPersonal);

			for (int i=0; i<11; i++) {
				arrayNombresPersonal.add(new JTextField());
				arrayFechasPersonal.add(new JDateChooser());
				arrayNombresPersonal.get(i).setBounds(10, 50+20*i, 120, 20);
				arrayFechasPersonal.get(i).setBounds(130, 50+20*i, 100, 20);
				panelFirmasPersonal.add(arrayNombresPersonal.get(i));
				panelFirmasPersonal.add(arrayFechasPersonal.get(i));
			}

			JLabel label3 = new JLabel("Nombre persona");
			label3.setBounds(25, 20, 150, 20);
			panelFirmasPersonal.add(label3);
			JLabel label4 = new JLabel("Fecha");
			label4.setBounds(145, 20, 80, 20);
			panelFirmasPersonal.add(label4);		

			JLabel labelPersonalFormado = new JLabel("<html><h2>REGISTRO PERSONAL FORMADO<h2></html>");
			labelPersonalFormado.setBounds(280, 50, 300, 25);
			formacionPersonal.add(labelPersonalFormado);
			JLabel labelVersionPersonalFormado = new JLabel("<html><h5>PC-01-FOR-08 REV.01</h5></html>");
			labelVersionPersonalFormado.setBounds(660, 5, 140, 25);
			formacionPersonal.add(labelVersionPersonalFormado);
			confirmacionFormacionPersonal = new JButton("Añadir imagen");
			confirmacionFormacionPersonal.setBounds(650, 50, 100, 20);
			formacionPersonal.add(confirmacionFormacionPersonal);

			confirmacionFormacionPersonal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});

			JPanel panelDatosPersonalFormado = new JPanel();
			panelDatosPersonalFormado.setBorder(BorderFactory.createTitledBorder("Datos generales"));
			panelDatosPersonalFormado.setBounds(10, 110, 400, 270);
			panelDatosPersonalFormado.setLayout(null);
			formacionPersonal.add(panelDatosPersonalFormado);
			JLabel realizadoPorLabel2 = new JLabel("Realizado por:");
			realizadoPorLabel2.setBounds(10, 50, 100, 20);
			panelDatosPersonalFormado.add(realizadoPorLabel2);
			JLabel fechaLabel2 = new JLabel("Fecha:");
			fechaLabel2.setBounds(10, 90, 100, 20);
			panelDatosPersonalFormado.add(fechaLabel2);
			JLabel clienteLabel2 = new JLabel("Cliente:");
			clienteLabel2.setBounds(10, 130, 100, 20);
			panelDatosPersonalFormado.add(clienteLabel2);
			JLabel piezaLabel2 = new JLabel("Pieza:");
			piezaLabel2.setBounds(10, 170, 100, 20);
			panelDatosPersonalFormado.add(piezaLabel2);
			JLabel referenciaLabel2 = new JLabel("Referencia pieza:");
			referenciaLabel2.setBounds(10, 210, 100, 20);
			panelDatosPersonalFormado.add(referenciaLabel2);
			realizadoFormado = new JTextField();
			realizadoFormado.setBounds(130, 50, 230, 20);
			panelDatosPersonalFormado.add(realizadoFormado);
			fechaFormado = new JDateChooser();
			fechaFormado.setBounds(130, 90, 230, 20);
			panelDatosPersonalFormado.add(fechaFormado);
			clienteFormado = new JTextField();
			clienteFormado.setBounds(130, 130, 230, 20);
			panelDatosPersonalFormado.add(clienteFormado);
			piezaFormado = new JTextField();
			piezaFormado.setBounds(130, 170, 230, 20);
			panelDatosPersonalFormado.add(piezaFormado);
			referenciaFormado = new JTextField();
			referenciaFormado.setBounds(130, 210, 230, 20);
			panelDatosPersonalFormado.add(referenciaFormado);
		}



		//TODO EL RESTO DE LA VENTANA
		tituloLabel = new JLabel("<html><h1>ORDEN DE PEDIDO</h1></html>");
		tituloLabel.setBounds(285, 15, 250, 25);
		panel.add(tituloLabel);

		clienteLabel = new JLabel("<html><h5>Cliente:</h5></html>");
		clienteLabel.setBounds(70, 50, 100, 25);
		panel.add(clienteLabel);
		cambiarCliente = new JButton("Cambiar");
		cambiarCliente.setBounds(120, 50, 75, 20);
		panel.add(cambiarCliente);
		clienteLabel2 = new JLabel("<html><h4>"+cliente+"</h4></html>");
		clienteLabel2.setBounds(90, 70, 300, 25);
		panel.add(clienteLabel2);

		fechaLabel = new JLabel("<html><h5>Fecha:</h5></html>");
		fechaLabel.setBounds(840, 245, 100, 25);
		panel.add(fechaLabel);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		fechaLabel2 = new JLabel("<html><h4>"+sdf.format(cal.getTime())+"</h4></html>");
		fechaLabel2.setBounds(860, 265, 120, 25);
		panel.add(fechaLabel2);

		personalCPALabel = new JLabel("<html><h5>Persona CPA:</h5></html>");
		personalCPALabel.setBounds(530, 50, 100, 25);
		panel.add(personalCPALabel);
		personalCPALabel2 = new JLabel("<html><h4>"+persona+"</h4></html>");
		personalCPALabel2.setBounds(550, 70, 120, 25);
		panel.add(personalCPALabel2);

		numAccionLabel = new JLabel("<html><h5>Nº Acción:</h5></html>");
		numAccionLabel.setBounds(840, 175, 100, 25);
		panel.add(numAccionLabel);
		numAccionLabel2 = new JLabel("<html><h4>"+numAccion+"</h4></html>");
		numAccionLabel2.setBounds(860, 195, 120, 25);
		panel.add(numAccionLabel2);

		JLabel jlbPicture = new JLabel(new ImageIcon("img/cpa.jpg"));
		jlbPicture.setPreferredSize(new Dimension(240, 110));
		jlbPicture.setBounds(760, 5, 240, 110);
		panel.add(jlbPicture);

		cambiarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ArrayList<Cliente> arrCli = llamadas.recuperarClientes(); 
				Cliente[] clientes = arrCli.toArray(new Cliente[arrCli.size()]);
				Cliente clienteNuevo = (Cliente) JOptionPane.showInputDialog(ventanaPrincipal, "Nuevo cliente", "Selecciona el cliente", 
						JOptionPane.QUESTION_MESSAGE, null, clientes, clientes[0]);
				
				Calendar fechaNomCarpeta;
				SimpleDateFormat sdfNomCarpeta;
				
				if (clienteNuevo != null) {
					if(!cliente.getTipo().equalsIgnoreCase(clienteNuevo.getTipo())) {
						cliente = clienteNuevo;
						clienteLabel2.setText("<html><h4>"+cliente+"</h4></html>");
						numAccion = llamadas.generarNumAccion(cliente.getTipo());
						numAccionLabel2.setText("<html><h4>"+numAccion+"</h4></html>");
						
						fechaNomCarpeta = Calendar.getInstance();
						sdfNomCarpeta = new SimpleDateFormat("HHmmss");
						nombreCarpeta = numAccion + sdfNomCarpeta.format(fechaNomCarpeta.getTime());
						
					} else {
						cliente = clienteNuevo;
						clienteLabel2.setText("<html><h4>"+cliente+"</h4></html>");
						
						fechaNomCarpeta = Calendar.getInstance();
						sdfNomCarpeta = new SimpleDateFormat("HHmmss");
						nombreCarpeta = numAccion + sdfNomCarpeta.format(fechaNomCarpeta.getTime());
						
					}

				}

			}
		});

		JButton guardar = new JButton("Guardar y salir");
		guardar.setBounds(840, 440, 140, 20);
		panel.add(guardar);
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Deseas guardar el servicio y cerrar la ventana?", "Guardar y salir", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					guardarYSalir();
				}
			}
		});

		JButton cancelar = new JButton("Salir");
		cancelar.setBounds(840, 480, 140, 20);
		panel.add(cancelar);
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(
						null,"¿Deseas guardar los cambios antes de salir?", "Salir",JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Guardar", "No guardar", "Cancelar" }, "Guardar");
				if (seleccion == 0) {
					guardarYSalir();
				}
				else if (seleccion == 1) {
					dispose();
				}
			}
		});

		JButton guardarYGenerar = new JButton("Generar factura");
		guardarYGenerar.setBounds(840, 400, 140, 20);
		panel.add(guardarYGenerar);
		guardarYGenerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(arrayImagenes[0]);
			}
		});

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				int seleccion = JOptionPane.showOptionDialog(
						null,"¿Deseas guardar los cambios antes de salir?", "Salir",JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Guardar", "No guardar", "Cancelar" }, "Guardar");
				if (seleccion == 0) {
					guardarYSalir();
				}
				else if (seleccion == 1) {
					dispose();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		this.getContentPane().add(panel);
		this.setSize(1000, 560);
		this.setTitle("ERP CPA - Nuevo servicio");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void guardarYSalir() {
		Calendar cal = Calendar.getInstance();
		int res = 0;
		if (semanal.isSelected())
			res = 1;
		else if (mensual.isSelected())
			res = 2;
		else if (otros.isSelected())
			res = 3;

		String piezasOK = "";
		String piezasRecuperadas = "";
		String arrayHoraNormal = "";
		String arrayHoraExtra= "";
		String arrayHoraSabado = "";
		String arrayHoraFestivo = "";
		String arrayHoraNocturna = "";
		String arrayHoraEspecialistaNormal = "";
		String arrayHoraEspecialistaExtra = "";
		String arrayHoraEspecialistaSabado = "";
		String arrayHoraEspecialistaFestiva = "";
		String arrayHoraEspecialistaNocturna = "";
		String arrayHoraCoordinacion = "";
		String arrayHoraAdministracion = "";
		String arrayGastosLogisticos = "";
		String arrayOtros1 = "";
		String arrayOtros2 = "";

		for(int i = 0; i < 30; i++) {
			piezasOK += "@;@" + piezas.getValueAt(0, i);
			piezasRecuperadas += "@;@" + piezas.getValueAt(1, i);

			arrayHoraNormal += "@;@" + horas.getValueAt(0, i);
			arrayHoraExtra += "@;@" + horas.getValueAt(1, i);
			arrayHoraSabado += "@;@" + horas.getValueAt(2, i);
			arrayHoraFestivo += "@;@" + horas.getValueAt(3, i);
			arrayHoraNocturna += "@;@" + horas.getValueAt(4, i);
			arrayHoraEspecialistaNormal += "@;@" + horas.getValueAt(5, i);
			arrayHoraEspecialistaExtra += "@;@" + horas.getValueAt(6, i);
			arrayHoraEspecialistaSabado += "@;@" + horas.getValueAt(7, i);
			arrayHoraEspecialistaFestiva += "@;@" + horas.getValueAt(8, i);
			arrayHoraEspecialistaNocturna += "@;@" + horas.getValueAt(9, i);
			arrayHoraCoordinacion += "@;@" + horas.getValueAt(10, i);
			arrayHoraAdministracion += "@;@" + horas.getValueAt(11, i);
			arrayGastosLogisticos += "@;@" + horas.getValueAt(12, i);
			arrayOtros1 += "@;@" + horas.getValueAt(13, i);
			arrayOtros2 += "@;@" + horas.getValueAt(14, i);
		}

		arrayHoraNormal = arrayHoraNormal.substring(3);
		arrayHoraExtra = arrayHoraExtra.substring(3);
		arrayHoraSabado = arrayHoraSabado.substring(3);				
		arrayHoraFestivo = arrayHoraFestivo.substring(3);
		arrayHoraNocturna = arrayHoraNocturna.substring(3);
		arrayHoraEspecialistaNormal = arrayHoraEspecialistaNormal.substring(3);
		arrayHoraEspecialistaExtra = arrayHoraEspecialistaExtra.substring(3);
		arrayHoraEspecialistaSabado = arrayHoraEspecialistaSabado.substring(3);				
		arrayHoraEspecialistaFestiva = arrayHoraEspecialistaFestiva.substring(3);
		arrayHoraEspecialistaNocturna = arrayHoraEspecialistaNocturna.substring(3);
		arrayHoraCoordinacion = arrayHoraCoordinacion.substring(3);
		arrayHoraAdministracion = arrayHoraAdministracion.substring(3);
		arrayGastosLogisticos = arrayGastosLogisticos.substring(3);				
		arrayOtros1 = arrayOtros1.substring(3);
		arrayOtros2 = arrayOtros2.substring(3);
		piezasOK = piezasOK.substring(3);
		piezasRecuperadas = piezasRecuperadas.substring(3);			

		String recuentoFinal = "";

		for (int i = 0; i < recuento.getRowCount() ; i++) {
			for (int j = 0; j < recuento.getColumnCount(); j++) {
				recuentoFinal += "@;@" + recuento.getValueAt(i, j);
			}
		}

		recuentoFinal = recuentoFinal.substring(3);

		String tablaDefectos = "";

		for (int i = 0; i < tablaHeaders2.getRowCount() ; i++) {
			for (int j = 0; j < defectos.getColumnCount(); j++) {
				if (j == 0) {
					tablaDefectos += "@;@" + tablaHeaders2.getValueAt(i, j);
				}
				tablaDefectos += "@;@" + defectos.getValueAt(i, j);
			}
		}

		tablaDefectos = tablaDefectos.substring(3);

		String accionesIntruccion = "";

		for (int i = 0; i < trabajos.getRowCount() ; i++) {
			for (int j = 0; j < trabajos.getColumnCount(); j++) {

				if ( j == 0) {
					accionesIntruccion += "@;@" + trabajos.getValueAt(i, j);
				} else if (j == 1) {
					accionesIntruccion += "@;@" + arrayImagenes[i];
				} else {
					if ((boolean) trabajos.getValueAt(i, j))
						accionesIntruccion += "@;@1";
					else
						accionesIntruccion += "@;@0";

				}
			}
		}



		String firmasRetrabajos = "";
		String firmasPersonal = "";
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-YYYY");

		if (anadirRetrabajos) {

			for (int i = 0; i < arrayFechasRetrabajos.size(); i++) {
				firmasRetrabajos += "@;@" + arrayNombresRetrabajos.get(i).getText() + "@;@";
				if (arrayFechasRetrabajos.get(i).getDate() != null) {
					firmasRetrabajos += sdf2.format(arrayFechasRetrabajos.get(i).getDate());
				}
			}

			firmasRetrabajos = firmasRetrabajos.substring(3);
		}

		if (anadirFormacion) {

			for (int i = 0; i < arrayFechasPersonal.size(); i++) {
				firmasPersonal += "@;@" + arrayNombresPersonal.get(i).getText() + "@;@";
				if (arrayFechasPersonal.get(i).getDate() != null) {
					firmasPersonal += sdf2.format(arrayFechasPersonal.get(i).getDate());
				}
			}

			firmasPersonal = firmasPersonal.substring(3);
		}


		accionesIntruccion = accionesIntruccion.substring(3);

		Servicio servi = null;

		if (anadirFormacion && anadirRetrabajos) {
			servi = new Servicio(cliente.getNif(),cliente.getNombre(), persona, numAccion, cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
					responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
					recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
					guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
					peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
					recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
					arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, realizadoRetrabajos.getText(), fechaRetrabajos.getDate(), fechaLiberacion.getDate(), 
					numReclamacion.getText(), fechaReclamacion.getDate(), referenciaRetrabajos.getText(), fechaComienzo.getDate(), tiempo.getText(), clienteRetrabajos.getText(), firmasRetrabajos, "img/prueba.jpg", realizadoFormado.getText(), 
					fechaFormado.getDate(), clienteFormado.getText(), piezaFormado.getText(), referenciaFormado.getText(), firmasPersonal, "img/prueba.jpg", anadirRetrabajos, anadirFormacion, nombreCarpeta);
		} else if (anadirFormacion) {
			servi = new Servicio(cliente.getNif(),cliente.getNombre(), persona, numAccion, cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
					responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
					recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
					guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
					peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
					recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
					arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, null, null, null, 
					null, null, null, null, null, null, null, null, realizadoFormado.getText(), 
					fechaFormado.getDate(), clienteFormado.getText(), piezaFormado.getText(), referenciaFormado.getText(), firmasPersonal, "img/prueba.jpg", anadirRetrabajos, anadirFormacion, nombreCarpeta);
		} else if (anadirRetrabajos) {
			servi = new Servicio(cliente.getNif(),cliente.getNombre(), persona, numAccion, cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
					responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
					recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
					guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
					peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
					recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
					arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, realizadoRetrabajos.getText(), fechaRetrabajos.getDate(), fechaLiberacion.getDate(), 
					numReclamacion.getText(), fechaReclamacion.getDate(), referenciaRetrabajos.getText(), fechaComienzo.getDate(), tiempo.getText(), clienteRetrabajos.getText(), firmasRetrabajos, "img/prueba.jpg", null, 
					null, null, null, null, null, null, anadirRetrabajos, anadirFormacion, nombreCarpeta);
		} else {
			servi = new Servicio(cliente.getNif(),cliente.getNombre(), persona, numAccion, cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
					responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
					recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
					guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
					peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
					recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
					arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, null, null, null, 
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, anadirRetrabajos, anadirFormacion, nombreCarpeta);
		}

		llamadas.introducirServicio(servi);
		ventanaPrincipal.refrescarListaServicios();
		
		int seleccion = JOptionPane.showOptionDialog(
				null,"¿Deseas enviar un correo con los cambios?", "Enviar correo",JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "Enviar");
		
		if (seleccion == 0) {
			
			new VentanaCorreo(ventanaPrincipal, llamadas).setVisible(true);
		}
		else if (seleccion == 1) {
			dispose();
		}
//		utilidades.subirNuevoExcel(servi);
		
		
		
		
		
System.setProperty("java.net.preferIPv4Stack", "true");
    	
        String server = "lhcp1017.webapps.net";
        int port = 21;
        String user = "ireguatek@clientes-cpavitoria06.com";
        String pass = "Ireguatekcpa1";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("img/cpa.jpg");
            
            String dirToCreate = "/cpa";
            ftpClient.makeDirectory(dirToCreate);
            
            String firstRemoteFile = "cpa/cpa.jpg";
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
           
            inputStream.close();
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		
		
		
		
		
		
		dispose();
	}
	
	public void actualizarImagenesAccionesInstruccion(String imgs, int fila) {
		arrayImagenes[fila] = imgs;
	}
}
