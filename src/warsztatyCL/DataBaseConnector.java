package warsztatyCL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/warsztatyCL?useSSL=false",
				"root", "coderslab")) {
			System.out.println("Nawiązano połączenie z bazą danych...");

		} catch (SQLException e) {
			System.err.println("Nieudana próba nawiązania połączenia z bazą danych...");
			e.printStackTrace();
		}
		;
	}
}
