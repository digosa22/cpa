package Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HiloEspera implements Runnable {

	private Utilidades uti;
	private Servicio servi;
	private JFrame ventanaPrincipal;

	public HiloEspera(Utilidades uti, Servicio servi, JFrame padre) {
		this.uti = uti;
		this.servi = servi;
		this.ventanaPrincipal = padre;
	}

	@Override
	public void run() {

		ventanaPrincipal.setEnabled(false);
		VentanaEspera ventanaEspera = new VentanaEspera(ventanaPrincipal);
		ventanaEspera.setVisible(true);
		uti.subirNuevoExcel(servi);
		ventanaEspera.dispose();
		JOptionPane.showMessageDialog(ventanaPrincipal, "Servicio guardado correctamente", "Servicio", JOptionPane.INFORMATION_MESSAGE);
		ventanaPrincipal.setEnabled(true);
		ventanaPrincipal.requestFocus();
	}
}
