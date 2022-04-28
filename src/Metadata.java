import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Metadata {
    static Connector connector = new Connector();
    static Connection connection = null;
    static DatabaseMetaData metadata = null;

    static {
        connection = connector.connect();
        try {
            metadata = connection.getMetaData();
        } catch (SQLException e) {
            System.err.println("There was an error getting the metadata: "
                    + e.getMessage());
        }
    }

    public static ArrayList getTablesMetadata() throws SQLException {
        String table[] = { "TABLE" };
        ResultSet rs = null;
        ArrayList tables = null;
        // receive the Type of the object in a String array.
        rs = metadata.getTables(null, null, null, table);
        tables = new ArrayList();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }

    public static void getColumnsMetadata(ArrayList tables)
        throws SQLException {
            ResultSet rs = null;
            // Print the columns properties of the actual table
            for (Object actualTable : tables) {
                rs = metadata.getColumns(null, null, (String)actualTable, null);
                System.out.println(String.valueOf(actualTable).toUpperCase());
                while (rs.next()) {
                    System.out.println(rs.getString("COLUMN_NAME") + " "
                            + rs.getString("TYPE_NAME") + " "
                            + rs.getString("COLUMN_SIZE"));
                }
                System.out.println("\n");
        }
    }
}
