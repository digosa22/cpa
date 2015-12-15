package Main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class VentanaEspera extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel mensaje;

	public VentanaEspera(JFrame ventanaPrincipal) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}
		
		panel = new JPanel();
		panel.setLayout(null);
		
		mensaje = new JLabel("<html><h3>Guardando servicio...</h3></html>");
		mensaje.setBounds(10, 10, 150, 20);
		panel.add(mensaje);		
		
		this.getContentPane().add(panel);
		this.setSize(200, 75);
		this.setTitle("Guardar");
		this.setResizable(false);
		this.setLocation(ventanaPrincipal.getLocation().x+160, ventanaPrincipal.getLocation().y+125);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

}
