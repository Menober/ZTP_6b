import java.util.ArrayList;

public class Class {
    public ArrayList<Field> fields = new ArrayList<Field>();
    public String nazwa;
    boolean hasSetters;
    boolean hasGetters;

    public String generateClass() {
        String result = "";
        result += "public class " + nazwa + " {\n\r";
        for (Field f : fields) {
            if (f.defValue == null)
                result += "\tprivate " + f.type + " " + f.name + ";\n\r";
            else {
                if (f.type.equals("String"))
                    result += "\tprivate " + f.type + " " + f.name + " = \"" + f.defValue + "\";\n\r";
                else
                    result += "\tprivate " + f.type + " " + f.name + " = " + f.defValue + ";\n\r";
            }
        }

        if (hasGetters) {
            for (Field f : fields) {
                result += "\tpublic " + f.type + " get" + f.name + "(){ return " + f.name + ";}\n\r";
            }
        }
        if (hasSetters) {
            for (Field f : fields) {
                result += "\tpublic void set" + f.name + "(" + f.type + " " + f.name + "){ this." + f.name + " = " + f.name + "; }\n\r";
            }
        }
        result += "}";
        return result;
    }
}
