import com.sun.org.apache.xml.internal.utils.WrongParserException;

public class Parser {
    private String className = "-c";
    private String addField = "-f";
    private String getters = "-g";
    private String setters = "-s";

    private String splitter = ":";

    public void run() {
        String pies = "-cPies -fint:DlugoscOgona:55 -fString:Imie -g -s";
        Class klasa;

        try {
            klasa = parser(pies);
            System.out.println(klasa.generateClass());
        } catch (NoClassStringException e) {
            e.printStackTrace();
        }


    }

    public Class parser(String text) throws NoClassStringException {
        String[] args = text.split(" ");
        Class newClass = new Class();

        for (String s : args) {
            if (s.contains(className)) {
                newClass.nazwa = s.substring(2);
            } else if (s.contains(addField)) {
                Field f = new Field();
                String[] field = s.split(splitter);
                if (field[0].contains("int"))
                    f.type = "int";
                else if (field[0].contains("String"))
                    f.type = "String";
                else if (field[0].contains("double"))
                    f.type = "double";
                f.name = field[1];
                if (field.length == 3) {
                    try {
                        if (f.type.equals("int")) {
                            Integer.parseInt(field[2]);
                        }
                        if (f.type.equals("double")) {
                            Double.parseDouble(field[2]);
                        }
                        f.defValue = field[2];
                    } catch (NumberFormatException e) {
                        System.out.println("<ERROR> CANNOT PARSE! DEF VALUE NOT ADDED! <ERROR>");
                    }
                }
                newClass.fields.add(f);
            } else if (s.contains(getters)) {
                newClass.hasGetters=true;
            } else if (s.contains(setters)) {
                newClass.hasSetters=true;
            }

        }
        if(newClass.nazwa==null)
            throw new NoClassStringException("No class name");
        return newClass;
    }
}
