package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.fabric.xmlrpc.base.Array;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;

public class VentanaNuevo2 extends JDialog {
	
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
	private JTable tablaHeaders2;
	private JTable defectos;
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
	
	public VentanaNuevo2(VentanaPrincipal venInventario, boolean anadirRetrabajos, boolean anadirFormacion, Servicio serv) {
		
		super(venInventario, true);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}
		
		panel = new JPanel();
		panel.setLayout(null);
		
		JTabbedPane panelDePestanas = new JTabbedPane(JTabbedPane.TOP);
		panelDePestanas.setBounds(20, 100, 800, 420);
		panel.add(panelDePestanas);

		tablaHeaders2 = new JTable();
		
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
		nombrePieza.setText(serv.getNombrePieza());
		nombrePieza.setBounds(115, 25, 150, 20);
		panelGenerales.add(nombrePieza);
		JLabel referenciasLabel = new JLabel("Referencias:");
		referenciasLabel.setBounds(10, 60, 95, 20);
		panelGenerales.add(referenciasLabel);
		referencias = new JTextField();
		referencias.setText(serv.getReferencias());
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
		numChasis1.setText(serv.getNumChasis1());
		numChasis2.setText(serv.getNumChasis2());
		numChasis3.setText(serv.getNumChasis3());
		numChasis4.setText(serv.getNumChasis4());
		responsable.setText(serv.getResponsableCPA());
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
		/*Object[][] data = {{"Prueba 1","Imágenes",false},{"Prueba 2","Imágenes",true},{"Prueba 3","Imágenes",true}
		,{"Prueba 3","Imágenes",true},{"Prueba 3","Imágenes",true},{"Prueba 3","Imágenes",true},{"Prueba 3","Imágenes",true}
		,{"Prueba 3","Imágenes",true},{"Prueba 3","Imágenes",true},{"Prueba 3","Imágenes",true},{"Prueba 3","Imágenes",true},{"Prueba 3","Imágenes",true}};*/
		
		Object[][] data = new Object[12][3];
		arrayImagenes = new String[12];
		
		String[] arr = serv.getAccionesIntruccion().split("@;@");
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
		/*for (int i=0; i<data.length; i++) {
			
		}*/
		
		
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
		trabajos.getColumnModel().getColumn(1).setCellEditor(new ClientsTableRenderer(new JCheckBox(), arrayImagenes));
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
		defectos = new JTable();
		DefaultTableModel modeloDefectos = (DefaultTableModel) defectos.getModel();
		SimpleDateFormat formateador = new SimpleDateFormat("dd");
		for (int i=0; i<30; i++) {
			modeloDefectos.addColumn(formateador.format(c.getTime()));
			c.add(Calendar.DATE, 1);
		}
		filaVacia = new String[30];
		for (int i=0; i<30; i++) {
			defectos.getColumnModel().getColumn(i).setPreferredWidth(30);
			defectos.getColumnModel().getColumn(i).setResizable(false);
			filaVacia[i] = "";
		}
		for (int i=0; i<6; i++) {
			modeloDefectos.addRow(filaVacia);
		}
		
