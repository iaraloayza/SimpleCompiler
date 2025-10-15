public class App {
    public static void main(String[] args) {

        // Exemplo 1 - execução direta de comandos
        /*
        String input = """
            push 10
            push 20
            add
            pop a
            push 45
            push a
            sub
            print
            """;
        */

        // Exemplo 2 - código em linguagem simples
        String input = """
            let a = 42 + 2;
            let b = 15 + 3;
            print a + b;
            """;

        Parser p = new Parser(input.getBytes());
        p.parse();

        Interpretador i = new Interpretador(p.output());
        i.run();
    }
}