// Responsável por ler e agrupar os caracteres em tokens
public class Scanner {
    private byte[] input;
    private int current;

    public Scanner(byte[] input) {
        this.input = input;
    }

    // Retorna o caractere atual sem consumir
    public char peek() {
        if (current < input.length)
            return (char) input[current];
        return '\0';
    }

    // Avança um caractere
    public void advance() {
        current++;
    }

    // Lê e retorna um número completo ([0-9]+)
    public String getNumber() {
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(peek())) {
            number.append(peek());
            advance();
        }
        return number.toString();
    }
}
