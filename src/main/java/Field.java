public class Field {
    public String name;
    public String type;
    public String defValue;

    public String generateField() {
        if (defValue != null)
            return "private " + type + " " + name + " = " + defValue + ";";
        else
            return "private " + type + " " + name + ";";
    }
}
