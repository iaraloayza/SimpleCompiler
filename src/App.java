public class App {
    public static void main(String[] args) throws Exception {
        // Expressão de entrada com números de múltiplos dígitos
        String input = "45+89-876";

        Parser parser = new Parser(input.getBytes());
        parser.parse();
    }
}