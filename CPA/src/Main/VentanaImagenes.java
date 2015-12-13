package Main;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultCaret;

public class VentanaImagenes extends JDialog {

	private JPanel panel;
	private String imagenes;
	private JLabel label;
	private JLabel label2;
	
	private JDialog ventanaPadre;
	private int fila;
	private int accion;
	
	private String ruta1;
	private String ruta2;

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

		Image image1 = null;
		Image image2 = null;
		
		ruta1 = imagenes.substring(0, imagenes.indexOf("#;#"));
		ruta2 = imagenes.substring(imagenes.indexOf("#;#")+3);
		
		label = new JLabel();
        label.setBounds(10, 50, 450, 450);
        label2 = new JLabel();
        label2.setBounds(470, 50, 450, 450);
        
		
        try {
        	if (!ruta1.equals("")) {
        		URL url = new URL(ruta1);
                image1 = ImageIO.read(url);
                label = new JLabel(new ImageIcon(image1));
                label.setBounds(10, 50, 450, 450);
        	}
        	if (!ruta2.equals("")) {
        		URL url = new URL(ruta2);
                image2 = ImageIO.read(url);
                label2 = new JLabel(new ImageIcon(image2));
                label2.setBounds(470, 50, 450, 450);
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        panel.add(label);
        panel.add(label2);
        
        JLabel nombreLabel = new JLabel("<html><h2>"+nombre+"</h2></html>");
        nombreLabel.setBounds(370, 10, 500, 30);
		panel.add(nombreLabel);
		
		JButton anadir1 = new JButton("Añadir imagen");
		JButton borrar1 = new JButton("Borrar imagen");
		JButton anadir2 = new JButton("Añadir imagen");
		JButton borrar2 = new JButton("Borrar imagen");
		
		anadir1.setBounds(125, 515, 100, 20);
		panel.add(anadir1);
		borrar1.setBounds(275, 515, 100, 20);
		panel.add(borrar1);
		anadir2.setBounds(560, 515, 100, 20);
		panel.add(anadir2);
		borrar2.setBounds(710, 515, 100, 20);
		panel.add(borrar2);
		
		anadir1.addActionListener(new ActionListener() {
			
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
							label.setIcon(new ImageIcon(ImageIO.read(new URL(imagenes.substring(0, imagenes.indexOf("#;#"))))));
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "El fichero seleccionado no existe", "Fichero no existe", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		anadir2.addActionListener(new ActionListener() {
			
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
							label2.setIcon(new ImageIcon(ImageIO.read(new URL(imagenes.substring(imagenes.indexOf("#;#")+3)))));
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "El fichero seleccionado no existe", "Fichero no existe", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		borrar1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				utilidades.borrarImagen(ruta1);
				ruta1 = "";
				actualizarImagenes();
				label.setIcon(null);
			}
		});
		borrar2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				utilidades.borrarImagen(ruta2);
				ruta2 = "";
				actualizarImagenes();
				label2.setIcon(null);
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
