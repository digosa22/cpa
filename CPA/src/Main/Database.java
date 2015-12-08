package Main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is the connection to the Aliada´s database.
 * @author xabi
 * @version 1.0
 * @since 1.0
 */

public class Database {
	
	/**
     * Get the database connection.
     * @return Connection
     * @see
     * @since 1.0
     */
	
	public Connection Get_Connection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://lhcp1017.webapps.net:3306/he1hqohd_cpa";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "he1hqohd_ireguat",
					"XXX");
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
