import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        Metadata metadata = new Metadata();

        System.out.println("oi  " +  metadata.tables);
        //Metadata.getColumnsMetadata(Metadata.getTablesMetadata());

    }
}