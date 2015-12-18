package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import Main.Posicion;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;


public class Utilidades {

	private String server = "lhcp1017.webapps.net";
	private int port = 21;
	private String user = "ireguatek@clientes-cpavitoria06.com";
	private String pass = "Ireguatekcpa1";
	private FTPClient ftpClient = new FTPClient();

//	public void insertarImagen2( XSSFWorkbook wb, XSSFSheet sheet, String imagen, Posicion posicion, int ancho, int alto, int filaSuma) {
//
//		try {
//
//			File i = new File("img/descarga_prueba.jpg");
//
//			BufferedImage originalImage = ImageIO.read(new File(imagen));
//			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
//
//			BufferedImage resizeImageJpg = resizeImage(originalImage, type, ancho, alto);
//			ImageIO.write(resizeImageJpg, "jpg", i); 
//
//			//FileInputStream obtains input bytes from the image file
//			InputStream inputStream = new FileInputStream("img/descarga_prueba.jpg");
//
//			//Get the contents of an InputStream as a byte[].
//			byte[] bytes = IOUtils.toByteArray(inputStream);
//			//Adds a picture to the workbook
//			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
//			//close the input stream
//			inputStream.close();
//
//			//Returns an object that handles instantiating concrete classes
//			CreationHelper helper = wb.getCreationHelper();
//
//			//Creates the top-level drawing patriarch.
//			Drawing drawing = sheet.createDrawingPatriarch();
//
//			//Create an anchor that is attached to the worksheet
//			ClientAnchor anchor = helper.createClientAnchor();
//			//set top-left corner for the image
//			anchor.setCol1(posicion.getColumna());
//			anchor.setRow1(posicion.getFila() + filaSuma);
//
//			//Creates a picture
//			Picture pict = drawing.createPicture(anchor, pictureIdx);
//			//Reset the image to the original size
//			pict.resize();
//
//			i.delete();
//
//
//		}
//		catch (Exception e) {
//		}
//
//	}

