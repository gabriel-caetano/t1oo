import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        Metadata.getColumnsMetadata(Metadata.getTablesMetadata());
    }
}