public class Parser {

    private Scanner scan;
    private char currentToken;

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken(); // primeiro token
    }

    // Lê o próximo token do scanner
    private void nextToken() {
        currentToken = scan.nextToken();
    }

    // Verifica se o token é o esperado
    private void match(char t) {
        if (currentToken == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    // Reconhece um número
    void digit() {
        if (Character.isDigit(currentToken)) {
            System.out.println("push " + currentToken);
            match(currentToken);
        } else {
            throw new Error("syntax error");
        }
    }

    // Reconhece operadores + e -
    void oper() {
        if (currentToken == '+') {
            match('+');
            digit();
            System.out.println("add");
            oper();
        } else if (currentToken == '-') {
            match('-');
            digit();
            System.out.println("sub");
            oper();
        }
    }

    // Expressão: número seguido por operadores
    void expr() {
        digit();
        oper();
    }

    public void parse() {
        expr();
    }
}