	public void insertarImagen(XSSFWorkbook wb, XSSFSheet sheet, String rutaImagenSinExtension, String extension, Posicion posicion, int anchoMaximo, int altoMaximo, int filaSuma) {

		try {
			File temporal = new File(rutaImagenSinExtension+"2."+extension);
			File original = new File(rutaImagenSinExtension+"."+extension);

			BufferedImage originalImage = ImageIO.read(original);
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			int anchoOriginal = originalImage.getWidth();
			int altoOriginal = originalImage.getHeight();

			int ancho = 50;
			int alto = 50;
			if (anchoOriginal > anchoMaximo) {
				double coeficiente = (double)anchoMaximo / (double)anchoOriginal;
				ancho = (int) (coeficiente * anchoOriginal);
				alto = (int) (coeficiente * altoOriginal);
			}
			else {
				ancho = anchoOriginal;
				alto = altoOriginal;
			}
			if (alto > altoMaximo) {
				double coeficiente = (double)altoMaximo / (double)alto;
				ancho = (int) (coeficiente * ancho);
				alto = (int) (coeficiente * alto);
			}

			BufferedImage resizeImageJpg = resizeImage(originalImage, type, ancho, alto);
			ImageIO.write(resizeImageJpg, extension, temporal); 

			//FileInputStream obtains input bytes from the image file
			InputStream inputStream = new FileInputStream(rutaImagenSinExtension+"2."+extension);

			//Get the contents of an InputStream as a byte[].
			byte[] bytes = IOUtils.toByteArray(inputStream);
			//Adds a picture to the workbook

			int pictureIdx = 0;
			if (extension.equalsIgnoreCase("jpg"))
				pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			else
				pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			//close the input stream
			inputStream.close();

			//Returns an object that handles instantiating concrete classes
			CreationHelper helper = wb.getCreationHelper();

			//Creates the top-level drawing patriarch.
			Drawing drawing = sheet.createDrawingPatriarch();

			//Create an anchor that is attached to the worksheet
			ClientAnchor anchor = helper.createClientAnchor();
			//set top-left corner for the image
			anchor.setCol1(posicion.getColumna());
			anchor.setRow1(posicion.getFila() + filaSuma);

			//Creates a picture
			Picture pict = drawing.createPicture(anchor, pictureIdx);
			//Reset the image to the original size
			pict.resize();

			temporal.delete();
			original.delete();

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public BufferedImage resizeImage(BufferedImage originalImage, int type, int ancho, int alto){
		BufferedImage resizedImage = new BufferedImage(ancho, alto, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, ancho, alto, null);
		g.dispose();

		return resizedImage;
	}

	public void descargarImagen(String imgUrl, String destino) {
		try {
			URL imagen = new URL(imgUrl);
			try (InputStream in = imagen.openStream()) {
				File f = new File(destino);
				Files.copy(in, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean subirNuevoExcel(Servicio servicio)  {

		ClienteRecuperado cliente = new Llamadas().recuperarCliente(servicio.getIdCliente());

		String idioma = cliente.getIdioma();
		String plantilla = "plantilla/plantilla-"+idioma+".xlsm";

		try {
			FileInputStream file = new FileInputStream(new File(plantilla));


			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = null;
			XSSFCell cell = null;
			Posiciones posiciones = new Posiciones();

			//VALIDACION 0
			sheet = workbook.getSheetAt(0);

			cell = sheet.getRow(posiciones.getNif().getFila()).getCell(posiciones.getNif().getColumna());
			cell.setCellValue(cliente.getNif());

			cell = sheet.getRow(posiciones.getWeb().getFila()).getCell(posiciones.getWeb().getColumna());
			cell.setCellValue(cliente.getWeb());

			cell = sheet.getRow(posiciones.getNumBanco().getFila()).getCell(posiciones.getNumBanco().getColumna());
			cell.setCellValue(cliente.getBanco());

			cell = sheet.getRow(posiciones.getNumCuenta().getFila()).getCell(posiciones.getNumCuenta().getColumna());
			cell.setCellValue(cliente.getNum_cc());

			cell = sheet.getRow(posiciones.getIBAN().getFila()).getCell(posiciones.getIBAN().getColumna());
			cell.setCellValue(cliente.getIban());

			cell = sheet.getRow(posiciones.getBIC().getFila()).getCell(posiciones.getBIC().getColumna());
			cell.setCellValue(cliente.getBic());

			cell = sheet.getRow(posiciones.getTarifa().getFila()).getCell(posiciones.getTarifa().getColumna());
			cell.setCellValue("Hora Normal: " + cliente.getHora_normal() + " €/h");

			if (!servicio.getImagenPantallazoValidacion().isEmpty()) {
				String destino = "img/ordenpedido"+servicio.getNumAccion()+servicio.getImagenPantallazoValidacion().substring(servicio.getImagenPantallazoValidacion().length()-4);
				descargarImagen(servicio.getImagenPantallazoValidacion(), destino);
				insertarImagen(workbook, sheet, destino.substring(0, destino.length()-4), destino.substring(destino.length()-3), posiciones.getImagenPantallazoValidacion(), 480, 700, 0);
			}
			
			if (!servicio.getImagenFirmaValidacion().isEmpty()) {
				String destino = "img/firmaValidacion"+servicio.getNumAccion()+servicio.getImagenFirmaValidacion().substring(servicio.getImagenFirmaValidacion().length()-4);
				descargarImagen(servicio.getImagenFirmaValidacion(), destino);
				insertarImagen(workbook, sheet, destino.substring(0, destino.length()-4), destino.substring(destino.length()-3), posiciones.getImagenFirmaValidacion(), 250, 75, 0);
			}



			// ORDEN DE PEDIDO 1 
			sheet = workbook.getSheetAt(1);
			cell = sheet.getRow(posiciones.getNumAccion().getFila()).getCell(posiciones.getNumAccion().getColumna());
			cell.setCellValue(servicio.getNumAccion());

			cell = sheet.getRow(posiciones.getFechaInicio().getFila()).getCell(posiciones.getFechaInicio().getColumna());
			cell.setCellValue(servicio.getFechaInicio());

			cell = sheet.getRow(posiciones.getFechaFin().getFila()).getCell(posiciones.getFechaFin().getColumna());//TODO FECHA FIN AL CERRAR SERVICIO

			cell = sheet.getRow(posiciones.getNombrePieza().getFila()).getCell(posiciones.getNombrePieza().getColumna());
			cell.setCellValue(servicio.getNombrePieza());

			cell = sheet.getRow(posiciones.getReferencias().getFila()).getCell(posiciones.getReferencias().getColumna());
			cell.setCellValue(servicio.getReferencias());

			cell = sheet.getRow(posiciones.getNumChasis1().getFila()).getCell(posiciones.getNumChasis1().getColumna());
			cell.setCellValue(servicio.getNumChasis1());

			cell = sheet.getRow(posiciones.getNumChasis2().getFila()).getCell(posiciones.getNumChasis2().getColumna());
			cell.setCellValue(servicio.getNumChasis2());

			cell = sheet.getRow(posiciones.getNumChasis3().getFila()).getCell(posiciones.getNumChasis3().getColumna());
			cell.setCellValue(servicio.getNumChasis3());

			cell = sheet.getRow(posiciones.getNumChasis4().getFila()).getCell(posiciones.getNumChasis4().getColumna());
			cell.setCellValue(servicio.getNumChasis4());

			cell = sheet.getRow(posiciones.getResponsableCPA().getFila()).getCell(posiciones.getResponsableCPA().getColumna());
			cell.setCellValue(servicio.getResponsableCPA());

			cell = sheet.getRow(posiciones.getPiezasVerde().getFila()).getCell(posiciones.getPiezasVerde().getColumna());
			if (servicio.isPiezasVerde())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getPiezasBlanco().getFila()).getCell(posiciones.getPiezasBlanco().getColumna());
			if (servicio.isPiezasBlanco())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getPiezasOtros().getFila()).getCell(posiciones.getPiezasOtros().getColumna());
			if (servicio.isPiezasOtros())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getPiezasRojo().getFila()).getCell(posiciones.getPiezasRojo().getColumna());
			if (servicio.isPiezasRojo())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getContenedorVerde().getFila()).getCell(posiciones.getContenedorVerde().getColumna());
			if (servicio.isContenedorVerde())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getContenedorRojo().getFila()).getCell(posiciones.getContenedorRojo().getColumna());
			if (servicio.isContenedorRojo())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getPersonaRecomendador().getFila()).getCell(posiciones.getPersonaRecomendador().getColumna());
			cell.setCellValue(servicio.getPersonaRecomendador());

			cell = sheet.getRow(posiciones.getDepartamentoRecomendador().getFila()).getCell(posiciones.getDepartamentoRecomendador().getColumna());
			cell.setCellValue(servicio.getDepartamentoRecomendador());

			cell = sheet.getRow(posiciones.getTelefonoRecomendador().getFila()).getCell(posiciones.getTelefonoRecomendador().getColumna());
			cell.setCellValue(servicio.getTelefonoRecomendador());

			cell = sheet.getRow(posiciones.getEmailRecomendador().getFila()).getCell(posiciones.getEmailRecomendador().getColumna());
			cell.setCellValue(servicio.getEmailRecomendador());

			if (servicio.getFechaSolicitudRecomendador() != null) {
				cell = sheet.getRow(posiciones.getFechaSolicitudRecomendador().getFila()).getCell(posiciones.getFechaSolicitudRecomendador().getColumna());
				cell.setCellValue(servicio.getFechaSolicitudRecomendador());
			}

			cell = sheet.getRow(posiciones.getEmpresaCliente().getFila()).getCell(posiciones.getEmpresaCliente().getColumna());
			cell.setCellValue(cliente.getEmpresa());

			cell = sheet.getRow(posiciones.getDireccionCliente().getFila()).getCell(posiciones.getDireccionCliente().getColumna());
			cell.setCellValue(cliente.getDireccion());

			cell = sheet.getRow(posiciones.getCodigoPostalCliente().getFila()).getCell(posiciones.getCodigoPostalCliente().getColumna());
			cell.setCellValue(cliente.getCodigo_postal()); 

			cell = sheet.getRow(posiciones.getPaisCliente().getFila()).getCell(posiciones.getPaisCliente().getColumna());
			cell.setCellValue(cliente.getPais());

			cell = sheet.getRow(posiciones.getPoblacionCliente().getFila()).getCell(posiciones.getPoblacionCliente().getColumna());
			cell.setCellValue(cliente.getPoblacion());

			cell = sheet.getRow(posiciones.getApartadoDeCorreosCliente().getFila()).getCell(posiciones.getApartadoDeCorreosCliente().getColumna());
			cell.setCellValue(cliente.getApartado_de_correo());

			cell = sheet.getRow(posiciones.getCodigoPostalCliente2().getFila()).getCell(posiciones.getCodigoPostalCliente2().getColumna());
			cell.setCellValue(cliente.getCodigo_postal_2());

			cell = sheet.getRow(posiciones.getCodigoPostalEmpresaCliente().getFila()).getCell(posiciones.getCodigoPostalEmpresaCliente().getColumna());
			cell.setCellValue(cliente.getCodigo_postal_empresa());

			cell = sheet.getRow(posiciones.getPersonaDeContactoCliente().getFila()).getCell(posiciones.getPersonaDeContactoCliente().getColumna());
			cell.setCellValue(cliente.getPersona_contacto());

			cell = sheet.getRow(posiciones.getDepartamentoCliente().getFila()).getCell(posiciones.getDepartamentoCliente().getColumna());
			cell.setCellValue(cliente.getDepartamento());

			cell = sheet.getRow(posiciones.getTelefonoCliente().getFila()).getCell(posiciones.getTelefonoCliente().getColumna());
			cell.setCellValue(cliente.getTelefono());

			cell = sheet.getRow(posiciones.getEmailCliente().getFila()).getCell(posiciones.getEmailCliente().getColumna());
			cell.setCellValue(cliente.getEmail());

			cell = sheet.getRow(posiciones.getDescripcionInstruccionDelServicio().getFila()).getCell(posiciones.getDescripcionInstruccionDelServicio().getColumna());
			cell.setCellValue(servicio.getDescripcionInstruccionDelServicio());

			cell = sheet.getRow(posiciones.getSeguridadCalzado().getFila()).getCell(posiciones.getSeguridadCalzado().getColumna());
			if (servicio.isSeguridadCalzado())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getSeguridadGafas().getFila()).getCell(posiciones.getSeguridadGafas().getColumna());
			if (servicio.isSeguridadGafas())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getSeguridadChaleco().getFila()).getCell(posiciones.getSeguridadChaleco().getColumna());
			if (servicio.isSeguridadChaleco())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getSeguridadTapones().getFila()).getCell(posiciones.getSeguridadTapones().getColumna());
			if (servicio.isSeguridadTapones())
				cell.setCellValue("X");

			cell = sheet.getRow(posiciones.getSeguridadGuantes().getFila()).getCell(posiciones.getSeguridadGuantes().getColumna());
			if (servicio.isSeguridadGuantes())
				cell.setCellValue("X");

			if (servicio.getInformacionResultados() == 0)
				cell = sheet.getRow(posiciones.getInformacionResultadosDiaria().getFila()).getCell(posiciones.getInformacionResultadosDiaria().getColumna());
			else if (servicio.getInformacionResultados() == 1)
				cell = sheet.getRow(posiciones.getInformacionResultadosSemanal().getFila()).getCell(posiciones.getInformacionResultadosSemanal().getColumna());
			else if (servicio.getInformacionResultados() == 2)
				cell = sheet.getRow(posiciones.getInformacionResultadosMensual().getFila()).getCell(posiciones.getInformacionResultadosMensual().getColumna());
			else
				cell = sheet.getRow(posiciones.getInformacionResultadosOtros().getFila()).getCell(posiciones.getInformacionResultadosOtros().getColumna());
			cell.setCellValue("X");



			// INSTRUCCION DE TRABAJO 2

			sheet = workbook.getSheetAt(2);

			String operario = "";

			if (servicio.isOperarioA1())
				operario += "; A1";
			if (servicio.isOperarioA2())
				operario += "; A2";
			if (servicio.isOperarioA3())
				operario += "; A3";
			if (servicio.isOperarioA4())
				operario += "; A4";
			if (servicio.isOperarioA5())
				operario += "; A5";
			if (servicio.isOperarioA6())
				operario += "; A6";
			if (servicio.isOperarioA7())
				operario += "; A7";
			if (servicio.isOperarioA8())
				operario += "; A8";
			if (servicio.isOperarioA9())
				operario += "; A9";
			if (operario.startsWith("; "))
				operario = operario.substring(2);

			cell = sheet.getRow(posiciones.getOperario().getFila()).getCell(posiciones.getOperario().getColumna());
			cell.setCellValue(operario);
			
			if (servicio.isPeticionMaterialInstruccion()) {
				cell = sheet.getRow(posiciones.getPeticionMaterialInstruccionAplica().getFila()).getCell(posiciones.getPeticionMaterialInstruccionAplica().getColumna());
				cell.setCellValue("X");
			} else {
				cell = sheet.getRow(posiciones.getPeticionMaterialInstruccionNoAplica().getFila()).getCell(posiciones.getPeticionMaterialInstruccionNoAplica().getColumna());
				cell.setCellValue("X");
			}
			if (servicio.isReferenciasCorrectasInstruccion()) {
				cell = sheet.getRow(posiciones.getReferenciasCorrectasInstruccionAplica().getFila()).getCell(posiciones.getReferenciasCorrectasInstruccionAplica().getColumna());
				cell.setCellValue("X");
			} else {
				cell = sheet.getRow(posiciones.getReferenciasCorrectasInstruccionNoAplica().getFila()).getCell(posiciones.getReferenciasCorrectasInstruccionNoAplica().getColumna());
				cell.setCellValue("X");
			}
			if (servicio.isSeleccionPiezasInstruccion()) {
				cell = sheet.getRow(posiciones.getSeleccionPiezasInstruccionAplica().getFila()).getCell(posiciones.getSeleccionPiezasInstruccionAplica().getColumna());
				cell.setCellValue("X");
			} else {
				cell = sheet.getRow(posiciones.getSeleccionPiezasInstruccionNoAplica().getFila()).getCell(posiciones.getSeleccionPiezasInstruccionNoAplica().getColumna());
				cell.setCellValue("X");
			}
			if (servicio.isRetrabajoPiezasInstruccion()) {
				cell = sheet.getRow(posiciones.getRetrabajoPiezasInstruccionAplica().getFila()).getCell(posiciones.getRetrabajoPiezasInstruccionAplica().getColumna());
				cell.setCellValue("X");
			} else {
				cell = sheet.getRow(posiciones.getRetrabajoPiezasInstruccionNoAplica().getFila()).getCell(posiciones.getRetrabajoPiezasInstruccionNoAplica().getColumna());
				cell.setCellValue("X");
			}
			if (servicio.isTrasvaseInstruccion()) {
				cell = sheet.getRow(posiciones.getTrasvaseInstruccionAplica().getFila()).getCell(posiciones.getTrasvaseInstruccionAplica().getColumna());
				cell.setCellValue("X");
			} else {
				cell = sheet.getRow(posiciones.getTrasvaseInstruccionNoAplica().getFila()).getCell(posiciones.getTrasvaseInstruccionNoAplica().getColumna());
				cell.setCellValue("X");
			}
			if (servicio.isOtrosInstruccion()) {
				cell = sheet.getRow(posiciones.getOtrosInstruccionAplica().getFila()).getCell(posiciones.getOtrosInstruccionAplica().getColumna());
				cell.setCellValue("X");
			} else {
				cell = sheet.getRow(posiciones.getOtrosInstruccionNoAplica().getFila()).getCell(posiciones.getOtrosInstruccionNoAplica().getColumna());
				cell.setCellValue("X");
			}
			
			cell = sheet.getRow(posiciones.getAccionesInstruccionComprobacionServicioPrestado().getFila()).getCell(posiciones.getAccionesInstruccionComprobacionServicioPrestado().getColumna());
			cell.setCellValue(servicio.getComprobacionServicioInstruccion());
			
			//TODO PONER EL accionesInstruccionPiezaMuestraImagen1 Y accionesInstruccionPiezaMuestraImagen2


			String[] arrTemp = servicio.getAccionesIntruccion().split("@;@");
			int fila = 0;
			int columna = 0;
