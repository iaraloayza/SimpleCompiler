public class Scanner {

    private byte[] input;
    private int current; 

    public Scanner (byte[] input) {
        this.input = input;
    }

    // Lê o próximo caractere sem avançar o ponteiro
    private char peek() {
        if (current < input.length)
            return (char)input[current];
        return '\0'; // fim da entrada
    }

    // Avança para o próximo caractere
    private void advance() {
        char ch = peek();
        if (ch != '\0') current++;
    }

    // Retorna o próximo token (ainda um caractere)
    public char nextToken() {
        char ch = peek();

        if (Character.isDigit(ch)) {
            advance();
            return ch;
        }

        switch (ch) {
            case '+':
            case '-':
                advance();
                return ch;
        }

        return '\0'; // nenhum token válido
    }

    // Teste simples do scanner
    public static void main(String[] args) {
        String input = "4-8+6";
        Scanner scan = new Scanner(input.getBytes());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
    }
}