class Scanner {
    private byte[] input;
    private int index;

    Scanner(byte[] input) {
        this.input = input;
        this.index = 0;
    }

    private char peek() {
        return (index < input.length) ? (char) input[index] : '\0';
    }

    private void advance() {
        index++;
    }

    // ignora espaços e quebras de linha
    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }

    public Token nextToken() {
        skipWhitespace(); // evita erro com espaços
        char ch = peek();

        if (Character.isDigit(ch)) {
            StringBuilder num = new StringBuilder();
            while (Character.isDigit(peek())) {
                num.append(peek());
                advance();
            }
            return new Token(TokenType.NUMBER, num.toString());
        }

        if (ch == '+') {
            advance();
            return new Token(TokenType.PLUS, "+");
        }

        if (ch == '-') {
            advance();
            return new Token(TokenType.MINUS, "-");
        }

        if (ch == '\0') {
            return new Token(TokenType.EOF, "");
        }

        throw new Error("Caractere inválido: " + ch);
    }
}