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

		Image image1 = null;
		
		label = new JLabel();
        label.setBounds(10, 50, 450, 450);
        
		
        try {
        		if(!imagen.isEmpty()) {
	        		URL url = new URL(imagen);
	                image1 = ImageIO.read(url);
	                label = new JLabel(new ImageIcon(image1));
	                label.setBounds(10, 50, 450, 450);
        		} else {
        			label = new JLabel();
        			label.setIcon(null);
	                label.setBounds(10, 50, 450, 450);
        		}
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        panel.add(label);
        
        JLabel nombreLabel = new JLabel("<html><h2>Imagen orden de pedido</h2></html>");
        if (pestanya == 1)
        	nombreLabel.setText("<html><h2>Imagen formación personal</h2></html>");
        else if (pestanya == 2)
        	nombreLabel.setText("<html><h2>Imagen gama retrabajos</h2></html>");
        nombreLabel.setBounds(370, 10, 500, 30);
		panel.add(nombreLabel);
		
		JButton anadir1 = new JButton("Añadir imagen");
		JButton borrar1 = new JButton("Borrar imagen");
		
		anadir1.setBounds(125, 515, 100, 20);
		panel.add(anadir1);
		borrar1.setBounds(275, 515, 100, 20);
		panel.add(borrar1);
		
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
							else if (pestanya == 1)
								urlImg += "personal";
							else
								urlImg += "retrabajos";
							urlImg += extension;
							label.setIcon(new ImageIcon(ImageIO.read(new URL(urlImg))));
							actualizarImagen();
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
				utilidades.borrarImagen(imagen.substring(imagen.indexOf(".com/")+4));
				imagen = "";
				borrarImagen();
				label.setIcon(null);
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
	
	public void actualizarImagen() {
		if (accion == 0) {
			((VentanaNuevo)ventanaPadre).actualizarImagen(urlImg, pestanya);
		}
		else {
			((VentanaEditar)ventanaPadre).actualizarImagen(urlImg, pestanya);
		}
	}
	
	public void borrarImagen() {
		if (accion == 0) {
			((VentanaNuevo)ventanaPadre).actualizarImagen("", pestanya);
		}
		else {
			((VentanaEditar)ventanaPadre).actualizarImagen("", pestanya);
		}
	}

}
