package warsztatyCL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreator {

	public void createDataBase() {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword",
				"root", "coderslab")) {
			Statement statement = con.createStatement();
			@SuppressWarnings("unused")
			int Result = statement.executeUpdate("CREATE DATABASE warsztatyCL");

			System.out.println("Stworzono bazę danych");

		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	
	
}
