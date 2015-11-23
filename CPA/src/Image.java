import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
 
 
public class Image {
 
	 private static final int IMG_WIDTH = 500;
	 private static final int IMG_HEIGHT = 500;
	
 public static void main(String[] args) {
 
  try {
	  
	  File i = new File("img/descarga_prueba.jpg");
	  
	  BufferedImage originalImage = ImageIO.read(new File("img/cuadrado.jpg"));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ImageIO.write(resizeImageJpg, "jpg", i); 
 
	  XSSFWorkbook wb = new XSSFWorkbook();
	  XSSFSheet sheet = wb.createSheet("My Sample Excel");
 
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
   anchor.setCol1(5);
   anchor.setRow1(8);
 
   //Creates a picture
   Picture pict = drawing.createPicture(anchor, pictureIdx);
   System.out.println(pict.getImageDimension().height);
   System.out.println(pict.getImageDimension().width);
   //Reset the image to the original size
   pict.resize();
   
   i.delete();
 
   //Write the Excel file
   FileOutputStream fileOut = null;
   fileOut = new FileOutputStream("facturas/myFile.xlsx");
   wb.write(fileOut);
   fileOut.close();
 
  }
  catch (Exception e) {
   System.out.println(e);
  }
 
 }
 
 private static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
			
		return resizedImage;
	    }
 
}
