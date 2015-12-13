package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class VentanaCorreo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private JPanel panel;
	private JLabel paraLabel;
	private JLabel ccLabel;
	private JLabel asuntoLabel;
	private JTextField para;
	private JTextField cc;
	private JTextField asunto;
	private JTextArea mensaje;
	private JButton aceptar;
	private JButton cancelar;

	public VentanaCorreo(JFrame padre, Llamadas llamadas, String nombreCarpeta) {

		super(padre, true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}

		panel = new JPanel();
		panel.setLayout(null);

		paraLabel = new JLabel("Para: ");
		paraLabel.setBounds(10, 10, 60, 20);
		panel.add(paraLabel);
		ccLabel = new JLabel("CC: ");
		ccLabel.setBounds(10, 50, 60, 20);
		panel.add(ccLabel);
		asuntoLabel = new JLabel("Asunto: ");
		asuntoLabel.setBounds(10, 90, 60, 20);
		panel.add(asuntoLabel);
		para = new JTextField();
		para.setBounds(60, 10, 315, 20);
		panel.add(para);
		cc = new JTextField();
		cc.setBounds(60, 50, 315, 20);
		panel.add(cc);
		asunto = new JTextField();
		asunto.setBounds(60, 90, 315, 20);
		panel.add(asunto);
		mensaje = new JTextArea();
		mensaje.setLineWrap(true);
		mensaje.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(mensaje);
		scrollPane.setBounds(20, 130, 355, 180);
		panel.add(scrollPane);	
		aceptar = new JButton("Aceptar");
		aceptar.setBounds(105, 320, 80, 20);
		panel.add(aceptar);

		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				if(!para.getText().trim().isEmpty() 
						&& !asunto.getText().trim().isEmpty()
						&& !mensaje.getText().trim().isEmpty()) 
				{
					String[] arrEmailsPara = para.getText().split(";");
					
					boolean emailParaCorrectos = true;
					int cont = 0;
					
					do {
					   emailParaCorrectos = validateEmail(arrEmailsPara[cont]);
					   cont++;
					} while (cont < arrEmailsPara.length && emailParaCorrectos);
					
					
					boolean emailCcCorrectos = true;
					if (!cc.getText().trim().isEmpty()) {
						String[] arrEmailsCc = cc.getText().split(";");
						
						cont = 0;
						
						do {
							emailCcCorrectos = validateEmail(arrEmailsCc[cont]);
						   cont++;
						} while (cont < arrEmailsCc.length && emailCcCorrectos);
					}					

					if (!emailParaCorrectos) {
						
						JOptionPane.showMessageDialog(
								null, "No has escrito correctamente los correos en el apartado PARA", "Correos incorrectos", JOptionPane.ERROR_MESSAGE);
						
					} else if (! emailCcCorrectos) {
						
						JOptionPane.showMessageDialog(
								null, "No has escrito correctamente los correos en el apartado CC", "Correos incorrectos", JOptionPane.ERROR_MESSAGE);
						
					} else {
						
						Mensaje mens = new Mensaje(para.getText(), cc.getText(), asunto.getText(), mensaje.getText(), nombreCarpeta );
						
						llamadas.nuevoMensaje(mens);
						
						dispose();
					}
				    
				} else {
					JOptionPane.showMessageDialog(
							null, "Los campos para, asunto y mensaje son obligatorios", "Campos vac�os", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(215, 320, 80, 20);
		panel.add(cancelar);	
		
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.getContentPane().add(panel);
		this.setSize(400, 380);
		this.setTitle("Enviar correo");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
 
    public static boolean validateEmail(String email) {
 
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }

}
