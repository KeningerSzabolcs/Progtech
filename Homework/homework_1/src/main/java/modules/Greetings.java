package modules;

public class Greetings {

    public String Greet(String name,String language){
        if(language.isEmpty()){
            language = "hu";
        }
    switch (language.toLowerCase()){
        case "hu":
            language = "Szia";
            break;
        case "en":
            language= "Hello";
            break;
        case "ger":
            language = "tschüss";
            break;
    }

     return language + " " + name + "!";
    }
}
