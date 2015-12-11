package Main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Main.Posicion;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;


public class Utilidades {

	private static final int IMG_WIDTH = 50;
	private static final int IMG_HEIGHT = 50;
	
//	private String server = "lhcp1017.webapps.net";
//	private int port = 21;
//    private String user = "ireguatek@clientes-cpavitoria06.com";
//    private String pass = "Ireguatekcpa1";
//    private FTPClient ftpClient = new FTPClient();

	public void insertarImagen( XSSFWorkbook wb, XSSFSheet sheet, String imagen, Posicion posicion, int ancho, int alto, int filaSuma) {

		try {

			File i = new File("img/descarga_prueba.jpg");

			BufferedImage originalImage = ImageIO.read(new File(imagen));
			int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg, "jpg", i); 

			//FileInputStream obtains input bytes from the image file
			InputStream inputStream = new FileInputStream("img/descarga_prueba.jpg");

			//Get the contents of an InputStream as a byte[].
			byte[] bytes = IOUtils.toByteArray(inputStream);
			//Adds a picture to the workbook
			int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
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
			//   System.out.println(pict.getImageDimension().height);
			//   System.out.println(pict.getImageDimension().width);
			//Reset the image to the original size
			pict.resize();

			i.delete();


		}
		catch (Exception e) {
			System.out.println(e);
		}

	}

	private BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}
	
	public void prueba(Servicio servicio) {
		try {
			FileInputStream file = new FileInputStream(new File("plantilla/plantilla.xlsm"));

			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = null;
			XSSFCell cell = null;
			Posiciones posiciones = new Posiciones();
			
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
			cell.setCellValue("Empresa cliente");
			
			cell = sheet.getRow(posiciones.getDireccionCliente().getFila()).getCell(posiciones.getDireccionCliente().getColumna());
			cell.setCellValue("getDireccionCliente");
			
			cell = sheet.getRow(posiciones.getCodigoPostalCliente().getFila()).getCell(posiciones.getCodigoPostalCliente().getColumna());
			cell.setCellValue("getCodigoPostalCliente");
			
			cell = sheet.getRow(posiciones.getPaisCliente().getFila()).getCell(posiciones.getPaisCliente().getColumna());
			cell.setCellValue("getPaisCliente");
			
			cell = sheet.getRow(posiciones.getPoblacionCliente().getFila()).getCell(posiciones.getPoblacionCliente().getColumna());
			cell.setCellValue("getPoblacionCliente");
			
			cell = sheet.getRow(posiciones.getApartadoDeCorreosCliente().getFila()).getCell(posiciones.getApartadoDeCorreosCliente().getColumna());
			cell.setCellValue("getApartadoDeCorreosCliente");
			
			cell = sheet.getRow(posiciones.getCodigoPostalCliente2().getFila()).getCell(posiciones.getCodigoPostalCliente2().getColumna());
			cell.setCellValue("getCodigoPostalCliente2");
			
			cell = sheet.getRow(posiciones.getCodigoPostalEmpresaCliente().getFila()).getCell(posiciones.getCodigoPostalEmpresaCliente().getColumna());
			cell.setCellValue("getCodigoPostalEmpresaCliente");
			
			cell = sheet.getRow(posiciones.getPersonaDeContactoCliente().getFila()).getCell(posiciones.getPersonaDeContactoCliente().getColumna());
			cell.setCellValue("getPersonaDeContactoCliente");
			
			cell = sheet.getRow(posiciones.getDepartamentoCliente().getFila()).getCell(posiciones.getDepartamentoCliente().getColumna());
			cell.setCellValue("getDepartamentoCliente");
			
			cell = sheet.getRow(posiciones.getTelefonoCliente().getFila()).getCell(posiciones.getTelefonoCliente().getColumna());
			cell.setCellValue("getTelefonoCliente");
			
			cell = sheet.getRow(posiciones.getEmailCliente().getFila()).getCell(posiciones.getEmailCliente().getColumna());
			cell.setCellValue("getEmailCliente");
			
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
			
			//TODO IMAGEN
			
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
			if (operario.startsWith("; "))
				operario = operario.substring(2);
			
			cell = sheet.getRow(posiciones.getOperario().getFila()).getCell(posiciones.getOperario().getColumna());
			cell.setCellValue(operario);
			
			
			String[] arrTemp = servicio.getAccionesIntruccion().split("@;@");
			int fila = 0;
			int columna = 0;
			String[] imagenes;
			boolean anadir = false;
			for (int i=0; i<arrTemp.length; i++) {
				
				if (i%3 == 0 || i == 0) {
					if (arrTemp[i].trim().isEmpty()) {
						anadir = false;
					}
					else {
						anadir = true;
					}
				}
				
				if (anadir){
				if (columna == 0) {
					cell = sheet.getRow(posiciones.getAccionesInstruccionDescripcionInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionDescripcionInicial().getColumna());
					cell.setCellValue(arrTemp[i]);
				} else if (columna == 1) {
//TODO					imagenes = arrTemp[i].split("#;#");
//					insertarImagen(workbook, sheet, imagenes[0], posiciones.getAccionesInstruccionOtros1Inicial(), 0, 0, fila);
//					insertarImagen(workbook, sheet, imagenes[1], posiciones.getAccionesInstruccionOtros2Inicial(), 0, 0, fila);
				} else {
					cell = sheet.getRow(posiciones.getAccionesInstruccionAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionAplicaInicial().getColumna());
					if (arrTemp[i].equalsIgnoreCase("0")) {
						cell = sheet.getRow(posiciones.getAccionesInstruccionNoAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionNoAplicaInicial().getColumna());
					}
					cell.setCellValue("X");
				}
				}
				
				columna++;
				if (columna > 2) {
					fila = fila + 3;
					if (fila == 33) {
						fila++;
					}
					columna = 0;
				}
			}
			
			// INFORMACION DE RESULTADOS 3
			
			sheet = workbook.getSheetAt(3);
			
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
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 1) {
						cell = sheet.getRow(posiciones.getDefecto2Fechas().getFila()).getCell(posiciones.getDefecto2Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 2) {
						cell = sheet.getRow(posiciones.getDefecto3Fechas().getFila()).getCell(posiciones.getDefecto3Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 3) {
						cell = sheet.getRow(posiciones.getDefecto4Fechas().getFila()).getCell(posiciones.getDefecto4Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 4) {
						cell = sheet.getRow(posiciones.getDefecto5Fechas().getFila()).getCell(posiciones.getDefecto5Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 5) {
						cell = sheet.getRow(posiciones.getDefecto6Fechas().getFila()).getCell(posiciones.getDefecto6Fechas().getColumna()+contador);
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
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			arrTemp = servicio.getPiezasRecuperadas().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getPiezasRecuperadas().getFila()).getCell(posiciones.getPiezasRecuperadas().getColumna()+i);
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
				else
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
				columna++;
				if (columna > 6) {
					fila++;
					columna = 0;
				}
			}
			
			
			
			
			// ESTIMACION DE HORAS Y COSTES 6
			sheet = workbook.getSheetAt(6);
			
			arrTemp = servicio.getArrayHoraNormal().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraNormal().getFila()).getCell(posiciones.getArrayHoraNormal().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraExtra().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraExtra().getFila()).getCell(posiciones.getArrayHoraExtra().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraSabado().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraSabado().getFila()).getCell(posiciones.getArrayHoraSabado().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraFestivo().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraFestivo().getFila()).getCell(posiciones.getArrayHoraFestivo().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraNocturna().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraNocturna().getFila()).getCell(posiciones.getArrayHoraNocturna().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraEspecialistaNormal().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNormal().getFila()).getCell(posiciones.getArrayHoraEspecialistaNormal().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraEspecialistaFestiva().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaFestivo().getFila()).getCell(posiciones.getArrayHoraEspecialistaFestivo().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraEspecialistaNocturna().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNocturna().getFila()).getCell(posiciones.getArrayHoraEspecialistaNocturna().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraCoordinacion().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraCoordinacion().getFila()).getCell(posiciones.getArrayHoraCoordinacion().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraAdministracion().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraAdministracion().getFila()).getCell(posiciones.getArrayHoraAdministracion().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayGastosLogisticos().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayGastosLogisticos().getFila()).getCell(posiciones.getArrayGastosLogisticos().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayOtros1().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayOtros1().getFila()).getCell(posiciones.getArrayOtros1().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			if (!servicio.isRetrabajos()) {
				workbook.removeSheetAt(9);
			}
			else {
				
				// GAMA DE RETRABAJOS 9
				sheet = workbook.getSheetAt(9);
				
				cell = sheet.getRow(posiciones.getRealizadoPorRetrabajos().getFila()).getCell(posiciones.getRealizadoPorRetrabajos().getColumna());
				cell.setCellValue(servicio.getRealizadoPorRetrabajos());

				cell = sheet.getRow(posiciones.getFechaRetrabajos().getFila()).getCell(posiciones.getFechaRetrabajos().getColumna());
				cell.setCellValue(servicio.getFechaRetrabajos());

				cell = sheet.getRow(posiciones.getFechaLiberacionRetrabajos().getFila()).getCell(posiciones.getFechaLiberacionRetrabajos().getColumna());
				cell.setCellValue(servicio.getFechaLiberacionRetrabajos());

				cell = sheet.getRow(posiciones.getNumReclamacionRetrabajos().getFila()).getCell(posiciones.getNumReclamacionRetrabajos().getColumna());
				cell.setCellValue(servicio.getNumReclamacionRetrabajos());

				cell = sheet.getRow(posiciones.getFechaReclamacionRetrabajos().getFila()).getCell(posiciones.getFechaReclamacionRetrabajos().getColumna());
				cell.setCellValue(servicio.getFechaReclamacionRetrabajos());

				cell = sheet.getRow(posiciones.getReferenciaPiezaRetrabajos().getFila()).getCell(posiciones.getReferenciaPiezaRetrabajos().getColumna());
				cell.setCellValue(servicio.getReferenciaPiezaRetrabajos());

				cell = sheet.getRow(posiciones.getFechaComienzoRetrabajos().getFila()).getCell(posiciones.getFechaComienzoRetrabajos().getColumna());
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
				
//				ut.insertarImagen(workbook, sheet, serv.getImagenRetrabajos(), posiciones.getImagenRetrabajos(), 0, 0, 0);	TODO
				
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
				
//				ut.insertarImagen(workbook, sheet, serv.getImagenPersonal(), posiciones.getImagenPersonal(), 0, 0, 0);	TODO
				
			}
			
			
			XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAllFormulaCells(workbook);
			
			file.close();

			FileOutputStream outFile =new FileOutputStream(new File("excel/temporal.xlsm"));
			workbook.write(outFile);
			outFile.close();
			workbook.close();
	        
	        System.out.println("fin yeahhh");
	        

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean subirNuevoExcel(Servicio servicio)  {
		
		String idioma = "es";//TODO METODO LLAMADAS
		String plantilla = "plantilla/plantilla-"+idioma+".xlsm";
		
		try {
			FileInputStream file = new FileInputStream(new File(plantilla));

			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = null;
			XSSFCell cell = null;
			Posiciones posiciones = new Posiciones();
			
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
			cell.setCellValue("Empresa cliente");
			
			cell = sheet.getRow(posiciones.getDireccionCliente().getFila()).getCell(posiciones.getDireccionCliente().getColumna());
			cell.setCellValue("getDireccionCliente");
			
			cell = sheet.getRow(posiciones.getCodigoPostalCliente().getFila()).getCell(posiciones.getCodigoPostalCliente().getColumna());
			cell.setCellValue("getCodigoPostalCliente");
			
			cell = sheet.getRow(posiciones.getPaisCliente().getFila()).getCell(posiciones.getPaisCliente().getColumna());
			cell.setCellValue("getPaisCliente");
			
			cell = sheet.getRow(posiciones.getPoblacionCliente().getFila()).getCell(posiciones.getPoblacionCliente().getColumna());
			cell.setCellValue("getPoblacionCliente");
			
			cell = sheet.getRow(posiciones.getApartadoDeCorreosCliente().getFila()).getCell(posiciones.getApartadoDeCorreosCliente().getColumna());
			cell.setCellValue("getApartadoDeCorreosCliente");
			
			cell = sheet.getRow(posiciones.getCodigoPostalCliente2().getFila()).getCell(posiciones.getCodigoPostalCliente2().getColumna());
			cell.setCellValue("getCodigoPostalCliente2");
			
			cell = sheet.getRow(posiciones.getCodigoPostalEmpresaCliente().getFila()).getCell(posiciones.getCodigoPostalEmpresaCliente().getColumna());
			cell.setCellValue("getCodigoPostalEmpresaCliente");
			
			cell = sheet.getRow(posiciones.getPersonaDeContactoCliente().getFila()).getCell(posiciones.getPersonaDeContactoCliente().getColumna());
			cell.setCellValue("getPersonaDeContactoCliente");
			
			cell = sheet.getRow(posiciones.getDepartamentoCliente().getFila()).getCell(posiciones.getDepartamentoCliente().getColumna());
			cell.setCellValue("getDepartamentoCliente");
			
			cell = sheet.getRow(posiciones.getTelefonoCliente().getFila()).getCell(posiciones.getTelefonoCliente().getColumna());
			cell.setCellValue("getTelefonoCliente");
			
			cell = sheet.getRow(posiciones.getEmailCliente().getFila()).getCell(posiciones.getEmailCliente().getColumna());
			cell.setCellValue("getEmailCliente");
			
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
			
			//TODO IMAGEN
			
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
			if (operario.startsWith("; "))
				operario = operario.substring(2);
			
			cell = sheet.getRow(posiciones.getOperario().getFila()).getCell(posiciones.getOperario().getColumna());
			cell.setCellValue(operario);
			
			
			String[] arrTemp = servicio.getAccionesIntruccion().split("@;@");
			int fila = 0;
			int columna = 0;
			String[] imagenes;
			boolean anadir = false;
			for (int i=0; i<arrTemp.length; i++) {
				
				if (i%3 == 0 || i == 0) {
					if (arrTemp[i].trim().isEmpty()) {
						anadir = false;
					}
					else {
						anadir = true;
					}
				}
				
				if (anadir){
				if (columna == 0) {
					cell = sheet.getRow(posiciones.getAccionesInstruccionDescripcionInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionDescripcionInicial().getColumna());
					cell.setCellValue(arrTemp[i]);
				} else if (columna == 1) {
//TODO					imagenes = arrTemp[i].split("#;#");
//					insertarImagen(workbook, sheet, imagenes[0], posiciones.getAccionesInstruccionOtros1Inicial(), 0, 0, fila);
//					insertarImagen(workbook, sheet, imagenes[1], posiciones.getAccionesInstruccionOtros2Inicial(), 0, 0, fila);
				} else {
					cell = sheet.getRow(posiciones.getAccionesInstruccionAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionAplicaInicial().getColumna());
					if (arrTemp[i].equalsIgnoreCase("0")) {
						cell = sheet.getRow(posiciones.getAccionesInstruccionNoAplicaInicial().getFila()+fila).getCell(posiciones.getAccionesInstruccionNoAplicaInicial().getColumna());
					}
					cell.setCellValue("X");
				}
				}
				
				columna++;
				if (columna > 2) {
					fila = fila + 3;
					if (fila == 33) {
						fila++;
					}
					columna = 0;
				}
			}
			
			// INFORMACION DE RESULTADOS 3
			
			sheet = workbook.getSheetAt(3);
			
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
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 1) {
						cell = sheet.getRow(posiciones.getDefecto2Fechas().getFila()).getCell(posiciones.getDefecto2Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 2) {
						cell = sheet.getRow(posiciones.getDefecto3Fechas().getFila()).getCell(posiciones.getDefecto3Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 3) {
						cell = sheet.getRow(posiciones.getDefecto4Fechas().getFila()).getCell(posiciones.getDefecto4Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 4) {
						cell = sheet.getRow(posiciones.getDefecto5Fechas().getFila()).getCell(posiciones.getDefecto5Fechas().getColumna()+contador);
						cell.setCellValue(Integer.parseInt(arrTemp[i]));
					}
					else if (fila == 5) {
						cell = sheet.getRow(posiciones.getDefecto6Fechas().getFila()).getCell(posiciones.getDefecto6Fechas().getColumna()+contador);
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
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			arrTemp = servicio.getPiezasRecuperadas().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getPiezasRecuperadas().getFila()).getCell(posiciones.getPiezasRecuperadas().getColumna()+i);
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
				else
					cell.setCellValue(Integer.parseInt(arrTemp[i]));
				columna++;
				if (columna > 6) {
					fila++;
					columna = 0;
				}
			}
			
			
			
			
			// ESTIMACION DE HORAS Y COSTES 6
			sheet = workbook.getSheetAt(6);
			
			arrTemp = servicio.getArrayHoraNormal().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraNormal().getFila()).getCell(posiciones.getArrayHoraNormal().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraExtra().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraExtra().getFila()).getCell(posiciones.getArrayHoraExtra().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraSabado().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraSabado().getFila()).getCell(posiciones.getArrayHoraSabado().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraFestivo().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraFestivo().getFila()).getCell(posiciones.getArrayHoraFestivo().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraNocturna().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraNocturna().getFila()).getCell(posiciones.getArrayHoraNocturna().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraEspecialistaNormal().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNormal().getFila()).getCell(posiciones.getArrayHoraEspecialistaNormal().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraEspecialistaFestiva().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaFestivo().getFila()).getCell(posiciones.getArrayHoraEspecialistaFestivo().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraEspecialistaNocturna().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraEspecialistaNocturna().getFila()).getCell(posiciones.getArrayHoraEspecialistaNocturna().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraCoordinacion().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraCoordinacion().getFila()).getCell(posiciones.getArrayHoraCoordinacion().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayHoraAdministracion().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayHoraAdministracion().getFila()).getCell(posiciones.getArrayHoraAdministracion().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayGastosLogisticos().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayGastosLogisticos().getFila()).getCell(posiciones.getArrayGastosLogisticos().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			arrTemp = servicio.getArrayOtros1().split("@;@");
			for (int i=0; i<arrTemp.length; i++) {
				cell = sheet.getRow(posiciones.getArrayOtros1().getFila()).getCell(posiciones.getArrayOtros1().getColumna()+i);
				cell.setCellValue(Integer.parseInt(arrTemp[i]));
			}
			
			
			if (!servicio.isRetrabajos()) {
				workbook.removeSheetAt(9);
			}
			else {
				
				// GAMA DE RETRABAJOS 9
				sheet = workbook.getSheetAt(9);
				
				cell = sheet.getRow(posiciones.getRealizadoPorRetrabajos().getFila()).getCell(posiciones.getRealizadoPorRetrabajos().getColumna());
				cell.setCellValue(servicio.getRealizadoPorRetrabajos());

				cell = sheet.getRow(posiciones.getFechaRetrabajos().getFila()).getCell(posiciones.getFechaRetrabajos().getColumna());
				cell.setCellValue(servicio.getFechaRetrabajos());

				cell = sheet.getRow(posiciones.getFechaLiberacionRetrabajos().getFila()).getCell(posiciones.getFechaLiberacionRetrabajos().getColumna());
				cell.setCellValue(servicio.getFechaLiberacionRetrabajos());

				cell = sheet.getRow(posiciones.getNumReclamacionRetrabajos().getFila()).getCell(posiciones.getNumReclamacionRetrabajos().getColumna());
				cell.setCellValue(servicio.getNumReclamacionRetrabajos());

				cell = sheet.getRow(posiciones.getFechaReclamacionRetrabajos().getFila()).getCell(posiciones.getFechaReclamacionRetrabajos().getColumna());
				cell.setCellValue(servicio.getFechaReclamacionRetrabajos());

				cell = sheet.getRow(posiciones.getReferenciaPiezaRetrabajos().getFila()).getCell(posiciones.getReferenciaPiezaRetrabajos().getColumna());
				cell.setCellValue(servicio.getReferenciaPiezaRetrabajos());

				cell = sheet.getRow(posiciones.getFechaComienzoRetrabajos().getFila()).getCell(posiciones.getFechaComienzoRetrabajos().getColumna());
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
				
//				ut.insertarImagen(workbook, sheet, serv.getImagenRetrabajos(), posiciones.getImagenRetrabajos(), 0, 0, 0);	TODO
				
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
				
//				ut.insertarImagen(workbook, sheet, serv.getImagenPersonal(), posiciones.getImagenPersonal(), 0, 0, 0);	TODO
				
			}
			
			
			XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAllFormulaCells(workbook);
			
			file.close();

			FileOutputStream outFile = new FileOutputStream(new File("excel/"+servicio.getNumAccion()+".xlsm"));
			workbook.write(outFile);
			outFile.close();
			workbook.close();	        

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		System.setProperty("java.net.preferIPv4Stack", "true");
//		
//		boolean todook = false;
//		
//		try {
//			 
//            ftpClient.connect(server, port);
//            ftpClient.login(user, pass);
//            ftpClient.enterLocalPassiveMode();
//            
//            todook = ftpClient.makeDirectory(servicio.getNombreCarpeta());
//            todook = ftpClient.makeDirectory(servicio.getNombreCarpeta()+"/img");
//            todook = ftpClient.makeDirectory(servicio.getNombreCarpeta()+"/pdf");
//            todook = ftpClient.makeDirectory(servicio.getNombreCarpeta()+"/excel");
//            
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            File ficheroLocal = new File("excel/"+servicio.getNumAccion()+".xlsm");
//            
//            String firstRemoteFile = servicio.getNombreCarpeta()+"/excel/"+servicio.getNumAccion()+".xlsm";
//            InputStream inputStream = new FileInputStream(ficheroLocal);
// 
//            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
//            inputStream.close();
//            if (done) {
//                System.out.println("The file is uploaded successfully.");
//            }
//           
//            inputStream.close();
// 
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
		
//        return todook;
		
//		crearCarpetas(servicio.getNombreCarpeta());
//		subirExcel();
		
		return true;
	}
	
	public void subirExcel() {
		
System.setProperty("java.net.preferIPv4Stack", "true");
    	
        String server = "lhcp1017.webapps.net";
        int port = 21;
        String user = "ireguatek@clientes-cpavitoria06.com";
        String pass = "Ireguatekcpa1";
 
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("img/cpa.jpg");
            
            String dirToCreate = "/cpa";
            ftpClient.makeDirectory(dirToCreate);
            
            String firstRemoteFile = "cpa/cpa.jpg";
            InputStream inputStream = new FileInputStream(firstLocalFile);
 
            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
           
            inputStream.close();
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
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
	
//	public void crearCarpetas(String nombreCarpeta) {
//		
//		System.setProperty("java.net.preferIPv4Stack", "true");
//		
//		try {
//			 
//            ftpClient.connect(server, port);
//            ftpClient.login(user, pass);
//            ftpClient.enterLocalPassiveMode();
//            
//            ftpClient.makeDirectory(nombreCarpeta);
//            ftpClient.makeDirectory(nombreCarpeta+"/img");
//            ftpClient.makeDirectory(nombreCarpeta+"/pdf");
//            ftpClient.makeDirectory(nombreCarpeta+"/excel");
// 
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//	}
	
//	public boolean renombrarCarpeta(String nombreCarpetaVieja, String nombreCarpeta)  {
//		
//		System.setProperty("java.net.preferIPv4Stack", "true");
//		
//		boolean todook = false;
//		
//		try {
//			 
//            ftpClient.connect(server, port);
//            ftpClient.login(user, pass);
//            ftpClient.enterLocalPassiveMode();
//            
//            todook = ftpClient.rename(nombreCarpetaVieja, nombreCarpeta);
// 
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//		
//        return todook;
//	}

}
