import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ClientDAO implements ClientDAOInterface {
    @Override
    public void createClient(Client client) throws SQLException {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String query = "INSERT INTO clients (name, phone, birth, created_at, updated_at) VALUES ('";
        query += client.getName() + "', '";
        query += client.getPhone() + "', '";
        query += client.getBirth() + "', '";
        query += now + "', '";
        query += now + "');";
        new Connector().executeMutation(query);
    }

    @Override
    public Client getClient(int id) {
        return null;
    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public void deleteClient(int id) {

    }

    @Override
    public ResultSet getAllClients() throws SQLException {
        String query = "SELECT * FROM clients;";
        return new Connector().executeQuery(query);
    }
}
