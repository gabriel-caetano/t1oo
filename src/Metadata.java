import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Metadata {
    Connector connector = new Connector();
    Connection connection = null;
    DatabaseMetaData metadata = null;
    List<Table> lstTables = new ArrayList<Table>();

    public Metadata() throws SQLException {
        connection = connector.connect();
        try {
            metadata = connection.getMetaData();
            this.getColumnsMetadata(this.getTablesMetadata());
        } catch (SQLException e) {
            System.err.println("There was an error getting the metadata: "
                    + e.getMessage());
        }

    }

    private ArrayList getTablesMetadata() throws SQLException {
        String table[] = { "TABLE"};
        ResultSet rs = metadata.getTables("t1oo", null, null, table);

        ArrayList tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

    private void getColumnsMetadata(ArrayList tables) throws SQLException {
            ResultSet rs = null;

            for (Object actualTable : tables) {
                Table table = new Table();
                lstTables.add(table);

                table.setName(String.valueOf(actualTable));
                rs = metadata.getColumns(null, null, (String)actualTable, null);

                while (rs.next()) {
                    table.setColumn(new Column(
                            rs.getString("COLUMN_NAME"),
                            rs.getString("TYPE_NAME"),
                            Integer.valueOf(rs.getString("COLUMN_SIZE"))
                    ));
                }
            }
    }
}
