import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forName("org.postgresql.Driver");

                String url = "jdbc:postgresql://localhost:5432/login";
                String username = "postgres";
                String password = "Avnish@2020";
                con = DriverManager.getConnection(url, username, password);

                System.out.println("Connection done..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
