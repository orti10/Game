package ex4_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase { 

	public static void main(String[] args)
	{
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop";//?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

			Statement statement = connection.createStatement();

			//			//insert data                                                                    //file name hash
			//			String insertSQL = "INSERT INTO logs (FirstID, SecondID, ThirdID, LogTime,Point, SomeDouble)\r\n" + 
			//					"VALUES (1, 2, 3, CURRENT_TIMESTAMP, 1.1, 2.2);";
			//			statement.executeUpdate(insertSQL);

			//selsct data, someDouble represent the code of the file we run on
			// for example 2128259830 is file number #2
			String allCustomersQuery = "SELECT * FROM logs WHERE FirstID='205672538' AND someDouble=1149748017;";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);//����� ������
			System.out.println("FirstID\t\tSecondID\tThirdID\t\tLogTime\t\t\t\tPoint\t\tSomeDouble");

			while(resultSet.next()) {
				
				System.out.println(resultSet.getInt("FirstID")+"\t"+//ortal
						resultSet.getInt("SecondID")+"\t"+//tomer
						resultSet.getInt("ThirdID")+"\t"+//avichay
						resultSet.getTimestamp("LogTime")+"\t\t"+//what time we play
						resultSet.getDouble("Point")+"\t\t"+//score
						resultSet.getDouble("SomeDouble"));//info the configuration id
			}
			resultSet.close();		
			statement.close();		
			connection.close();		
		}

		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}