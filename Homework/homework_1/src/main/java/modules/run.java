package modules;

public class run {
    private static void main(final String[] args) {
        Greetings greetings = new Greetings();
        String greet = greetings.greets("Szabolcs", "ger");
        System.out.printf(greet);
    }
}
