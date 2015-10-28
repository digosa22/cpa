import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class prueba {
	
	public static void main(String[] argv) {
		 
//		System.out.println("-------- MySQL JDBC Connection Testing ------------");
//	 
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Where is your MySQL JDBC Driver?");
//			e.printStackTrace();
//			return;
//		}
//	 
//		System.out.println("MySQL JDBC Driver Registered!");
//		Connection connection = null;
//	 
//		try {
//			connection = DriverManager
//			.getConnection("jdbc:mysql://localhost:3306/cpa_db","root", "admin");
//	 
//		} catch (SQLException e) {
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			return;
//		}
//	 
//		if (connection != null) {
//			System.out.println("You made it, take control your database now!");
//		} else {
//			System.out.println("Failed to make connection!");
//		}
//				
//			
//			
//		try {
//			Statement statement = connection.createStatement();
//			String sql = "SELECT * FROM datos_plantilla";
//			ResultSet rs = statement.executeQuery(sql);
//			
//			while (rs.next()) {
//				System.out.println(rs.getString("id") + "  -  " + rs.getString("posicion"));
//			}		
//			
//			statement.close();
//			connection.close();
//			
//			} catch(Exception ex) {
//				JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().getName(),
//						JOptionPane.ERROR_MESSAGE);
//			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		try {
			FileInputStream file = new FileInputStream(new File("plantilla/plantilla.xlsm"));

			
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(1);

			XSSFCell cell = null;

			cell = sheet.getRow(11).getCell(1);
			cell.setCellValue("paco martinez");
			cell = sheet.getRow(12).getCell(1);
			cell.setCellValue("marketing");
			cell = sheet.getRow(13).getCell(1);
			cell.setCellValue("667356456");
			cell = sheet.getRow(14).getCell(1);
			cell.setCellValue("paco.martinez@email.com");
			cell = sheet.getRow(15).getCell(1);
			cell.setCellValue("11/12/2015");

			XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateAllFormulaCells(workbook);

			file.close();

			FileOutputStream outFile =new FileOutputStream(new File("plantilla/temporal.xlsm"));
			workbook.write(outFile);
			outFile.close();

	
			
			
			
			File file2 = new File( "plantilla/temporal.xlsm");
	        String macroName = "!cpa.guardar";
	        callExcelMacro(file2, macroName);
			Macros("documento");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.validacion";
	        callExcelMacro(file2, macroName);
			Macros("hoja1");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.pedido";
	        callExcelMacro(file2, macroName);
			Macros("hoja2");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.instruccion";
	        callExcelMacro(file2, macroName);
			Macros("hoja3");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.informacion";
	        callExcelMacro(file2, macroName);
			Macros("hoja4");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.grafico";
	        callExcelMacro(file2, macroName);
			Macros("hoja5");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.recuento";
	        callExcelMacro(file2, macroName);
			Macros("hoja6");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.estimacion";
	        callExcelMacro(file2, macroName);
			Macros("hoja7");
	        
			file2 = new File( "plantilla/temporal.xlsm");
	        macroName = "!cpa.valoracion";
	        callExcelMacro(file2, macroName);
			Macros("hoja8");
	        
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
        		
        	    File afile =new File("plantilla/aeiou.pdf");
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
