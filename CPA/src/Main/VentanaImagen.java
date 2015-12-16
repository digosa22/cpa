package Main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

public class VentanaImagen extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private String imagen;
	private JLabel label;
	private int accion;
	private int pestanya;

	private String urlImg = "";

	private boolean existeImagen = false;
	private JButton botonAnadir;
	private JButton botonBorrar;

	private JDialog ventanaPadre;

	public VentanaImagen(JDialog ventanaPadre, String imag, int accion, int pestanya, String nombreCarpeta, Utilidades utilidades) {

		super(ventanaPadre, true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}

		this.ventanaPadre = ventanaPadre;
		this.accion = accion;
		this.pestanya = pestanya;

		imagen = imag;
		panel = new JPanel();
		panel.setLayout(null);

		label = new JLabel();
		label.setBounds(10, 50, 450, 450);


		try {
			if(!imagen.isEmpty()) {
				ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(imagen)));
				int anchoOriginal = imgTemporal.getIconWidth();
				int altoOriginal = imgTemporal.getIconHeight();
				if (anchoOriginal > altoOriginal) {
					if (anchoOriginal > 450) {
						double coeficiente = (double)450 / (double)anchoOriginal;
						anchoOriginal = (int) (coeficiente * anchoOriginal);
						altoOriginal = (int) (coeficiente * altoOriginal);
					}
				}
				else {
					if (anchoOriginal > 450) {
						double coeficiente = (double)450 / (double)altoOriginal;
						anchoOriginal = (int) (coeficiente * anchoOriginal);
						altoOriginal = (int) (coeficiente * altoOriginal);
					}
				}
				Image img = imgTemporal.getImage().getScaledInstance(anchoOriginal, altoOriginal, Image.SCALE_SMOOTH);
				label = new JLabel(new ImageIcon(img));
				label.setBounds(10, 50, 450, 450);
				existeImagen = true;
			} else {
				label = new JLabel();
				label.setIcon(null);
				label.setBounds(10, 50, 450, 450);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		panel.add(label);

		JLabel nombreLabel = new JLabel("<html><h2>Pantallazo validación</h2></html>");
		if (pestanya == 9)
			nombreLabel.setText("<html><h2>Imagen firma validación</h2></html>");
		if (pestanya == 1)
			nombreLabel.setText("<html><h2>Imagen formación personal</h2></html>");
		else if (pestanya == 2)
			nombreLabel.setText("<html><h2>Imagen gama retrabajos</h2></html>");
		nombreLabel.setBounds(100, 10, 500, 30);
		panel.add(nombreLabel);

		botonAnadir = new JButton("Añadir imagen");
		botonBorrar = new JButton("Borrar imagen");
		botonBorrar.setEnabled(existeImagen);

		botonAnadir.setBounds(125, 515, 100, 20);
		panel.add(botonAnadir);
		botonBorrar.setBounds(275, 515, 100, 20);
		panel.add(botonBorrar);

		botonAnadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(".");

				FileFilter filter = new ExtensionFileFilter("Imagenes (*.jpg, *.png)", new String[] { "jpg", "png" });
				fileChooser.setFileFilter(filter);

				int status = fileChooser.showOpenDialog(null);

				if (status == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					File folderFiles = new File(selectedFile.getParent());
					String[] array = folderFiles.list();
					ArrayList<String> excelFiles = new ArrayList<>();
					String filename = selectedFile.getName();

					boolean existe = false;
					for (int i=0; i<array.length; i++) {
						if (array[i].toLowerCase().endsWith(".jpg") || array[i].toLowerCase().endsWith(".png"))
							excelFiles.add(array[i].substring(0, array[i].length()-4));
					}

					if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".png"))
						filename = filename.substring(0, filename.length()-4);

					for (int i=0; i<excelFiles.size() && !existe; i++) {
						if (excelFiles.get(i).toLowerCase().equals(filename.toLowerCase()))
							existe = true;
					}

					if (existe) {
						int reply = 0;
						if (existeImagen && accion == 1)
							reply = JOptionPane.showConfirmDialog(null, "¿Deseas sobreescribir la imagen actual por la imagen seleccionada?", "Sobreescribir imagen", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							imagen = selectedFile.getAbsolutePath();

							String extension = ".jpg";
							if(imagen.toLowerCase().endsWith(".png"))
								extension = ".png";

							utilidades.crearCarpetas(nombreCarpeta);
							utilidades.subirImagen(imagen, nombreCarpeta, pestanya, extension);

							try {
								urlImg = "http://clientes-cpavitoria06.com/"+nombreCarpeta+"/img/";
								if (pestanya == 0)
									urlImg += "ordendepedido";
								else if (pestanya == 9)
									urlImg += "firmaValidacion";
								else if (pestanya == 1)
									urlImg += "personal";
								else
									urlImg += "retrabajos";
								urlImg += extension;
								ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(urlImg)));
								int anchoOriginal = imgTemporal.getIconWidth();
								int altoOriginal = imgTemporal.getIconHeight();
								if (anchoOriginal > altoOriginal) {
									if (anchoOriginal > 450) {
										double coeficiente = (double)450 / (double)anchoOriginal;
										anchoOriginal = (int) (coeficiente * anchoOriginal);
										altoOriginal = (int) (coeficiente * altoOriginal);
									}
								}
								else {
									if (anchoOriginal > 450) {
										double coeficiente = (double)450 / (double)altoOriginal;
										anchoOriginal = (int) (coeficiente * anchoOriginal);
										altoOriginal = (int) (coeficiente * altoOriginal);
									}
								}
								Image img = imgTemporal.getImage().getScaledInstance(anchoOriginal, altoOriginal, Image.SCALE_SMOOTH);
								actualizarImagen();
								label.setIcon(new ImageIcon(img));
							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					else
						JOptionPane.showMessageDialog(null, "El fichero seleccionado no existe", "Fichero no existe", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		botonBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Deseas borrar la imagen?", "Borrar imagen", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					utilidades.borrarImagen(imagen.substring(imagen.indexOf(".com/")+4));
					imagen = "";
					borrarImagen();
					label.setIcon(null);
				}
			}
		});

		this.getContentPane().add(panel);
		this.setSize(470, 580);
		this.setTitle("Añadir imagen");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actualizarImagen() {
		if (accion == 0) {
			((VentanaNuevo)ventanaPadre).actualizarImagen(urlImg, pestanya);
		}
		else {
			((VentanaEditar)ventanaPadre).actualizarImagen(urlImg, pestanya);
		}
		existeImagen = true;
		botonBorrar.setEnabled(true);
	}

	public void borrarImagen() {
		if (accion == 0) {
			((VentanaNuevo)ventanaPadre).actualizarImagen("", pestanya);
		}
		else {
			((VentanaEditar)ventanaPadre).actualizarImagen("", pestanya);
		}
		existeImagen = false;
		botonBorrar.setEnabled(false);
	}
}
