import java.sql.*;

import javax.swing.JOptionPane;

public class prueba {
	
	public static void main(String[] argv) {
		 
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
	 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	 
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/cpa_db","root", "admin");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		
/*		
		int result = 0;
		try {
//			Statement statement = connection.createStatement();
			
			PreparedStatement pState = connection.prepareStatement("REPLACE INTO prueba" + "(nombre, foto) VALUES(?, ?)");
			pState.setString(1, "juanjose");
			FileInputStream fis = new FileInputStream(new File("D:\\Descargas\\fdfdgfd.JPG"));
			pState.setBinaryStream(2, fis);
			
//			String sql = "INSERT INTO prueba (nombre, foto) VALUES ('xabi', )";
//			result = statement.executeUpdate(sql);
			result = pState.executeUpdate();
//			statement.close();
			InputStream is = null;ImageIcon ii = null;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select foto from prueba where nombre = 'diego'");
			if (rs.next()) {
	            is = rs.getBinaryStream(1);
	            if (is != null) {

	                BufferedImage bi = ImageIO.read(is);
	                ImageIO.write(bi, "jpg", new File("BIEN.JPG"));
	                System.out.println("DE PUTA MADRE!!!");
	                
	            }

	        }
			
			
			
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().getName(),
						JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(result);*/
			
			
			
			
			
			
			
			
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM datos_plantilla";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(rs.getString("id") + "  -  " + rs.getString("posicion"));
			}		
			
			statement.close();
			connection.close();
			
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getClass().getName(),
						JOptionPane.ERROR_MESSAGE);
			}
	  }

}
