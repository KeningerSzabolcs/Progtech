package modules;

public class run {
    public static void main(String[] args) {
        Greetings greetings = new Greetings();
        String greet = greetings.Greet("Szabolcs", "ger");
        System.out.printf(greet);
    }
}
