public class App {
    public static void main(String[] args) {
        String input = """
            let a = 42 + 5 - 8;
            let b = 56 + 8;
            print a + b + 6;
            """;

        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}