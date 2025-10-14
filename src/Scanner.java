public class Scanner {

    private byte[] input;
    private int current = 0;

    public Scanner(byte[] input) {
        this.input = input;
    }

    // Retorna o caractere atual
    private char peek() {
        if (current >= input.length) return '\0';
        return (char) input[current];
    }

    // Avança uma posição no input
    private void advance() {
        current++;
    }

    // Lê e forma um número com mais de um dígito
    private Token number() {
        int start = current;
        while (Character.isDigit(peek())) {
            advance();
        }
        String n = new String(input, start, current - start);
        return new Token(TokenType.NUMBER, n);
    }

    // Retorna o próximo token identificado
    public Token nextToken() {
        char ch = peek();

        if (ch == '0') {
            advance();
            return new Token(TokenType.NUMBER, Character.toString(ch));
        } else if (Character.isDigit(ch))
            return number();

        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '\0':
                return new Token(TokenType.EOF, "EOF");
            default:
                throw new Error("lexical error at " + ch);
        }
    }
}
