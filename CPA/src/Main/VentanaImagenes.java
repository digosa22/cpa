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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

public class VentanaImagenes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private String[] arrayInstrucciones;
	private JLabel label;
	private JLabel label2;

	private JDialog ventanaPadre;
	private int nuevoEditar;
	
	private int accionSeleccionada = 0;
	private JComboBox<String> comboAcciones;
	private JTextArea textoArea;
	private JCheckBox aplicaCheckbox;

	private String texto;
	private String imagen1;
	private String imagen2;
	private boolean aplica;

	private JButton botonAnadir1;
	private JButton botonBorrar1;
	private JButton botonAnadir2;
	private JButton botonBorrar2;

	private boolean existeImagen1 = false;
	private boolean existeImagen2 = false;

	public VentanaImagenes(JDialog ventanaPadre, String[] arrayInst, int nuevoEditar, Utilidades utilidades, String nombreCarpeta) {

		super(ventanaPadre, true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}

		this.ventanaPadre = ventanaPadre;
		this.nuevoEditar = nuevoEditar;

		this.arrayInstrucciones = arrayInst;
		panel = new JPanel();
		panel.setLayout(null);
		
		cargarDatosDeAccion();
		
		JLabel seleccionaAccionLabel = new JLabel("<html><h4>Selecciona la acci�n a modificar</h4></html>");
		seleccionaAccionLabel.setBounds(10, 15, 200, 30);
		panel.add(seleccionaAccionLabel);
		comboAcciones = new JComboBox<String>();
		comboAcciones.addItem("Acci�n 3");
		comboAcciones.addItem("Acci�n 4");
		comboAcciones.addItem("Acci�n 5");
		comboAcciones.addItem("Acci�n 6");
		comboAcciones.addItem("Acci�n 7");
		comboAcciones.addItem("Acci�n 8");
		comboAcciones.addItem("Acci�n 9");
		comboAcciones.addItem("Acci�n 10");
		comboAcciones.addItem("Acci�n 11");
		comboAcciones.addItem("Acci�n 12");
		comboAcciones.setBounds(40, 60, 120, 30);
		panel.add(comboAcciones);

		label = new JLabel();
		label.setBounds(300, 20, 300, 300);
		label2 = new JLabel();
		label2.setBounds(620, 20, 300, 300);
		
		textoArea = new JTextArea();
		textoArea.setLineWrap(true);
		textoArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textoArea);
		scrollPane.setBounds(15, 120, 200, 180);
		panel.add(scrollPane);
		
		aplicaCheckbox = new JCheckBox("Aplica");
		aplicaCheckbox.setBounds(200, 65, 80, 20);
		panel.add(aplicaCheckbox);
		
		imagen1 = "http://lamelonera.com/wp-content/uploads/2014/03/textura-negra_2-1600x1591.jpg";
		imagen2 = "http://lamelonera.com/wp-content/uploads/2014/03/textura-negra_2-1600x1591.jpg";
		

		try {
			if (!imagen1.isEmpty()) {
				ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(imagen1)));
				int anchoOriginal = imgTemporal.getIconWidth();
				int altoOriginal = imgTemporal.getIconHeight();
				if (anchoOriginal > altoOriginal) {
					if (anchoOriginal > 300) {
						double coeficiente = (double)300 / (double)anchoOriginal;
						anchoOriginal = (int) (coeficiente * anchoOriginal);
						altoOriginal = (int) (coeficiente * altoOriginal);
					}
				}
				else {
					if (anchoOriginal > 300) {
						double coeficiente = (double)300 / (double)altoOriginal;
						anchoOriginal = (int) (coeficiente * anchoOriginal);
						altoOriginal = (int) (coeficiente * altoOriginal);
					}
				}
				Image img = imgTemporal.getImage().getScaledInstance(anchoOriginal, altoOriginal, Image.SCALE_SMOOTH);
				label = new JLabel(new ImageIcon(img));
				label.setBounds(300, 20, 300, 300);
				existeImagen1 = true;
			}
			if (!imagen2.isEmpty()) {
				ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(imagen2)));
				int anchoOriginal = imgTemporal.getIconWidth();
				int altoOriginal = imgTemporal.getIconHeight();
				if (anchoOriginal > altoOriginal) {
					if (anchoOriginal > 300) {
						double coeficiente = (double)300 / (double)anchoOriginal;
						anchoOriginal = (int) (coeficiente * anchoOriginal);
						altoOriginal = (int) (coeficiente * altoOriginal);
					}
				}
				else {
					if (anchoOriginal > 300) {
						double coeficiente = (double)300 / (double)altoOriginal;
						anchoOriginal = (int) (coeficiente * anchoOriginal);
						altoOriginal = (int) (coeficiente * altoOriginal);
					}
				}
				Image img = imgTemporal.getImage().getScaledInstance(anchoOriginal, altoOriginal, Image.SCALE_SMOOTH);
				label2 = new JLabel(new ImageIcon(img));
				label2.setBounds(620, 20, 300, 300);
				existeImagen2 = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		panel.add(label);
		panel.add(label2);

		botonAnadir1 = new JButton("A�adir imagen");
		botonBorrar1 = new JButton("Borrar imagen");
		botonBorrar1.setEnabled(existeImagen1);
		botonAnadir2 = new JButton("A�adir imagen");
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
						if (existeImagen1 && nuevoEditar == 1)
							reply = JOptionPane.showConfirmDialog(null, "�Deseas sobreescribir la imagen actual por la imagen seleccionada?", "Sobreescribir imagen", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							String imagen = selectedFile.getAbsolutePath();

							String extension = ".jpg";
							if(imagen.toLowerCase().endsWith(".png"))
								extension = ".png";

							utilidades.crearCarpetas(nombreCarpeta);
							int temp = accionSeleccionada + 1;
							String nombre = "instruccion" + temp + "1" + extension;
							utilidades.subirImagenInstruccion(imagen, nombreCarpeta, nombre);
							imagen1 = "http://clientes-cpavitoria06.com/"+nombreCarpeta+"/img/"+nombre;
							actualizarImagenes();
							try {
								ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(imagen1)));
								int anchoOriginal = imgTemporal.getIconWidth();
								int altoOriginal = imgTemporal.getIconHeight();
								if (anchoOriginal > altoOriginal) {
									if (anchoOriginal > 300) {
										double coeficiente = (double)300 / (double)anchoOriginal;
										anchoOriginal = (int) (coeficiente * anchoOriginal);
										altoOriginal = (int) (coeficiente * altoOriginal);
									}
								}
								else {
									if (anchoOriginal > 300) {
										double coeficiente = (double)300 / (double)altoOriginal;
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
						if (existeImagen2 && nuevoEditar == 1)
							reply = JOptionPane.showConfirmDialog(null, "�Deseas sobreescribir la imagen actual por la imagen seleccionada?", "Sobreescribir imagen", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							String imagen = selectedFile.getAbsolutePath();

							String extension = ".jpg";
							if(imagen.toLowerCase().endsWith(".png"))
								extension = ".png";

							utilidades.crearCarpetas(nombreCarpeta);
							int temp = accionSeleccionada + 1;
							String nombre = "instruccion" + temp + "2" + extension;
							utilidades.subirImagenInstruccion(imagen, nombreCarpeta, nombre);

							imagen2 = "http://clientes-cpavitoria06.com/"+nombreCarpeta+"/img/"+nombre;
							actualizarImagenes();
							try {
								ImageIcon imgTemporal = new ImageIcon(ImageIO.read(new URL(imagen2)));
								int anchoOriginal = imgTemporal.getIconWidth();
								int altoOriginal = imgTemporal.getIconHeight();
								if (anchoOriginal > altoOriginal) {
									if (anchoOriginal > 300) {
										double coeficiente = (double)300 / (double)anchoOriginal;
										anchoOriginal = (int) (coeficiente * anchoOriginal);
										altoOriginal = (int) (coeficiente * altoOriginal);
									}
								}
								else {
									if (anchoOriginal > 300) {
										double coeficiente = (double)300 / (double)altoOriginal;
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
				int reply = JOptionPane.showConfirmDialog(null, "�Deseas borrar la imagen?", "Borrar imagen", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					utilidades.borrarImagen(imagen1);
					imagen1 = "";
					actualizarImagenes();
					botonBorrar1.setEnabled(false);
					label.setIcon(null);
				}
			}
		});
		botonBorrar2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "�Deseas borrar la imagen?", "Borrar imagen", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					utilidades.borrarImagen(imagen2);
					imagen2 = "";
					actualizarImagenes();
					botonBorrar2.setEnabled(false);
					label2.setIcon(null);
				}
			}
		});

		this.getContentPane().add(panel);
		this.setSize(940, 580);
		this.setTitle("Acciones instrucci�n");
		this.setResizable(false);
		this.setLocation((getToolkit().getScreenSize().width - this.getBounds().width) / 2,
				(getToolkit().getScreenSize().height - this.getBounds().height) / 2);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void cargarDatosDeAccion() {
		String temp = arrayInstrucciones[accionSeleccionada];
		texto = temp.substring(0, temp.indexOf("@;@"));
		temp = temp.substring(temp.indexOf("@;@")+3);
		imagen1 = temp.substring(0, temp.indexOf("#;#"));
		temp = temp.substring(temp.indexOf("#;#")+3);
		imagen2 = temp.substring(0, temp.indexOf("@;@"));
		temp = temp.substring(temp.indexOf("@;@")+3);
		if (temp.equalsIgnoreCase("0")) {
			aplica = false;
		} else {
			aplica = true;
		}
	}

	public void actualizarImagenes() {
		
//		imagenes = imagen1 + "#;#" + imagen2;
//		if (nuevoEditar == 0) {
//			((VentanaNuevo)ventanaPadre).actualizarAccionesInstruccion(arrayInstrucciones);
//		}
//		else {
//			((VentanaEditar)ventanaPadre).actualizarAccionesInstruccion(arrayInstrucciones);
//		}
	}

}
