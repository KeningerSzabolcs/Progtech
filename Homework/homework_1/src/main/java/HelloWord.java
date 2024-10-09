import java.util.Scanner;

public class HelloWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add meg a neved:");

        String name = scanner.nextLine();

        System.out.println("Add meg a nemzetiseged (hu/en/ger):");
        String language = scanner.nextLine();

        if (language.isEmpty()) {
            language = "hu";
        }

        switch (language.toLowerCase()){
            case "hu":
                System.out.println("Hello " + name + "!");
                break;
            case "en":
                System.out.println("Hello " + name + "!");
                break;
            case "ger":
                System.out.println("Hallo " + name + "!");
                break;

            default:
                System.out.println("Ismeretlen nyelv!");
                System.out.println("szia " + name + "!");
    }
        scanner.close();
}}
