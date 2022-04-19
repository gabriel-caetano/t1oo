import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClientDAO clientDao = new ClientDAO();
        // Client client = new Client("Fulano", "40028922", "2000-02-20");
        ResultSet res = clientDao.getAllClients();
        while (res.next()) {
            System.out.println(res.getString("name"));
        }
    }
}