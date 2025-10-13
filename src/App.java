public class App {
    public static void main(String[] args) throws Exception {
        // Expressão de entrada a ser analisada
        String input = "8+5-7+9";

        // Cria o analisador e inicia o processo de parsing
        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}