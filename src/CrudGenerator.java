import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudGenerator {

    private Map<String, String> mapTypeConversor = new HashMap<String, String>(){
        {
            put("VARCHAR", "String");
            put("INT", "Integer");
            put("TIMESTAMP", "String");
        }
    };

    public String createClassTemplate(Table table) {
        String header = String.format("""
            public class %s {
            """, table.getName());
        String body = this.getAttributesTemplate(table.getColumns());
        String footer = """
                }
                """;
        return (header+body+footer);
    }

    public String createInterfaceDAOTemplate(Table table) {
        String nameCapitalized = Utils.capitalize(table.getName());

        return String.format("""
                import java.util.List;
                public interface %sInterfaceDAO {
                    public void add%s(%s obj);
                    public void remove%s(Integer id);
                    public void update%s(%s obj);
                    public %s get%s(Integer id);
                    public List<%s> getAll%s();
                }
                """,
                table.getName(),
                nameCapitalized, nameCapitalized,
                nameCapitalized,
                nameCapitalized, nameCapitalized,
                nameCapitalized, nameCapitalized,
                nameCapitalized, nameCapitalized
        );
    }

    public String createDAOTemplate(Table table) {
        String nameCapitalized = Utils.capitalize(table.getName());

        String header = String.format("""
                import java.util.List;
                public class %sDAO implements %sInterfaceDAO {
                """, nameCapitalized, table.getName());
        String body = this.getDAOMethods(table);
        String footer = """
                }
                """;
        return (header + body + footer);
    }

    private String getAttributesTemplate(List<Column> lstColumns) {
        String attributes = "";

        for(Column column : lstColumns) {
            attributes += createAttribuite(column);
            attributes += createGet(column);
            attributes += createSet(column);
        }

        return attributes;
    }

    private String createAttribuite(Column column) {
        return String.format("""
                    private %s %s;
                """, this.mapTypeConversor.get(column.getType().toUpperCase()), column.getName());
    }

    private String createGet(Column column) {
        return String.format("""
                    public %s get%s(){return this.%s;}
                """, this.mapTypeConversor.get(column.getType().toUpperCase()), Utils.capitalize(column.getName()),
                column.getName()
                );
    }

    private String createSet(Column column) {
        return String.format("""
                    public void set%s(%s par){this.%s = par;}
                    
                """, Utils.capitalize(column.getName()), this.mapTypeConversor.get(column.getType().toUpperCase()),
                column.getName());
    }

    private String createAddItem(Table table) {
        String nameCapitalized = Utils.capitalize(table.getName());

        return String.format("""
                    @Override
                    public void add%s(%s obj) {insert obj;}
                """, nameCapitalized, nameCapitalized);
    }

    private String createRemoveItem(Table table) {
        return String.format("""
                    @Override
                    public void remove%s(Integer id){remove Id;}
                """, Utils.capitalize(table.getName()));
    }

    private String createUpdateItem(Table table) {
        String nameCapitalized = Utils.capitalize(table.getName());

        return String.format("""
                    @Override
                    public void update%s(%s obj) {update obj;}
                """, nameCapitalized, nameCapitalized);
    }

    private String getDAOMethods(Table table) {
        return createAddItem(table) + createRemoveItem(table) + createUpdateItem(table);
    }
}
