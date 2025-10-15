import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Scanner scan;
    private Token currentToken;
    private List<String> code = new ArrayList<>(); // guarda comandos gerados

    public Parser(byte[] input) {
        InputStream stream = new ByteArrayInputStream(input);
        this.scan = new Scanner(stream);
        this.currentToken = scan.nextToken();
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

    // statements -> statement*
    public void parse() {
        statements();
    }

    void statements() {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    // statement -> printStatement | letStatement
    void statement() {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error");
        }
    }

    // printStatement -> 'print' expr ';'
    void printStatement() {
        match(TokenType.PRINT);
        expr();
        code.add("print");
        match(TokenType.SEMICOLON);
    }

    // letStatement -> 'let' ID '=' expr ';'
    void letStatement() {
        match(TokenType.LET);
        String varName = currentToken.lexeme;
        match(TokenType.ID);
        match(TokenType.ASSIGN);
        expr();
        code.add("pop " + varName);
        match(TokenType.SEMICOLON);
    }

    // expr -> number oper
    void expr() {
        number();
        oper();
    }

    // oper -> + number oper | - number oper | ϵ
    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            number();
            code.add("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            number();
            code.add("sub");
            oper();
        }
    }

    // number -> [0-9]+ | ID
    void number() {
        if (currentToken.type == TokenType.NUMBER) {
            code.add("push " + currentToken.lexeme);
            match(TokenType.NUMBER);
        } else if (currentToken.type == TokenType.ID) {
            code.add("push " + currentToken.lexeme);
            match(TokenType.ID);
        } else {
            throw new Error("syntax error");
        }
    }

    // retorna código gerado como string
    public String output() {
        return String.join(System.lineSeparator(), code);
    }
}