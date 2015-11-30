package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class VentanaPestanasAMostrar extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;	
	
	private JCheckBox mostrarRetrabajos;
	private JCheckBox mostrarFormacion;
	
	public VentanaPestanasAMostrar(VentanaPrincipal venInventario, Cliente cliente, String persona, Llamadas llamadas) {
		
		super(venInventario, true);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}
		
		panel = new JPanel();
		panel.setLayout(null);
		
		mostrarRetrabajos = new JCheckBox("Gama de retrabajos");
		mostrarRetrabajos.setBounds(10, 40, 130, 20);
		panel.add(mostrarRetrabajos);
		mostrarFormacion = new JCheckBox("Formación personal");
		mostrarFormacion.setBounds(140, 40, 130, 20);
		panel.add(mostrarFormacion);
		JLabel texto = new JLabel("Selecciona las pestañas que deseas mostrar");
		texto.setBounds(30, 10, 240, 25);
		panel.add(texto);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.setBounds(45, 70, 80, 25);
		panel.add(aceptar);
		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
//TODO				new VentanaNuevo(venInventario, mostrarRetrabajos.isSelected(), mostrarFormacion.isSelected(), cliente, persona).setVisible(true);
				
				
//				Servicio serv = ll.recuperarServicio("numAccion");
				String numAccion = "A667788";
				new VentanaNuevo(venInventario, mostrarRetrabajos.isSelected(), mostrarFormacion.isSelected(), cliente, persona, numAccion, llamadas).setVisible(true);
			}
		});
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setBounds(130, 70, 80, 25);
		panel.add(cancelar);
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.getContentPane().add(panel);
		this.setSize(270, 140);
		this.setTitle("Pestañas a mostrar");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
