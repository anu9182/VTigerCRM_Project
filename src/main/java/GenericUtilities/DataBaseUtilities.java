package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
/**
 * @author Anusha This is a reusable class for database methods
 */
public class DataBaseUtilities {
	public Connection con;
	/**
	 * This is method is used for connecting the database
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Connection getDatabaseConnection() throws SQLException {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "anusha");
		return con;
	}
	/**
	 * This method is used for fetching the data from database
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	
	public ResultSet fetchDataFromDataBase(String query) throws SQLException {
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(query);
		return result;
	}
	/**
	 * This method is used for updating the data from database
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	
	public int updateDataToDatabase(String query) throws SQLException {
		Statement stat = con.createStatement();
		int result = stat.executeUpdate(query);
		return result;
	}
	/**
	 * this method is used for closing the database
	 * @throws SQLException
	 */
	public void closeDatabaseConnection() throws SQLException {
		con.close();
	}

}
