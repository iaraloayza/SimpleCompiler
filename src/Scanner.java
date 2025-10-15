import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Scanner {
    private final byte[] input;
    private int current = 0;
    private static final Map<String, TokenType> keywords;

    // palavras-chave
    static {
        keywords = new HashMap<>();
        keywords.put("let", TokenType.LET);
        keywords.put("print", TokenType.PRINT);
    }

    public Scanner(InputStream in) {
        try {
            input = in.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAtEnd() {
        return current >= input.length;
    }

    private char advance() {
        return (char) input[current++];
    }

    private char peek() {
        return isAtEnd() ? '\0' : (char) input[current];
    }

    private void skipWhitespace() {
        while (!isAtEnd()) {
            char c = peek();
            if (c == ' ' || c == '\r' || c == '\n' || c == '\t') advance();
            else break;
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    // número
    private Token number() {
        int start = current;
        while (isDigit(peek())) advance();
        String num = new String(input, start, current - start);
        return new Token(TokenType.NUMBER, num);
    }

    // identificador ou palavra-chave
    private Token identifier() {
        int start = current;
        while (isAlphaNumeric(peek())) advance();
        String id = new String(input, start, current - start);
        TokenType type = keywords.getOrDefault(id, TokenType.ID);
        return new Token(type, id);
    }

    public Token nextToken() {
        skipWhitespace();
        if (isAtEnd()) return new Token(TokenType.EOF, "");

        char ch = advance();

        switch (ch) {
            case '+': return new Token(TokenType.PLUS, "+");
            case '-': return new Token(TokenType.MINUS, "-");
            case '=': return new Token(TokenType.ASSIGN, "=");
            case ';': return new Token(TokenType.SEMICOLON, ";");
        }

        if (isDigit(ch)) {
            current--; 
            return number();
        }

        if (isAlpha(ch)) {
            current--; 
            return identifier();
        }

        throw new Error("lexical error at " + ch);
    }
}