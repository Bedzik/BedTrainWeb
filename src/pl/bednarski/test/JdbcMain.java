package pl.bednarski.test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcMain {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

		final String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);

		final String dbPath = "jdbc:mysql://localhost:3306/world?serverTimezone=UTC&useSSL=false";
		Connection conn = DriverManager.getConnection(dbPath, "root", "root");

		Statement statement = conn.createStatement();
		final String sqlQuery = "SELECT Name, District, Population FROM city";
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		
		String cityName = null;
		String DistrictName = null;
		int cityPopulation = 0;
		while (resultSet.next()) {
			cityName = resultSet.getString("Name");
			DistrictName = resultSet.getString("District");
			cityPopulation = resultSet.getInt("Population");
			System.out.println(cityName + " " + DistrictName + " " + cityPopulation);
		}
		
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
		if (conn != null) {
			conn.close();
		}

	}
}