		defectos.setRowSelectionAllowed(false);
		JScrollPane scrollDefectos = new JScrollPane(defectos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		defectos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		defectos.getTableHeader().setReorderingAllowed(false);
		tablaHeaders2.getTableHeader().setReorderingAllowed(false);
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
		piezas = new JTable();
		DefaultTableModel modeloPiezas = (DefaultTableModel) piezas.getModel();
		c = Calendar.getInstance();
		for (int i=0; i<30; i++) {
			modeloPiezas.addColumn(formateador.format(c.getTime()));
			c.add(Calendar.DATE, 1);
		}
		filaVacia = new String[30];
		for (int i=0; i<30; i++) {
			piezas.getColumnModel().getColumn(i).setPreferredWidth(30);
			piezas.getColumnModel().getColumn(i).setResizable(false);
			filaVacia[i] = "";
		}
		for (int i=0; i<2; i++) {
			modeloPiezas.addRow(filaVacia);
		}
		
		piezas.setRowSelectionAllowed(false);
		JScrollPane scrollPiezas = new JScrollPane(piezas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		piezas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		piezas.getTableHeader().setReorderingAllowed(false);
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
		recuento = new JTable();
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
		ExcelAdapter myAd = new ExcelAdapter(recuento);
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
		
		horas = new JTable();
		DefaultTableModel modeloHoras = (DefaultTableModel) horas.getModel();
		formateador = new SimpleDateFormat("dd");
		c = Calendar.getInstance();
		for (int i=0; i<30; i++) {
			modeloHoras.addColumn(formateador.format(c.getTime()));
			c.add(Calendar.DATE, 1);
		}
		filaVacia = new String[30];
		
		for (int i=0; i<30; i++) {
			horas.getColumnModel().getColumn(i).setPreferredWidth(30);
			horas.getColumnModel().getColumn(i).setResizable(false);
			filaVacia[i] = "";
		}
		for (int i=0; i<tiposHora.length; i++) {
			modeloHoras.addRow(filaVacia);
		}
		
		horas.setRowSelectionAllowed(false);
		JScrollPane scrollHoras = new JScrollPane(horas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		horas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		horas.getTableHeader().setReorderingAllowed(false);
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
		clienteLabel2 = new JLabel("<html><h4>"+serv.getNomCliente()+"</h4></html>");
		clienteLabel2.setBounds(90, 70, 300, 25);
		panel.add(clienteLabel2);
		
		fechaLabel = new JLabel("<html><h5>Fecha:</h5></html>");
		fechaLabel.setBounds(840, 245, 100, 25);
		panel.add(fechaLabel);
//		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		fechaLabel2 = new JLabel("<html><h4>"+sdf.format(serv.getFechaInicio())+"</h4></html>");
		fechaLabel2.setBounds(860, 265, 120, 25);
		panel.add(fechaLabel2);
		
		personalCPALabel = new JLabel("<html><h5>Persona CPA:</h5></html>");
		personalCPALabel.setBounds(530, 50, 100, 25);
		panel.add(personalCPALabel);
		personalCPALabel2 = new JLabel("<html><h4>"+serv.getPersonaCPA()+"</h4></html>");
		personalCPALabel2.setBounds(550, 70, 120, 25);
		panel.add(personalCPALabel2);
		
		numAccionLabel = new JLabel("<html><h5>Nº Acción:</h5></html>");
		numAccionLabel.setBounds(840, 175, 100, 25);
		panel.add(numAccionLabel);
		numAccionLabel2 = new JLabel("<html><h4>"+serv.getNumAccion()+"</h4></html>");
		numAccionLabel2.setBounds(860, 195, 120, 25);
		panel.add(numAccionLabel2);
		
		JLabel jlbPicture = new JLabel(new ImageIcon("img/cpa.jpg"));
		jlbPicture.setPreferredSize(new Dimension(240, 110));
		jlbPicture.setBounds(760, 5, 240, 110);
		panel.add(jlbPicture);
		
		JButton guardar = new JButton("Guardar y salir");
		guardar.setBounds(840, 440, 140, 20);
		panel.add(guardar);
		guardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				
				
				try {
					FileInputStream file = new FileInputStream(new File("plantilla/ochoa.xlsm"));

					
					XSSFWorkbook workbook = new XSSFWorkbook(file);

					XSSFSheet sheet = null;
					XSSFCell cell = null;
					Posiciones posiciones = new Posiciones();
					
					
					// ORDEN DE PEDIDO 1 
					sheet = workbook.getSheetAt(1);
					cell = sheet.getRow(posiciones.getNumAccion().getFila()).getCell(posiciones.getNumAccion().getColumna());
					cell.setCellValue(serv.getNumAccion());
					
					cell = sheet.getRow(posiciones.getFechaInicio().getFila()).getCell(posiciones.getFechaInicio().getColumna());
					cell.setCellValue(serv.getFechaInicio());
					
					cell = sheet.getRow(posiciones.getFechaFin().getFila()).getCell(posiciones.getFechaFin().getColumna());
					Calendar calen = Calendar.getInstance();
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
					cell.setCellValue(sdf.format(calen.getTime()));
					
					cell = sheet.getRow(posiciones.getNombrePieza().getFila()).getCell(posiciones.getNombrePieza().getColumna());
					cell.setCellValue(serv.getNombrePieza());
					
					cell = sheet.getRow(posiciones.getReferencias().getFila()).getCell(posiciones.getReferencias().getColumna());
					cell.setCellValue(serv.getReferencias());
					
					cell = sheet.getRow(posiciones.getNumChasis1().getFila()).getCell(posiciones.getNumChasis1().getColumna());
					cell.setCellValue(serv.getNumChasis1());
					
					cell = sheet.getRow(posiciones.getNumChasis2().getFila()).getCell(posiciones.getNumChasis2().getColumna());
					cell.setCellValue(serv.getNumChasis2());
					
					cell = sheet.getRow(posiciones.getNumChasis3().getFila()).getCell(posiciones.getNumChasis3().getColumna());
					cell.setCellValue(serv.getNumChasis3());
					
					cell = sheet.getRow(posiciones.getNumChasis4().getFila()).getCell(posiciones.getNumChasis4().getColumna());
					cell.setCellValue(serv.getNumChasis4());
					
					cell = sheet.getRow(posiciones.getResponsableCPA().getFila()).getCell(posiciones.getResponsableCPA().getColumna());
					cell.setCellValue(serv.getResponsableCPA());
					
					cell = sheet.getRow(posiciones.getPiezasVerde().getFila()).getCell(posiciones.getPiezasVerde().getColumna());
					if (serv.isPiezasVerde())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getPiezasBlanco().getFila()).getCell(posiciones.getPiezasBlanco().getColumna());
					if (serv.isPiezasBlanco())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getPiezasOtros().getFila()).getCell(posiciones.getPiezasOtros().getColumna());
					if (serv.isPiezasOtros())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getPiezasRojo().getFila()).getCell(posiciones.getPiezasRojo().getColumna());
					if (serv.isPiezasRojo())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getContenedorVerde().getFila()).getCell(posiciones.getContenedorVerde().getColumna());
					if (serv.isContenedorVerde())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getContenedorRojo().getFila()).getCell(posiciones.getContenedorRojo().getColumna());
					if (serv.isContenedorRojo())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getPersonaRecomendador().getFila()).getCell(posiciones.getPersonaRecomendador().getColumna());
					cell.setCellValue(serv.getPersonaRecomendador());
					
					cell = sheet.getRow(posiciones.getDepartamentoRecomendador().getFila()).getCell(posiciones.getDepartamentoRecomendador().getColumna());
					cell.setCellValue(serv.getDepartamentoRecomendador());
					
					cell = sheet.getRow(posiciones.getTelefonoRecomendador().getFila()).getCell(posiciones.getTelefonoRecomendador().getColumna());
					cell.setCellValue(serv.getTelefonoRecomendador());
					
					cell = sheet.getRow(posiciones.getEmailRecomendador().getFila()).getCell(posiciones.getEmailRecomendador().getColumna());
					cell.setCellValue(serv.getEmailRecomendador());
					
					cell = sheet.getRow(posiciones.getFechaSolicitudRecomendador().getFila()).getCell(posiciones.getFechaSolicitudRecomendador().getColumna());
					cell.setCellValue(serv.getFechaSolicitudRecomendador());
					
					cell = sheet.getRow(posiciones.getEmpresaCliente().getFila()).getCell(posiciones.getEmpresaCliente().getColumna());
					cell.setCellValue("Empresa cliente");
					
					cell = sheet.getRow(posiciones.getDireccionCliente().getFila()).getCell(posiciones.getDireccionCliente().getColumna());
					cell.setCellValue("getDireccionCliente");
					
					cell = sheet.getRow(posiciones.getCodigoPostalCliente().getFila()).getCell(posiciones.getCodigoPostalCliente().getColumna());
					cell.setCellValue("getCodigoPostalCliente");
					
					cell = sheet.getRow(posiciones.getPaisCliente().getFila()).getCell(posiciones.getPaisCliente().getColumna());
					cell.setCellValue("getPaisCliente");
					
					cell = sheet.getRow(posiciones.getPoblacionCliente().getFila()).getCell(posiciones.getPoblacionCliente().getColumna());
					cell.setCellValue("getPoblacionCliente");
					
					cell = sheet.getRow(posiciones.getApartadoDeCorreosCliente().getFila()).getCell(posiciones.getApartadoDeCorreosCliente().getColumna());
					cell.setCellValue("getApartadoDeCorreosCliente");
					
					cell = sheet.getRow(posiciones.getCodigoPostalCliente2().getFila()).getCell(posiciones.getCodigoPostalCliente2().getColumna());
					cell.setCellValue("getCodigoPostalCliente2");
					
					cell = sheet.getRow(posiciones.getCodigoPostalEmpresaCliente().getFila()).getCell(posiciones.getCodigoPostalEmpresaCliente().getColumna());
					cell.setCellValue("getCodigoPostalEmpresaCliente");
					
					cell = sheet.getRow(posiciones.getPersonaDeContactoCliente().getFila()).getCell(posiciones.getPersonaDeContactoCliente().getColumna());
					cell.setCellValue("getPersonaDeContactoCliente");
					
					cell = sheet.getRow(posiciones.getDepartamentoCliente().getFila()).getCell(posiciones.getDepartamentoCliente().getColumna());
					cell.setCellValue("getDepartamentoCliente");
					
					cell = sheet.getRow(posiciones.getTelefonoCliente().getFila()).getCell(posiciones.getTelefonoCliente().getColumna());
					cell.setCellValue("getTelefonoCliente");
					
					cell = sheet.getRow(posiciones.getEmailCliente().getFila()).getCell(posiciones.getEmailCliente().getColumna());
					cell.setCellValue("getEmailCliente");
					
					cell = sheet.getRow(posiciones.getDescripcionInstruccionDelServicio().getFila()).getCell(posiciones.getDescripcionInstruccionDelServicio().getColumna());
					cell.setCellValue(serv.getDescripcionInstruccionDelServicio());
					
					cell = sheet.getRow(posiciones.getSeguridadCalzado().getFila()).getCell(posiciones.getSeguridadCalzado().getColumna());
					if (serv.isSeguridadCalzado())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getSeguridadGafas().getFila()).getCell(posiciones.getSeguridadGafas().getColumna());
					if (serv.isSeguridadGafas())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getSeguridadChaleco().getFila()).getCell(posiciones.getSeguridadChaleco().getColumna());
					if (serv.isSeguridadChaleco())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getSeguridadTapones().getFila()).getCell(posiciones.getSeguridadTapones().getColumna());
					if (serv.isSeguridadTapones())
						cell.setCellValue("X");
					
					cell = sheet.getRow(posiciones.getSeguridadGuantes().getFila()).getCell(posiciones.getSeguridadGuantes().getColumna());
					if (serv.isSeguridadGuantes())
						cell.setCellValue("X");
					
					if (serv.getInformacionResultados() == 0)
						cell = sheet.getRow(posiciones.getInformacionResultadosDiaria().getFila()).getCell(posiciones.getInformacionResultadosDiaria().getColumna());
					else if (serv.getInformacionResultados() == 1)
						cell = sheet.getRow(posiciones.getInformacionResultadosSemanal().getFila()).getCell(posiciones.getInformacionResultadosSemanal().getColumna());
					else if (serv.getInformacionResultados() == 2)
						cell = sheet.getRow(posiciones.getInformacionResultadosMensual().getFila()).getCell(posiciones.getInformacionResultadosMensual().getColumna());
					else
						cell = sheet.getRow(posiciones.getInformacionResultadosOtros().getFila()).getCell(posiciones.getInformacionResultadosOtros().getColumna());
					cell.setCellValue("X");
					
					Utilidades ut = new Utilidades();
					ut.insertarImagen(workbook, sheet, serv.getImagenOrdenDePedido(), posiciones.getImagenOrdenDePedido(), 0, 0, 0);					
					
					// INSTRUCCION DE TRABAJO 2
					
					sheet = workbook.getSheetAt(2);
					
					String operario = "";
					
					if (serv.isOperarioA1())
						operario += "; A1";
					if (serv.isOperarioA2())
						operario += "; A2";
					if (serv.isOperarioA3())
						operario += "; A3";
					if (serv.isOperarioA4())
						operario += "; A4";
					if (serv.isOperarioA5())
						operario += "; A5";
					if (serv.isOperarioA6())
						operario += "; A6";
					if (serv.isOperarioA7())
						operario += "; A7";
					if (operario.startsWith("; "))
						operario = operario.substring(2);
					
					cell = sheet.getRow(posiciones.getOperario().getFila()).getCell(posiciones.getOperario().getColumna());
					cell.setCellValue(operario);
					
					
					String[] arrTemp = serv.getAccionesIntruccion().split("@;@");
					int fila = 0;
					int columna = 0;
					String[] imagenes;
					for (int i=0; i<arrTemp.length; i++) {
						if (columna == 0) {
							cell = sheet.getRow(posiciones.getAccionesInstruccionDescripcionInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionDescripcionInicial().getColumna());
							cell.setCellValue(arrTemp[i]);
						} else if (columna == 1) {
							imagenes = arrTemp[i].split("#;#");
							ut.insertarImagen(workbook, sheet, imagenes[0], posiciones.getAccionesInstruccionOtros1Inicial(), 0, 0, fila);
							ut.insertarImagen(workbook, sheet, imagenes[1], posiciones.getAccionesInstruccionOtros2Inicial(), 0, 0, fila);
						} else {
							cell = sheet.getRow(posiciones.getAccionesInstruccionAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionAplicaInicial().getColumna());
							if (arrTemp[i].equalsIgnoreCase("0")) {
								cell = sheet.getRow(posiciones.getAccionesInstruccionNoAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionNoAplicaInicial().getColumna());
							}
							cell.setCellValue("X");
						}
						
						columna++;
						if (columna > 2) {
							fila = fila + 3;
							if (fila == 33) {
								fila++;
							}
							columna = 0;
						}
					}
					
					// INFORMACION DE RESULTADOS 3
					
					sheet = workbook.getSheetAt(3);
					
					arrTemp = serv.getTablaDefectos().split("@;@");
					fila = 0;
					columna = 0;
					int contador = 0;
					boolean esDefecto = true;
					for (int i=0; i<arrTemp.length; i++) {
						if (esDefecto) {
							esDefecto = false;
							if (fila == 0) {
								cell = sheet.getRow(posiciones.getDefecto1Nombre().getFila()).getCell(posiciones.getDefecto1Nombre().getColumna());
								cell.setCellValue(arrTemp[i]);
							}
							else if (fila == 1) {
								cell = sheet.getRow(posiciones.getDefecto2Nombre().getFila()).getCell(posiciones.getDefecto2Nombre().getColumna());
								cell.setCellValue(arrTemp[i]);
							}
							else if (fila == 2) {
								cell = sheet.getRow(posiciones.getDefecto3Nombre().getFila()).getCell(posiciones.getDefecto3Nombre().getColumna());
								cell.setCellValue(arrTemp[i]);
							}
							else if (fila == 3) {
								cell = sheet.getRow(posiciones.getDefecto4Nombre().getFila()).getCell(posiciones.getDefecto4Nombre().getColumna());
								cell.setCellValue(arrTemp[i]);
							}
							else if (fila == 4) {
								cell = sheet.getRow(posiciones.getDefecto5Nombre().getFila()).getCell(posiciones.getDefecto5Nombre().getColumna());
								cell.setCellValue(arrTemp[i]);
							}
							else if (fila == 5) {
								cell = sheet.getRow(posiciones.getDefecto6Nombre().getFila()).getCell(posiciones.getDefecto6Nombre().getColumna());
								cell.setCellValue(arrTemp[i]);
							}
						}
						else {
							if (fila == 0) {
								cell = sheet.getRow(posiciones.getDefecto1Fechas().getFila()).getCell(posiciones.getDefecto1Fechas().getColumna()+contador);
								cell.setCellValue(Integer.parseInt(arrTemp[i]));
							}
							else if (fila == 1) {
								cell = sheet.getRow(posiciones.getDefecto2Fechas().getFila()).getCell(posiciones.getDefecto2Fechas().getColumna()+contador);
								cell.setCellValue(Integer.parseInt(arrTemp[i]));
							}
							else if (fila == 2) {
								cell = sheet.getRow(posiciones.getDefecto3Fechas().getFila()).getCell(posiciones.getDefecto3Fechas().getColumna()+contador);
								cell.setCellValue(Integer.parseInt(arrTemp[i]));
							}
							else if (fila == 3) {
								cell = sheet.getRow(posiciones.getDefecto4Fechas().getFila()).getCell(posiciones.getDefecto4Fechas().getColumna()+contador);
								cell.setCellValue(Integer.parseInt(arrTemp[i]));
							}
							else if (fila == 4) {
								cell = sheet.getRow(posiciones.getDefecto5Fechas().getFila()).getCell(posiciones.getDefecto5Fechas().getColumna()+contador);
								cell.setCellValue(Integer.parseInt(arrTemp[i]));
							}
							else if (fila == 5) {
								cell = sheet.getRow(posiciones.getDefecto6Fechas().getFila()).getCell(posiciones.getDefecto6Fechas().getColumna()+contador);
								cell.setCellValue(Integer.parseInt(arrTemp[i]));
							}
							contador++;
						}
						columna++;
						if (columna > 30) {
							fila++;
							contador = 0;
							columna = 0;
							esDefecto = true;
						}
					}
					
					
					
					arrTemp = serv.getPiezasOK().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getPiezasOK().getFila()).getCell(posiciones.getPiezasOK().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					arrTemp = serv.getPiezasRecuperadas().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getPiezasRecuperadas().getFila()).getCell(posiciones.getPiezasRecuperadas().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					
					
					// RECUENTO FINAL 5
					sheet = workbook.getSheetAt(5);
					
					arrTemp = serv.getRecuentoFinal().split("@;@");
					columna = 0;
					fila = 0;
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getRecuentoFinal().getFila()+fila).getCell(posiciones.getRecuentoFinal().getColumna()+columna);
						if (columna < 4)
							cell.setCellValue(arrTemp[i]);
						else
							cell.setCellValue(Integer.parseInt(arrTemp[i]));
						columna++;
						if (columna > 6) {
							fila++;
							columna = 0;
						}
					}
					
					
					
					
					// ESTIMACION DE HORAS Y COSTES 6
					sheet = workbook.getSheetAt(6);
					
					arrTemp = serv.getArrayHoraNormal().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraNormal().getFila()).getCell(posiciones.getArrayHoraNormal().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraExtra().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraExtra().getFila()).getCell(posiciones.getArrayHoraExtra().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraSabado().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraSabado().getFila()).getCell(posiciones.getArrayHoraSabado().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraFestivo().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraFestivo().getFila()).getCell(posiciones.getArrayHoraFestivo().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraNocturna().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraNocturna().getFila()).getCell(posiciones.getArrayHoraNocturna().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraEspecialistaNormal().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNormal().getFila()).getCell(posiciones.getArrayHoraEspecialistaNormal().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraEspecialistaFestiva().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraEspecialistaFestivo().getFila()).getCell(posiciones.getArrayHoraEspecialistaFestivo().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraEspecialistaNocturna().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNocturna().getFila()).getCell(posiciones.getArrayHoraEspecialistaNocturna().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraCoordinacion().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraCoordinacion().getFila()).getCell(posiciones.getArrayHoraCoordinacion().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayHoraAdministracion().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayHoraAdministracion().getFila()).getCell(posiciones.getArrayHoraAdministracion().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayGastosLogisticos().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayGastosLogisticos().getFila()).getCell(posiciones.getArrayGastosLogisticos().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					arrTemp = serv.getArrayOtros1().split("@;@");
					for (int i=0; i<arrTemp.length; i++) {
						cell = sheet.getRow(posiciones.getArrayOtros1().getFila()).getCell(posiciones.getArrayOtros1().getColumna()+i);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					
					
					
					
					// FORMACION PERSONAL 8
					sheet = workbook.getSheetAt(8);
					
					cell = sheet.getRow(posiciones.getRealizadoPorPersonal().getFila()).getCell(posiciones.getRealizadoPorPersonal().getColumna());
					cell.setCellValue(serv.getRealizadoPorPersonal());

					cell = sheet.getRow(posiciones.getFechaPersonal().getFila()).getCell(posiciones.getFechaPersonal().getColumna());
					cell.setCellValue(serv.getFechaPersonal());

					cell = sheet.getRow(posiciones.getClientePersonal().getFila()).getCell(posiciones.getClientePersonal().getColumna());
					cell.setCellValue(serv.getClientePersonal());

					cell = sheet.getRow(posiciones.getPiezaPersonal().getFila()).getCell(posiciones.getPiezaPersonal().getColumna());
					cell.setCellValue(serv.getPiezaPersonal());

					cell = sheet.getRow(posiciones.getReferenciaPersonal().getFila()).getCell(posiciones.getReferenciaPersonal().getColumna());
					cell.setCellValue(serv.getReferenciaPersonal());
					
					arrTemp = serv.getFirmasPersonal().split("@;@");
					columna = 0;
					fila = 0;
					for (int i=0; i<arrTemp.length; i++) {
						if (columna == 0) {
							cell = sheet.getRow(posiciones.getFirmasPersonalNombres().getFila()+fila).getCell(posiciones.getFirmasPersonalNombres().getColumna());
							cell.setCellValue(arrTemp[i]);
						}
						else {
							cell = sheet.getRow(posiciones.getFirmasPersonalFechas().getFila()+fila).getCell(posiciones.getFirmasPersonalFechas().getColumna());
							cell.setCellValue(arrTemp[i]);
						}
						columna++;
						if (columna > 1) {
							columna = 0;
							fila++;
						}
					}
					
					ut.insertarImagen(workbook, sheet, serv.getImagenPersonal(), posiciones.getImagenPersonal(), 0, 0, 0);	
					
					// GAMA DE RETRABAJOS 9
					sheet = workbook.getSheetAt(9);
					
					cell = sheet.getRow(posiciones.getRealizadoPorRetrabajos().getFila()).getCell(posiciones.getRealizadoPorRetrabajos().getColumna());
					cell.setCellValue(serv.getRealizadoPorRetrabajos());

					cell = sheet.getRow(posiciones.getFechaRetrabajos().getFila()).getCell(posiciones.getFechaRetrabajos().getColumna());
					cell.setCellValue(serv.getFechaRetrabajos());

					cell = sheet.getRow(posiciones.getFechaLiberacionRetrabajos().getFila()).getCell(posiciones.getFechaLiberacionRetrabajos().getColumna());
					cell.setCellValue(serv.getFechaLiberacionRetrabajos());

					cell = sheet.getRow(posiciones.getNumReclamacionRetrabajos().getFila()).getCell(posiciones.getNumReclamacionRetrabajos().getColumna());
					cell.setCellValue(serv.getNumReclamacionRetrabajos());

					cell = sheet.getRow(posiciones.getFechaReclamacionRetrabajos().getFila()).getCell(posiciones.getFechaReclamacionRetrabajos().getColumna());
					cell.setCellValue(serv.getFechaReclamacionRetrabajos());

					cell = sheet.getRow(posiciones.getReferenciaPiezaRetrabajos().getFila()).getCell(posiciones.getReferenciaPiezaRetrabajos().getColumna());
					cell.setCellValue(serv.getReferenciaPiezaRetrabajos());

					cell = sheet.getRow(posiciones.getFechaComienzoRetrabajos().getFila()).getCell(posiciones.getFechaComienzoRetrabajos().getColumna());
					cell.setCellValue(serv.getFechaComienzoRetrabajos());

					cell = sheet.getRow(posiciones.getTiempoRetrabajos().getFila()).getCell(posiciones.getTiempoRetrabajos().getColumna());
					cell.setCellValue(serv.getTiempoRetrabajos());

					cell = sheet.getRow(posiciones.getClienteRetrabajos().getFila()).getCell(posiciones.getClienteRetrabajos().getColumna());
					cell.setCellValue(serv.getClienteRetrabajos());
					
					arrTemp = serv.getFirmasRetrabajos().split("@;@");
					columna = 0;
					fila = 0;
					for (int i=0; i<arrTemp.length; i++) {
						if (columna == 0) {
							cell = sheet.getRow(posiciones.getFirmasRetrabajosNombres().getFila()+fila).getCell(posiciones.getFirmasRetrabajosNombres().getColumna());
							cell.setCellValue(arrTemp[i]);
						}
						else {
							cell = sheet.getRow(posiciones.getFirmasRetrabajosFechas().getFila()+fila).getCell(posiciones.getFirmasRetrabajosFechas().getColumna());
							cell.setCellValue(arrTemp[i]);
						}
						columna++;
						if (columna > 1) {
							columna = 0;
							fila++;
						}
					}
					
					ut.insertarImagen(workbook, sheet, serv.getImagenRetrabajos(), posiciones.getImagenRetrabajos(), 0, 0, 0);	
					
					
					
					XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					evaluator.evaluateAllFormulaCells(workbook);
					
					
					file.close();

					FileOutputStream outFile =new FileOutputStream(new File("plantilla/resultado.xlsm"));
					workbook.write(outFile);
					outFile.close();
					
			        
			        System.out.println("fin yeahhh");
			        

				} catch (Exception exp) {
					exp.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setBounds(840, 480, 140, 20);
		panel.add(cancelar);
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Llamadas ll = new Llamadas();
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
					servi = new Servicio(1,"a pelo", "mierda", "numAccion", cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
							responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
							recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
							guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
							peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
							recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
							arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, realizadoRetrabajos.getText(), fechaRetrabajos.getDate(), fechaLiberacion.getDate(), 
							numReclamacion.getText(), fechaReclamacion.getDate(), referenciaRetrabajos.getText(), fechaComienzo.getDate(), tiempo.getText(), clienteRetrabajos.getText(), firmasRetrabajos, "img/prueba.jpg", realizadoFormado.getText(), 
							fechaFormado.getDate(), clienteFormado.getText(), piezaFormado.getText(), referenciaFormado.getText(), firmasPersonal, "img/prueba.jpg", anadirRetrabajos, anadirFormacion);
				} else if (anadirFormacion) {
					servi = new Servicio(1,"a pelo", "mierda", "numAccion", cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
							responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
							recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
							guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
							peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
							recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
							arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, null, null, null, 
							null, null, null, null, null, null, null, null, realizadoFormado.getText(), 
							fechaFormado.getDate(), clienteFormado.getText(), piezaFormado.getText(), referenciaFormado.getText(), firmasPersonal, "img/prueba.jpg", anadirRetrabajos, anadirFormacion);
				} else if (anadirRetrabajos) {
					servi = new Servicio(1, "a pelo", "mierda", "numAccion", cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
							responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
							recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
							guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
							peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
							recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
							arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, realizadoRetrabajos.getText(), fechaRetrabajos.getDate(), fechaLiberacion.getDate(), 
							numReclamacion.getText(), fechaReclamacion.getDate(), referenciaRetrabajos.getText(), fechaComienzo.getDate(), tiempo.getText(), clienteRetrabajos.getText(), firmasRetrabajos, "img/prueba.jpg", null, 
							null, null, null, null, null, null, anadirRetrabajos, anadirFormacion);
				} else {
					servi = new Servicio(1 ,"a pelo", "mierda", "numAccion", cal.getTime(), nombrePieza.getText(), referencias.getText(), numChasis1.getText(), numChasis2.getText(), numChasis3.getText(), numChasis4.getText(),
							responsable.getText(), piezasVerde.isSelected(), piezasBlanco.isSelected(), piezasOtros.isSelected(), piezasRojo.isSelected(), contenedorVerde.isSelected(), contenedorRojo.isSelected(), recomPersonaContacto.getText(),
							recomDepartamento.getText(), recomTelefono.getText(), recomEmail.getText(), recomFechaSolicitud.getDate(), descripcionServicio.getText(), calzado.isSelected(), gafas.isSelected(), chaleco.isSelected(), tapones.isSelected(),
							guantes.isSelected(), res, "img/cpa.jpg", operarioA1.isSelected(), operarioA2.isSelected(), operarioA3.isSelected(), operarioA4.isSelected(), operarioA5.isSelected(), operarioA6.isSelected(), operarioA7.isSelected(),
							peticionMaterial.isSelected(), referenciasPiezas.isSelected(), seleccion.isSelected(), retrabajo.isSelected(), trasvase.isSelected(), otrosPiezas.isSelected(), accionesIntruccion, tablaDefectos, piezasOK, piezasRecuperadas,
							recuentoFinal, arrayHoraNormal, arrayHoraExtra, arrayHoraSabado, arrayHoraFestivo, arrayHoraNocturna, arrayHoraEspecialistaNormal, arrayHoraEspecialistaExtra, arrayHoraEspecialistaSabado, arrayHoraEspecialistaFestiva, 
							arrayHoraEspecialistaNocturna, arrayHoraCoordinacion, arrayHoraAdministracion, arrayGastosLogisticos, arrayOtros1, arrayOtros2, null, null, null, 
							null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, anadirRetrabajos, anadirFormacion);
				}
				
				ll.introducirServicio(servi);
			}
		});
		
		JButton guardarYGenerar = new JButton("Generar factura");
		guardarYGenerar.setBounds(840, 400, 140, 20);
		panel.add(guardarYGenerar);
		guardarYGenerar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(arrayFechasRetrabajos.get(4).getDate());
			}
		});
		
		
		
		
		piezasVerde.setSelected(serv.isPiezasVerde());
		piezasBlanco.setSelected(serv.isPiezasBlanco());
		piezasOtros.setSelected(serv.isPiezasOtros());
		piezasRojo.setSelected(serv.isPiezasRojo());
		contenedorVerde.setSelected(serv.isContenedorVerde());
		contenedorRojo.setSelected(serv.isContenedorRojo());
		recomPersonaContacto.setText(serv.getPersonaRecomendador());
		recomDepartamento.setText(serv.getDepartamentoRecomendador());
		recomTelefono.setText(serv.getTelefonoRecomendador());
		recomEmail.setText(serv.getEmailRecomendador());
		recomFechaSolicitud.setDate(serv.getFechaSolicitudRecomendador());
		descripcionServicio.setText(serv.getDescripcionInstruccionDelServicio());
		calzado.setSelected(serv.isSeguridadCalzado());
		gafas.setSelected(serv.isSeguridadGafas());
		chaleco.setSelected(serv.isSeguridadChaleco());
		guantes.setSelected(serv.isSeguridadGuantes());
		tapones.setSelected(serv.isSeguridadTapones());
		if (serv.getInformacionResultados() == 0) {
			diaria.setSelected(true);
		}
		if (serv.getInformacionResultados() == 1) {
			semanal.setSelected(true);
		}
		if (serv.getInformacionResultados() == 2) {
			mensual.setSelected(true);
		}
		if (serv.getInformacionResultados() == 3) {
			otros.setSelected(true);
		}
		
		
		operarioA1.setSelected(serv.isOperarioA1());
		operarioA2.setSelected(serv.isOperarioA2());
		operarioA3.setSelected(serv.isOperarioA3());
		operarioA4.setSelected(serv.isOperarioA4());
		operarioA5.setSelected(serv.isOperarioA5());
		operarioA6.setSelected(serv.isOperarioA6());
		operarioA7.setSelected(serv.isOperarioA7());
		
		peticionMaterial.setSelected(serv.isPeticionMaterialInstruccion());
		referenciasPiezas.setSelected(serv.isReferenciasCorrectasInstruccion());
		seleccion.setSelected(serv.isSeleccionPiezasInstruccion());
		retrabajo.setSelected(serv.isRetrabajoPiezasInstruccion());
		trasvase.setSelected(serv.isTrasvaseInstruccion());
		otrosPiezas.setSelected(serv.isOtrosInstruccion());
		//TODO TABLA DE IMAGENES (ACCIONES INSTRUCCION)
		
		
		
		String[] arrTemp = serv.getRecuentoFinal().split("@;@");
		int row = 0;
		int column = 0;
		for (int i=0; i<arrTemp.length; i++) {
			recuento.setValueAt(arrTemp[i], row, column);
			column++;
			if (column > 6) {
				column = 0;
				row++;
			}
		}
		
		arrTemp = serv.getTablaDefectos().split("@;@");
		row = 0;
		column = 0;
		for (int i=0; i<arrTemp.length; i++) {
			if (column == 0) {
				tablaHeaders2.setValueAt(arrTemp[i], row, column);
			} else {
				defectos.setValueAt(arrTemp[i], row, column-1);
			}
			column++;
			if (column > 30) {
				column = 0;
				row++;
			}
		}
		
		arrTemp = serv.getPiezasOK().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			piezas.setValueAt(arrTemp[i], 0, i);
		}
		
		arrTemp = serv.getPiezasRecuperadas().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			piezas.setValueAt(arrTemp[i], 1, i);
		}
		
		arrTemp = serv.getArrayHoraNormal().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 0, i );
		}
		
		arrTemp = serv.getArrayHoraExtra().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 1, i );
		}
		arrTemp = serv.getArrayHoraSabado().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 2, i );
		}
		arrTemp = serv.getArrayHoraFestivo().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 3, i );
		}
		arrTemp = serv.getArrayHoraNocturna().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 4, i );
		}
		arrTemp = serv.getArrayHoraEspecialistaNormal().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 5, i );
		}
		arrTemp = serv.getArrayHoraEspecialistaExtra().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 6, i );
		}
		arrTemp = serv.getArrayHoraEspecialistaSabado().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 7, i );
		}
		arrTemp = serv.getArrayHoraEspecialistaFestiva().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 8, i );
		}
		arrTemp = serv.getArrayHoraEspecialistaNocturna().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 9, i );
		}
		arrTemp = serv.getArrayHoraCoordinacion().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 10, i );
		}
		arrTemp = serv.getArrayHoraAdministracion().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 11, i );
		}
		arrTemp = serv.getArrayGastosLogisticos().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 12, i );
		}
		arrTemp = serv.getArrayOtros1().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 13, i );
		}
		arrTemp = serv.getArrayOtros2().split("@;@");
		for (int i=0; i<arrTemp.length; i++) {
			horas.setValueAt(arrTemp[i], 14, i );
		}
		
		if (anadirRetrabajos) {
		
		realizadoRetrabajos.setText(serv.getRealizadoPorRetrabajos());
		fechaRetrabajos.setDate(serv.getFechaRetrabajos());
		fechaLiberacion.setDate(serv.getFechaLiberacionRetrabajos());
		numReclamacion.setText(serv.getNumReclamacionRetrabajos());
		fechaReclamacion.setDate(serv.getFechaReclamacionRetrabajos());
		referenciaRetrabajos.setText(serv.getReferenciaPiezaRetrabajos());
		fechaComienzo.setDate(serv.getFechaComienzoRetrabajos());
		tiempo.setText(serv.getTiempoRetrabajos());
		clienteRetrabajos.setText(serv.getClienteRetrabajos());
		
		arrTemp = serv.getFirmasRetrabajos().split("@;@");
		row = 0;
		column = 0;
		for (int i=0; i<arrTemp.length; i++) {
			if(column == 0) {
				arrayNombresRetrabajos.get(row).setText(arrTemp[i]);
			} else {
				if (!arrTemp[i].equals(""))
					arrayFechasRetrabajos.get(row).setDate(new Date(arrTemp[i]));
			}
			column++;
			if (column > 1) {
				column = 0;
				row++;
			}
		}
		}
		
		if (anadirFormacion) {
		realizadoFormado.setText(serv.getRealizadoPorPersonal());
		fechaFormado.setDate(serv.getFechaPersonal());
		clienteFormado.setText(serv.getClientePersonal());
		piezaFormado.setText(serv.getPiezaPersonal());
		referenciaFormado.setText(serv.getReferenciaPersonal());
		
		arrTemp = serv.getFirmasPersonal().split("@;@");
		row = 0;
		column = 0;
		for (int i=0; i<arrTemp.length; i++) {
			if(column == 0) {
				arrayNombresPersonal.get(row).setText(arrTemp[i]);
			} else {
				if (!arrTemp[i].equals(""))
					arrayFechasPersonal.get(row).setDate(new Date(arrTemp[i]));
			}
			column++;
			if (column > 1) {
				column = 0;
				row++;
			}
		}
		}
		
		
		
		this.getContentPane().add(panel);
		this.setSize(1000, 560);
		this.setTitle("ERP CPA - Nueva factura");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
