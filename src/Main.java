import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Metadata metadata = new Metadata();
        CrudGenerator generator =  new CrudGenerator();

        for(Table table : metadata.lstTables) {
            System.out.println(generator.createClassTemplate(table));
            System.out.print("==================================\n");
            System.out.println(generator.createInterfaceDAOTemplate(table));
            System.out.print("==================================\n");
            System.out.println(generator.createDAOTemplate(table));
            System.out.print("==================================");
        }

    }
}