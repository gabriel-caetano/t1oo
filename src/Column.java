public class Column {
    private String name;
    private String type;//Mudar para Enum
    private Integer size;

    public Column(String name, String type, Integer size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