//			String[] imagenes;
//			boolean anadir = false;
//			for (int i=0; i<arrTemp.length; i++) {
//
//				if (i%3 == 0 || i == 0) {
//					if (arrTemp[i].trim().isEmpty()) {
//						anadir = false;
//					}
//					else {
//						anadir = true;
//					}
//				}
//
//				if (anadir){
//					if (columna == 0) {
//						cell = sheet.getRow(posiciones.getAccionesInstruccionDescripcionInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionDescripcionInicial().getColumna());
//						cell.setCellValue(arrTemp[i]);
//					} else if (columna == 1) {
//						imagenes = new String[2];
//						imagenes[0] = arrTemp[i].substring(0, arrTemp[i].indexOf("#;#"));
//						imagenes[1] = arrTemp[i].substring(arrTemp[i].indexOf("#;#")+3);
//						if (!imagenes[0].isEmpty()) {
//							String destino = "img/instruccion"+servicio.getNumAccion()+imagenes[0].substring(imagenes[0].length()-4);
//							descargarImagen(imagenes[0], destino);
//							insertarImagen(workbook, sheet, destino.substring(0, destino.length()-4), destino.substring(destino.length()-3), posiciones.getAccionesInstruccionOtros1Inicial(), 150, 150, fila);//TODO CAMBIAR ANCHO Y ALTO CUANDO ESTE PLANTILLA
//						}
//						if (!imagenes[1].isEmpty()) {
//							String destino = "img/instruccion"+servicio.getNumAccion()+imagenes[1].substring(imagenes[1].length()-4);
//							descargarImagen(imagenes[1], destino);
//							insertarImagen(workbook, sheet, destino.substring(0, destino.length()-4), destino.substring(destino.length()-3), posiciones.getAccionesInstruccionOtros2Inicial(), 150, 150, fila);//TODO CAMBIAR ANCHO Y ALTO CUANDO ESTE PLANTILLA
//						}
//					} else {
//						cell = sheet.getRow(posiciones.getAccionesInstruccionAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionAplicaInicial().getColumna());
//						if (arrTemp[i].equalsIgnoreCase("0")) {
//							cell = sheet.getRow(posiciones.getAccionesInstruccionNoAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionNoAplicaInicial().getColumna());
//						}
//						cell.setCellValue("X");
//					}
//				}
//
//				columna++;
//				if (columna > 2) {
//					fila = fila + 3;
//					if (fila == 33) {
//						fila++;
//					}
//					columna = 0;
//				}
//			}

			// INFORMACION DE RESULTADOS 3

			sheet = workbook.getSheetAt(3);
			
			SimpleDateFormat formateador = new SimpleDateFormat("dd-MMM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(servicio.getFechaInicio());
			for (int i=0; i<30; i++) {
				cell = sheet.getRow(posiciones.getArrayDeFechasInformacion().getFila()).getCell(posiciones.getArrayDeFechasInformacion().getColumna()+i);
				cell.setCellValue(formateador.format(cal.getTime()));
				cal.add(Calendar.DATE, 1);
			}

			arrTemp = servicio.getTablaDefectos().split("@;@");
			fila = 0;
			columna = 0;
			int contador = 0;
			boolean esDefecto = true;
			for (int i=0; i<arrTemp.length; i++) {
				if (esDefecto) {
					esDefecto = false;
					if (fila == 0) {
						cell = sheet.getRow(posiciones.getDefecto1Nombre().getFila()).getCell(posiciones.getDefecto1Nombre().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					else if (fila == 1) {
						cell = sheet.getRow(posiciones.getDefecto2Nombre().getFila()).getCell(posiciones.getDefecto2Nombre().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					else if (fila == 2) {
						cell = sheet.getRow(posiciones.getDefecto3Nombre().getFila()).getCell(posiciones.getDefecto3Nombre().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					else if (fila == 3) {
						cell = sheet.getRow(posiciones.getDefecto4Nombre().getFila()).getCell(posiciones.getDefecto4Nombre().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					else if (fila == 4) {
						cell = sheet.getRow(posiciones.getDefecto5Nombre().getFila()).getCell(posiciones.getDefecto5Nombre().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					else if (fila == 5) {
						cell = sheet.getRow(posiciones.getDefecto6Nombre().getFila()).getCell(posiciones.getDefecto6Nombre().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
				}
				else {
					if (fila == 0) {
						cell = sheet.getRow(posiciones.getDefecto1Fechas().getFila()).getCell(posiciones.getDefecto1Fechas().getColumna()+contador);
						if (!arrTemp[i].isEmpty())
							cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 1) {
						cell = sheet.getRow(posiciones.getDefecto2Fechas().getFila()).getCell(posiciones.getDefecto2Fechas().getColumna()+contador);
						if (!arrTemp[i].isEmpty())
							cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 2) {
						cell = sheet.getRow(posiciones.getDefecto3Fechas().getFila()).getCell(posiciones.getDefecto3Fechas().getColumna()+contador);
						if (!arrTemp[i].isEmpty())
							cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 3) {
						cell = sheet.getRow(posiciones.getDefecto4Fechas().getFila()).getCell(posiciones.getDefecto4Fechas().getColumna()+contador);
						if (!arrTemp[i].isEmpty())
							cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 4) {
						cell = sheet.getRow(posiciones.getDefecto5Fechas().getFila()).getCell(posiciones.getDefecto5Fechas().getColumna()+contador);
						if (!arrTemp[i].isEmpty())
							cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 5) {
						cell = sheet.getRow(posiciones.getDefecto6Fechas().getFila()).getCell(posiciones.getDefecto6Fechas().getColumna()+contador);
						if (!arrTemp[i].isEmpty())
							cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					contador++;
				}
				columna++;
				if (columna > 30) {
					fila++;
					contador = 0;
					columna = 0;
					esDefecto = true;
				}
			}



			arrTemp = servicio.getPiezasOK().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getPiezasOK().getFila()).getCell(posiciones.getPiezasOK().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			arrTemp = servicio.getPiezasRecuperadas().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getPiezasRecuperadas().getFila()).getCell(posiciones.getPiezasRecuperadas().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			// RECUENTO FINAL 5
			sheet = workbook.getSheetAt(5);

			arrTemp = servicio.getRecuentoFinal().split("@;@");
			columna = 0;
			fila = 0;
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getRecuentoFinal().getFila()+fila).getCell(posiciones.getRecuentoFinal().getColumna()+columna);
				if (columna < 4)
					cell.setCellValue(arrTemp[i]);
				else if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
				columna++;
				if (columna > 6) {
					fila++;
					columna = 0;
				}
			}




			// ESTIMACION DE HORAS Y COSTES 6
			sheet = workbook.getSheetAt(6);

			formateador = new SimpleDateFormat("dd-MMM");
			cal = Calendar.getInstance();
			cal.setTime(servicio.getFechaInicio());
			for (int i=0; i<30; i++) {
				cell = sheet.getRow(posiciones.getArrayDeFechasEstimacion().getFila()).getCell(posiciones.getArrayDeFechasEstimacion().getColumna()+i);
				cell.setCellValue(formateador.format(cal.getTime()));
				cal.add(Calendar.DATE, 1);
			}
			
			arrTemp = servicio.getArrayHoraNormal().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraNormal().getFila()).getCell(posiciones.getArrayHoraNormal().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraExtra().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraExtra().getFila()).getCell(posiciones.getArrayHoraExtra().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraSabado().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraSabado().getFila()).getCell(posiciones.getArrayHoraSabado().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraFestivo().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraFestivo().getFila()).getCell(posiciones.getArrayHoraFestivo().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraNocturna().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraNocturna().getFila()).getCell(posiciones.getArrayHoraNocturna().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraEspecialistaNormal().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNormal().getFila()).getCell(posiciones.getArrayHoraEspecialistaNormal().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraEspecialistaFestiva().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaFestivo().getFila()).getCell(posiciones.getArrayHoraEspecialistaFestivo().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraEspecialistaNocturna().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNocturna().getFila()).getCell(posiciones.getArrayHoraEspecialistaNocturna().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraCoordinacion().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraCoordinacion().getFila()).getCell(posiciones.getArrayHoraCoordinacion().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayHoraAdministracion().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraAdministracion().getFila()).getCell(posiciones.getArrayHoraAdministracion().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayGastosLogisticos().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayGastosLogisticos().getFila()).getCell(posiciones.getArrayGastosLogisticos().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}


			arrTemp = servicio.getArrayOtros1().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayOtros1().getFila()).getCell(posiciones.getArrayOtros1().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}

			arrTemp = servicio.getArrayOtros2().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayOtros2().getFila()).getCell(posiciones.getArrayOtros2().getColumna()+i);
				if (!arrTemp[i].isEmpty())
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}

			cell = sheet.getRow(posiciones.getTotalImporteTiempoInvertido().getFila()).getCell(posiciones.getTotalImporteTiempoInvertido().getColumna());
			String formula = "AG3*" + cliente.getHora_normal() + "+AG4*" + cliente.getHora_extra() + "+AG5*" + cliente.getHora_sabado() + "+AG6*" + cliente.getHora_festiva() + "+AG7*" + cliente.getHora_nocturna() 
			+ "+AG8*" + cliente.getHora_especialista_normal() + "+AG9*" + cliente.getHora_especialista_extra() + "+AG10*" + cliente.getHora_especialista_sabado() + "+AG11*" + cliente.getHora_especialista_festiva() 
			+ "+AG12*" + cliente.getHora_especialista_nocturna() + "+AG13*" + cliente.getHora_coordinacion() + "+AG14*" + cliente.getHora_administracion() + "+AG15*" + cliente.getGastos_logisticos() + "+AG16+AG17";
			cell.setCellFormula(formula);


			if (!servicio.isRetrabajos()) {
				workbook.removeSheetAt(9);
			}
			else {

				// GAMA DE RETRABAJOS 9
				sheet = workbook.getSheetAt(9);

				cell = sheet.getRow(posiciones.getRealizadoPorRetrabajos().getFila()).getCell(posiciones.getRealizadoPorRetrabajos().getColumna());
				cell.setCellValue(servicio.getRealizadoPorRetrabajos());

				cell = sheet.getRow(posiciones.getFechaRetrabajos().getFila()).getCell(posiciones.getFechaRetrabajos().getColumna());
				if (servicio.getFechaRetrabajos() != null)
					cell.setCellValue(servicio.getFechaRetrabajos());

				cell = sheet.getRow(posiciones.getFechaLiberacionRetrabajos().getFila()).getCell(posiciones.getFechaLiberacionRetrabajos().getColumna());
				if (servicio.getFechaLiberacionRetrabajos() != null)
					cell.setCellValue(servicio.getFechaLiberacionRetrabajos());

				cell = sheet.getRow(posiciones.getNumReclamacionRetrabajos().getFila()).getCell(posiciones.getNumReclamacionRetrabajos().getColumna());
				cell.setCellValue(servicio.getNumReclamacionRetrabajos());

				cell = sheet.getRow(posiciones.getFechaReclamacionRetrabajos().getFila()).getCell(posiciones.getFechaReclamacionRetrabajos().getColumna());
				if (servicio.getFechaReclamacionRetrabajos() != null)
					cell.setCellValue(servicio.getFechaReclamacionRetrabajos());

				cell = sheet.getRow(posiciones.getReferenciaPiezaRetrabajos().getFila()).getCell(posiciones.getReferenciaPiezaRetrabajos().getColumna());
				cell.setCellValue(servicio.getReferenciaPiezaRetrabajos());

				cell = sheet.getRow(posiciones.getFechaComienzoRetrabajos().getFila()).getCell(posiciones.getFechaComienzoRetrabajos().getColumna());
				if (servicio.getFechaComienzoRetrabajos() != null)
					cell.setCellValue(servicio.getFechaComienzoRetrabajos());

				cell = sheet.getRow(posiciones.getTiempoRetrabajos().getFila()).getCell(posiciones.getTiempoRetrabajos().getColumna());
				cell.setCellValue(servicio.getTiempoRetrabajos());

				cell = sheet.getRow(posiciones.getClienteRetrabajos().getFila()).getCell(posiciones.getClienteRetrabajos().getColumna());
				cell.setCellValue(servicio.getClienteRetrabajos());

				arrTemp = servicio.getFirmasRetrabajos().split("@;@");
				columna = 0;
				fila = 0;
				for (int i=0; i<arrTemp.length; i++) {
					if (columna == 0) {
						cell = sheet.getRow(posiciones.getFirmasRetrabajosNombres().getFila()+fila).getCell(posiciones.getFirmasRetrabajosNombres().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					else {
						cell = sheet.getRow(posiciones.getFirmasRetrabajosFechas().getFila()+fila).getCell(posiciones.getFirmasRetrabajosFechas().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					columna++;
					if (columna > 1) {
						columna = 0;
						fila++;
					}
				}

				if (!servicio.getImagenRetrabajos().isEmpty()) {
					String destino = "img/retrabajos"+servicio.getNumAccion()+servicio.getImagenRetrabajos().substring(servicio.getImagenRetrabajos().length()-4);
					descargarImagen(servicio.getImagenRetrabajos(), destino);
					insertarImagen(workbook, sheet, destino.substring(0, destino.length()-4), destino.substring(destino.length()-3), posiciones.getImagenRetrabajos(), 750, 640, 0);
				}

			}


			if (!servicio.isFormacion()) {
				workbook.removeSheetAt(8);
			}
			else {

				// FORMACION PERSONAL 8
				sheet = workbook.getSheetAt(8);

				cell = sheet.getRow(posiciones.getRealizadoPorPersonal().getFila()).getCell(posiciones.getRealizadoPorPersonal().getColumna());
				cell.setCellValue(servicio.getRealizadoPorPersonal());

				cell = sheet.getRow(posiciones.getFechaPersonal().getFila()).getCell(posiciones.getFechaPersonal().getColumna());
				if (servicio.getFechaPersonal() != null)
					cell.setCellValue(servicio.getFechaPersonal());

				cell = sheet.getRow(posiciones.getClientePersonal().getFila()).getCell(posiciones.getClientePersonal().getColumna());
				cell.setCellValue(servicio.getClientePersonal());

				cell = sheet.getRow(posiciones.getPiezaPersonal().getFila()).getCell(posiciones.getPiezaPersonal().getColumna());
				cell.setCellValue(servicio.getPiezaPersonal());

				cell = sheet.getRow(posiciones.getReferenciaPersonal().getFila()).getCell(posiciones.getReferenciaPersonal().getColumna());
				cell.setCellValue(servicio.getReferenciaPersonal());

				arrTemp = servicio.getFirmasPersonal().split("@;@");
				columna = 0;
				fila = 0;
				for (int i=0; i<arrTemp.length; i++) {
					if (columna == 0) {
						cell = sheet.getRow(posiciones.getFirmasPersonalNombres().getFila()+fila).getCell(posiciones.getFirmasPersonalNombres().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					else {
						cell = sheet.getRow(posiciones.getFirmasPersonalFechas().getFila()+fila).getCell(posiciones.getFirmasPersonalFechas().getColumna());
						cell.setCellValue(arrTemp[i]);
					}
					columna++;
					if (columna > 1) {
						columna = 0;
						fila++;
					}
				}

				if (!servicio.getImagenPersonal().isEmpty()) {
					String destino = "img/personal"+servicio.getNumAccion()+servicio.getImagenPersonal().substring(servicio.getImagenPersonal().length()-4);
					descargarImagen(servicio.getImagenPersonal(), destino);
					insertarImagen(workbook, sheet, destino.substring(0, destino.length()-4), destino.substring(destino.length()-3), posiciones.getImagenPersonal(), 750, 640, 0);
				}

			}


			XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAllFormulaCells(workbook);

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File("excel/"+servicio.getNumAccion()+".xlsm"));
			workbook.write(outFile);
			outFile.close();
			File carpeta = new File("pdf/" + servicio.getNumAccion().replace("-", "") );
			carpeta.mkdir();
			Files.copy(new File("excel/"+servicio.getNumAccion()+".xlsm").toPath(), new File("pdf/" +  servicio.getNumAccion().replace("-", "") + "/" + servicio.getNumAccion().replace("-", "") + ".xlsm").toPath(), StandardCopyOption.REPLACE_EXISTING);
			workbook.close();	 

		} catch (Exception e) {
			e.printStackTrace();
		}

		crearCarpetas(servicio.getNombreCarpeta());
		subirExcel(servicio.getNombreCarpeta(), servicio.getNumAccion());
		subirPdfs(servicio.getNombreCarpeta(), servicio.getNumAccion().replace("-", ""), servicio.isFormacion(), servicio.isRetrabajos());

		return true;
	}

	public void subirPdfs(String nombreCarpeta, String numAccion, boolean formacion, boolean retrabajos) {

		File file2 = new File( "pdf/" + numAccion + "/" + numAccion + ".xlsm");

		ArrayList<String> lPdfs = new ArrayList<String>();
		lPdfs.add("pdf/" + numAccion + "/estimacion.pdf");
		lPdfs.add("pdf/" + numAccion + "/grafico.pdf");
		lPdfs.add("pdf/" + numAccion + "/informacion.pdf");
		lPdfs.add("pdf/" + numAccion + "/instruccion.pdf");
		lPdfs.add("pdf/" + numAccion + "/pedido.pdf");
		lPdfs.add("pdf/" + numAccion + "/recuento.pdf");
		lPdfs.add("pdf/" + numAccion + "/validacion.pdf");
		lPdfs.add("pdf/" + numAccion + "/valoracion.pdf");

		callExcelMacro(file2, "!cpa.estimacion");
		callExcelMacro(file2, "!cpa.grafico");
		callExcelMacro(file2, "!cpa.informacion");
		callExcelMacro(file2, "!cpa.instruccion");
		callExcelMacro(file2, "!cpa.pedido");
		callExcelMacro(file2, "!cpa.recuento");
		callExcelMacro(file2, "!cpa.validacion");
		callExcelMacro(file2, "!cpa.valoracion");

		if (formacion) {
			callExcelMacro(file2, "!cpa.personal");
			lPdfs.add("pdf/" + numAccion + "/personal.pdf");
		}
		if (retrabajos) {
			callExcelMacro(file2, "!cpa.retrabajos");
			lPdfs.add("pdf/" + numAccion + "/retrabajos.pdf");
		}

		ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			for (int i=0; i < lPdfs.size(); i++) {
				// APPROACH #1: uploads first file using an InputStream
				File ficheroLocal = new File(lPdfs.get(i));

				String firstRemoteFile = nombreCarpeta+"/pdf"+lPdfs.get(i).substring(lPdfs.get(i).lastIndexOf("/"));
				InputStream inputStream = new FileInputStream(ficheroLocal);

				ftpClient.storeFile(firstRemoteFile, inputStream);
				inputStream.close();
			}

			File carpeta = new File("pdf/" + numAccion);
			delete(carpeta);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public void delete(File file)
			throws IOException{

		if(file.isDirectory()){

			//directory is empty, then delete it
			if(file.list().length==0){

				file.delete();

			}else{

				//list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					//construct the file structure
					File fileDelete = new File(file, temp);

					//recursive delete
					delete(fileDelete);
				}

				//check the directory again, if empty then delete it
				if(file.list().length==0){
					file.delete();
				}
			}

		}else{
			//if file, then delete it
			file.delete();
		}
	}

	public void subirExcel(String nombreCarpeta, String numAccion) {

		ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
			File ficheroLocal = new File("excel/"+numAccion+".xlsm");

			String firstRemoteFile = nombreCarpeta+"/excel/"+numAccion+".xlsm";
			InputStream inputStream = new FileInputStream(ficheroLocal);

			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (done) {
				ficheroLocal.delete();
			}

			inputStream.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void crearCarpetas(String nombreCarpeta) {

		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.makeDirectory(nombreCarpeta);
			ftpClient.makeDirectory(nombreCarpeta+"/img");
			ftpClient.makeDirectory(nombreCarpeta+"/pdf");
			ftpClient.makeDirectory(nombreCarpeta+"/excel");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public boolean renombrarCarpeta(String nombreCarpetaVieja, String nombreCarpeta)  {


		boolean todook = false;

		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			todook = ftpClient.rename(nombreCarpetaVieja, nombreCarpeta);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return todook;
	}

	public void callExcelMacro(File file, String macroName) {
		ComThread.InitSTA();
		final ActiveXComponent excel = new ActiveXComponent("Excel.Application");

		try {
			// This will open the excel if the property is set to true
			// excel.setProperty("Visible", new Variant(true));

			final Dispatch workbooks = excel.getProperty("Workbooks")
					.toDispatch();
			final Dispatch workBook = Dispatch.call(workbooks, "Open",
					file.getAbsolutePath()).toDispatch();

			// Calls the macro
			final Variant result = Dispatch.call(excel, "Run",
					new Variant(file.getName() + macroName));

			// Saves and closes
			Dispatch.call(workBook, "Save");

			com.jacob.com.Variant f = new com.jacob.com.Variant(true);
			Dispatch.call(workBook, "Close", f);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			excel.invoke("Quit", new Variant[0]);
			ComThread.Release();
		}
	}

	public void subirImagen(String imagen, String nombreCarpeta, int pestanya, String extension) {

		ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
			File ficheroLocal = new File(imagen);

			String firstRemoteFile = nombreCarpeta+"/img/ordendepedido" + extension;
			if (pestanya == 1) {
				firstRemoteFile = nombreCarpeta+"/img/personal" + extension;
			} else if (pestanya == 2) {
				firstRemoteFile = nombreCarpeta+"/img/retrabajos" + extension;
			} else if (pestanya == 9) {
				firstRemoteFile = nombreCarpeta+"/img/firmaValidacion" + extension;
			}
			System.out.println(firstRemoteFile);
			InputStream inputStream = new FileInputStream(ficheroLocal);

			ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void subirImagenInstruccion(String imagen, String nombreCarpeta, String nombreImagen) {

		ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
			File ficheroLocal = new File(imagen);

			String firstRemoteFile = nombreCarpeta+"/img/" + nombreImagen;

			InputStream inputStream = new FileInputStream(ficheroLocal);

			ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void borrarImagen(String url) {

		ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			ftpClient.deleteFile(url);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
