import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:12346/t1oo";
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "123456789");
        System.out.println("Connecting database...");
        try (Connection connection = DriverManager.getConnection(url, info)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.out.print(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}