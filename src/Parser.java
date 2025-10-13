public class Parser {
    private byte[] input; // expressão em bytes
    private int current;  // posição atual na leitura

    public Parser(byte[] input) {
        this.input = input;
    }

    // Retorna o caractere atual
    private char peek() {
        if (current < input.length)
            return (char) input[current];
        return '\0';
    }

    // Verifica e consome o caractere esperado
    private void match(char c) {
        if (c == peek())
            current++;
        else
            throw new Error("syntax error");
    }

    // expr -> digit oper
    void expr() {
        digit();
        oper();
    }

    // digit -> [0-9]
    void digit() {
        if (Character.isDigit(peek())) {
            System.out.println("push " + peek());
            match(peek());
        } else {
            throw new Error("syntax error");
        }
    }

    // oper -> + digit oper | - digit oper | ε
    void oper() {
        if (peek() == '+') {
            match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (peek() == '-') {
            match('-');
            digit();
            System.out.println("sub");
            oper();
        }
    }

    // Inicia o processo de análise
    public void parse() {
        expr();
    }
}