package Main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class VentanaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private List<String> array;
	private DefaultListModel<String> listModel;
	private String stringTemporal;
	
	private VentanaPrincipal ventanaPrincipal;
	
	private JList<String> list;
	
	private JTextField busquedaField;
	
	private Llamadas llamadas = new Llamadas();
	
	private JLabel jlbPicture;
	
	public VentanaPrincipal() {
		
		ventanaPrincipal = this;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}
		
		
		ventanaPrincipal.setIconImage(new ImageIcon("img/cpaLogo.jpg").getImage());
		
		panel = new JPanel();
		panel.setLayout(null);

		JLabel tituloLabel = new JLabel("<html><h1>ERP CPA</h1></html>");
		tituloLabel.setBounds(200, 20, 250, 25);
		panel.add(tituloLabel);
		
		jlbPicture = new JLabel(new ImageIcon("img/cpa.jpg"));
		jlbPicture.setPreferredSize(new Dimension(240, 110));
		jlbPicture.setBounds(220, 100, 240, 110);
		panel.add(jlbPicture);

		JLabel buscarLabel = new JLabel("Buscar:");
		buscarLabel.setBounds(40, 40, 70, 25);
		panel.add(buscarLabel);

		busquedaField = new JTextField();
		busquedaField.setBounds(20, 70, 170, 25);
		panel.add(busquedaField);
		
		array = llamadas.recuperarListaServiciosEnLinea();
		
		listModel = new DefaultListModel<String>();
		for (int i=0; i<array.size(); i++) {
			listModel.addElement(array.get(i));
		}
		
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane1 = new JScrollPane(list);
		scrollPane1.setBounds(20, 120, 170, 200);
		panel.add(scrollPane1);
		
		busquedaField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				list.clearSelection();
				listModel.removeAllElements();
				for (int i=0; i<array.size(); i++) {
					if (array.get(i).toLowerCase().contains(busquedaField.getText().toLowerCase())) {
						listModel.addElement(array.get(i));
					}
					else {
						stringTemporal = array.get(i).toLowerCase();
						stringTemporal = stringTemporal.replace("á", "a");
						stringTemporal = stringTemporal.replace("é", "e");
						stringTemporal = stringTemporal.replace("í", "i");
						stringTemporal = stringTemporal.replace("ó", "o");
						stringTemporal = stringTemporal.replace("ú", "u");
						if (stringTemporal.contains(busquedaField.getText().toLowerCase())) {
							listModel.addElement(array.get(i));
						}
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		JButton nuevo = new JButton("<html>Nuevo<br/>servicio</html>");
		nuevo.setBounds(225, 225, 100, 40);
		panel.add(nuevo);
		nuevo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Cliente> arrCli = llamadas.recuperarClientes(); 
				Cliente[] clientes = arrCli.toArray(new Cliente[arrCli.size()]);
				ArrayList<String> arrPer = llamadas.recuperarPersonalCPA(); 
				String[] personas = arrPer.toArray(new String[arrPer.size()]);
				Cliente cliente = (Cliente) JOptionPane.showInputDialog(ventanaPrincipal, "Selecciona el cliente", "Selecciona el cliente", 
						JOptionPane.QUESTION_MESSAGE, null, clientes, clientes[0]);
				if (cliente != null) {
					String persona = (String) JOptionPane.showInputDialog(ventanaPrincipal, "Identifícate", "Identifícate", 
							JOptionPane.QUESTION_MESSAGE, null, personas, personas[0]); 
					if (persona != null) {
						new VentanaPestanasAMostrar(ventanaPrincipal, cliente, persona, llamadas).setVisible(true);
					}
				}
			}
		});
		
		JButton borrar = new JButton("<html>Borrar<br/>servicio</html>");
		borrar.setBounds(350, 225, 100, 40);
		panel.add(borrar);
		borrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecciona un servicio de la lista", "Servicio no seleccionado", JOptionPane.ERROR_MESSAGE);
				} else {
					int reply = JOptionPane.showConfirmDialog(null, "¿Deseas borrar el servicio seleccionado?", "Borrar", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						if (llamadas.borrarServicio(list.getSelectedValue())) {
							list.clearSelection();
							listModel.removeAllElements();
							array = llamadas.recuperarListaServiciosEnLinea();
							for (int i=0; i<array.size(); i++) {
								listModel.addElement(array.get(i));
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar borrar el servicio", "Servicio no borrado", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		JButton editar = new JButton("<html>Ver<br/>servicio</html>");
		editar.setBounds(225, 280, 100, 40);
		panel.add(editar);
		editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (list.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecciona un servicio de la lista", "Servicio no seleccionado", JOptionPane.ERROR_MESSAGE);
				} else {
					
					new VentanaEditar(ventanaPrincipal, llamadas.recuperarServicio(list.getSelectedValue()), llamadas).setVisible(true);;
					
					
				}
				
			}
		});
		
		JButton nuevaFactura = new JButton("<html>Generar<br/>factura</html>");
		nuevaFactura.setBounds(350, 280, 100, 40);
		panel.add(nuevaFactura);
		nuevaFactura.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		this.getContentPane().add(panel);
		this.setSize(500, 370);
		this.setTitle("ERP CPA");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void refrescarListaServicios() {
		list.clearSelection();
		listModel.removeAllElements();
		array = llamadas.recuperarListaServiciosEnLinea();
		for (int i=0; i<array.size(); i++) {
			listModel.addElement(array.get(i));
		}
	}
	
}
