import java.util.HashMap;
import java.util.Map;

public class Scanner {
    private byte[] input;
    private int current = 0;

    // Palavras-chave reconhecidas
    private static final Map<String, TokenType> keywords;
    static {
        keywords = new HashMap<>();
        keywords.put("let", TokenType.LET);
    }

    public Scanner(byte[] input) {
        this.input = input;
    }

    private boolean isAtEnd() {
        return current >= input.length;
    }

    private char peek() {
        return isAtEnd() ? '\0' : (char) input[current];
    }

    private char advance() {
        return (char) input[current++];
    }

    private void skipWhitespace() {
        while (!isAtEnd()) {
            char ch = peek();
            if (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n')
                advance();
            else
                break;
        }
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
               c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || Character.isDigit(c);
    }

    private Token number() {
        int start = current;
        while (Character.isDigit(peek())) advance();
        String num = new String(input, start, current - start);
        return new Token(TokenType.NUMBER, num);
    }

    private Token identifier() {
        int start = current;
        while (isAlphaNumeric(peek())) advance();
        String id = new String(input, start, current - start);
        TokenType type = keywords.get(id);
        if (type == null) type = TokenType.IDENT;
        return new Token(type, id);
    }

    public Token nextToken() {
        skipWhitespace();

        if (isAtEnd()) return new Token(TokenType.EOF, "");

        char ch = peek();

        // Identificadores e palavras-chave
        if (isAlpha(ch)) return identifier();

        if (Character.isDigit(ch)) return number();

        switch (ch) {
            case '+': advance(); return new Token(TokenType.PLUS, "+");
            case '-': advance(); return new Token(TokenType.MINUS, "-");
            case '=': advance(); return new Token(TokenType.EQ, "=");
            case ';': advance(); return new Token(TokenType.SEMICOLON, ";");
            default:
                throw new Error("lexical error at " + ch);
        }
    }
}