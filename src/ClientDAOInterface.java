import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAOInterface {
    public void createClient(Client client) throws SQLException;

    public Client getClient(int id);

    public void updateClient(Client client);

    public void deleteClient(int id);

    public ResultSet getAllClients() throws SQLException;
}
