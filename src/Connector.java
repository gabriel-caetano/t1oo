import java.sql.*;
import java.util.Properties;

public class Connector {
    public Connection connect() {
        String url = "jdbc:mysql://0.0.0.0:1234/t1oo";
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "1234");
        System.out.println("Connecting database...");
        try {
            System.out.println("Database connected!");
            return DriverManager.getConnection(url, info);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void executeMutation(String query) throws SQLException {
        Connection connection = this.connect();
        connection.prepareStatement(query).execute();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = this.connect();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
