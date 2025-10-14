class Parser {

    private Scanner scan;
    private Token currentToken;

    Parser(byte[] input) {
        scan = new Scanner(input);
        nextToken();
    }

    private void nextToken() {
        currentToken = scan.nextToken();
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        } else {
            throw new Error("syntax error");
        }
    }

    // expr -> number oper
    void expr() {
        number();
        oper();
    }

    // oper -> + number oper | - number oper | ε
    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            number();
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            number();
            System.out.println("sub");
            oper();
        }
    }

    // number -> [0-9]+
    void number() {
        System.out.println("push " + currentToken.lexeme);
        match(TokenType.NUMBER);
    }

    void parse() {
        expr();
        System.out.println("Parsing concluído.");
    }

    public static void main(String[] args) {
        String input = "89 +508 -7+99";
        Parser p = new Parser(input.getBytes());
        p.parse();
    }
}