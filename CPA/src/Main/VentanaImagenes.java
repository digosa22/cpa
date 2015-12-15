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

public class VentanaImagenes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private String imagenes;
	private JLabel label;
	private JLabel label2;

	private JDialog ventanaPadre;
	private int fila;
	private int accion;

	private String ruta1;
	private String ruta2;

	private JButton botonAnadir1;
	private JButton botonBorrar1;
	private JButton botonAnadir2;
	private JButton botonBorrar2;

	private boolean existeImagen1 = false;
	private boolean existeImagen2 = false;

	public VentanaImagenes(JDialog ventanaPadre, String imags, int fila, String nombre, int accion, Utilidades utilidades, String nombreCarpeta) {

		super(ventanaPadre, true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}

		this.ventanaPadre = ventanaPadre;
		this.fila = fila;
		this.accion = accion;

		imagenes = imags;
		panel = new JPanel();
		panel.setLayout(null);

		ruta1 = imagenes.substring(0, imagenes.indexOf("#;#"));
		ruta2 = imagenes.substring(imagenes.indexOf("#;#")+3);

		label = new JLabel();
		label.setBounds(10, 50, 450, 450);
		label2 = new JLabel();
		label2.setBounds(470, 50, 450, 450);


		try {
			if (!ruta1.isEmpty()) {
				ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(ruta1)));
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
				existeImagen1 = true;
			}
			if (!ruta2.isEmpty()) {
				ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(ruta2)));
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
				label2 = new JLabel(new ImageIcon(img));
				label2.setBounds(470, 50, 450, 450);
				existeImagen2 = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		panel.add(label);
		panel.add(label2);

		JLabel nombreLabel = new JLabel("<html><h2>"+nombre+"</h2></html>");
		nombreLabel.setBounds(370, 10, 500, 30);
		panel.add(nombreLabel);

		botonAnadir1 = new JButton("Añadir imagen");
		botonBorrar1 = new JButton("Borrar imagen");
		botonBorrar1.setEnabled(existeImagen1);
		botonAnadir2 = new JButton("Añadir imagen");
		botonBorrar2 = new JButton("Borrar imagen");
		botonBorrar2.setEnabled(existeImagen2);

		botonAnadir1.setBounds(125, 515, 100, 20);
		panel.add(botonAnadir1);
		botonBorrar1.setBounds(275, 515, 100, 20);
		panel.add(botonBorrar1);
		botonAnadir2.setBounds(560, 515, 100, 20);
		panel.add(botonAnadir2);
		botonBorrar2.setBounds(710, 515, 100, 20);
		panel.add(botonBorrar2);

		botonAnadir1.addActionListener(new ActionListener() {

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
						if (existeImagen1 && accion == 1)
							reply = JOptionPane.showConfirmDialog(null, "¿Deseas sobreescribir la imagen actual por la imagen seleccionada?", "Sobreescribir imagen", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							String imagen = selectedFile.getAbsolutePath();

							String extension = ".jpg";
							if(imagen.toLowerCase().endsWith(".png"))
								extension = ".png";

							utilidades.crearCarpetas(nombreCarpeta);
							int temp = fila + 1;
							String nombre = "instruccion" + temp + "1" + extension;
							utilidades.subirImagenInstruccion(imagen, nombreCarpeta, nombre);
							ruta1 = "http://clientes-cpavitoria06.com/"+nombreCarpeta+"/img/"+nombre;
							actualizarImagenes();
							try {
								ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(ruta1)));
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
								botonBorrar1.setEnabled(true);
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
		botonAnadir2.addActionListener(new ActionListener() {

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
						if (existeImagen2 && accion == 1)
							reply = JOptionPane.showConfirmDialog(null, "¿Deseas sobreescribir la imagen actual por la imagen seleccionada?", "Sobreescribir imagen", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							String imagen = selectedFile.getAbsolutePath();

							String extension = ".jpg";
							if(imagen.toLowerCase().endsWith(".png"))
								extension = ".png";

							utilidades.crearCarpetas(nombreCarpeta);
							int temp = fila + 1;
							String nombre = "instruccion" + temp + "2" + extension;
							utilidades.subirImagenInstruccion(imagen, nombreCarpeta, nombre);

							ruta2 = "http://clientes-cpavitoria06.com/"+nombreCarpeta+"/img/"+nombre;
							actualizarImagenes();
							try {
								ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(ruta2)));
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
								botonBorrar2.setEnabled(true);
								label2.setIcon(new ImageIcon(img));
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
		botonBorrar1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Deseas borrar la imagen?", "Borrar imagen", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					utilidades.borrarImagen(ruta1);
					ruta1 = "";
					actualizarImagenes();
					botonBorrar1.setEnabled(false);
					label.setIcon(null);
				}
			}
		});
		botonBorrar2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Deseas borrar la imagen?", "Borrar imagen", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					utilidades.borrarImagen(ruta2);
					ruta2 = "";
					actualizarImagenes();
					botonBorrar2.setEnabled(false);
					label2.setIcon(null);
				}
			}
		});

		this.getContentPane().add(panel);
		this.setSize(940, 580);
		this.setTitle("Añadir imágenes");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actualizarImagenes() {
		imagenes = ruta1 + "#;#" + ruta2;
		if (accion == 0) {
			((VentanaNuevo)ventanaPadre).actualizarImagenesAccionesInstruccion(imagenes, fila);
		}
		else {
			((VentanaEditar)ventanaPadre).actualizarImagenesAccionesInstruccion(imagenes, fila);
		}
	}

}
