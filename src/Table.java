import java.util.ArrayList;
import java.util.List;

public class Table {
    String name = "";
    List<Column> columns = new ArrayList<Column>();

    public void setName(String name) {
        this.name = name;
    }

    public void setColumn(Column column) {
        this.columns.add(column);
    }
}
