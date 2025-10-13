public class Parser {
    private Scanner scanner; // analisador léxico

    public Parser(byte[] input) {
        this.scanner = new Scanner(input);
    }

    // expr -> number oper
    void expr() {
        number();
        oper();
    }

    // number -> [0-9]+
    void number() {
        if (scanner.peek() == '\0') throw new Error("syntax error");
        String num = scanner.getNumber(); // obtém número completo
        System.out.println("push " + num);
    }

    // oper -> + number oper | - number oper | ε
    void oper() {
        char token = scanner.peek();
        if (token == '+') {
            scanner.advance();
            number();
            System.out.println("add");
            oper();
        } else if (token == '-') {
            scanner.advance();
            number();
            System.out.println("sub");
            oper();
        }
    }

    public void parse() {
        expr();
    }
}
