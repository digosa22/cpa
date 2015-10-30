import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;
import java.sql.*;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;



public class facturasprueba {
	
	public static void main(String[] argv) {
		
		
		
		
		
		
		
		try {
			FileInputStream file = new FileInputStream(new File("facturas/factura-es.xlsm"));

			
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);

			XSSFCell cell = null;

			cell = sheet.getRow(16).getCell(1);
			cell.setCellValue("diego");
			cell = sheet.getRow(16).getCell(3);
			cell.setCellValue("molero");

			file.close();

			FileOutputStream outFile =new FileOutputStream(new File("facturas/temporal.xlsm"));
			workbook.write(outFile);
			outFile.close();

	
			
			
			
			File file2 = new File( "facturas/temporal.xlsm");
	        String macroName = "!cpa.guardar";
	        callExcelMacro(file2, macroName);
			Macros("ochoa");
	        
	        file2.delete();
	        
	        System.out.println("fin yeahhh");
	        

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
		
	}
	
	
	
	
	private static void callExcelMacro(File file, String macroName) {
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
	
	public static void Macros(String nombre) throws Exception {
		
		InputStream inStream = null;
    	OutputStream outStream = null;
        		
        	    File afile =new File("facturas/aeiou.pdf");
        	    File bfile =new File("pdfs/"+nombre+".pdf");
        		
        	    inStream = new FileInputStream(afile);
        	    outStream = new FileOutputStream(bfile);
            	
        	    byte[] buffer = new byte[1024];
        		
        	    int length;
        	    //copy the file content in bytes 
        	    while ((length = inStream.read(buffer)) > 0){
        	  
        	    	outStream.write(buffer, 0, length);
        	 
        	    }
        	 
        	    inStream.close();
        	    outStream.close();
        	    
        	    //delete the original file
        	    afile.delete();
        	    
        	    System.out.println("File is copied successful!");
	}

}
