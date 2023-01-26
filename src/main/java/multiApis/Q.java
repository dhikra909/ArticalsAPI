package multiApis;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Q {
	public static void q1() {
		String url = "jdbc:mysql://localhost:3306/my_Dataapi";
		// Username and password to access DB
		// Custom initialization
		String user = "root";
		String pass = "root";
		Scanner scanner = new Scanner(System.in);
		String status = "OK";
		String copyright = "Copyright (c) 2023 The New York Times Company.  All Rights Reserved.";
		Integer num_results = 20;

		String q1 = "SELECT Section_id, COUNT(*) as count" + " FROM articles where Section_id !=0  GROUP BY section_id "
				+ "ORDER BY count DESC Limit 5;";
		System.out.println(q1);

		Connection con1 = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);
			// Reference to connection interface
			con1 = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement st = con1.createStatement();
			ResultSet resultSet = st.executeQuery(q1);
			// System.out.println(resultSet);
			System.out.println("section_id" + "    " + "count");
			while (resultSet.next()) {
				Integer section_id = resultSet.getInt("section_id");
				Integer count = resultSet.getInt("count");

				System.out.println(section_id + "		" + count);
			}
		}
		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}

	}
}
