package modules;

public class Greetings {

    private final String name;

    private final String language;

    public Greetings(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    private String greets(String name,
                          String language) {

        if (language.isEmpty()) {
            language = "hu";
        }

    switch (language.toLowerCase()) {
        case "hu":
            language = "Szia";
            break;
        case "en":
            language = "Hello";
            break;
        case "ger":
            language = "tschüss";
            break;
        default:
            System.out.println("Ismeretlen nyelv!");
            language = "szia";
    }

     return language + " " + name + "!";
    }
}
