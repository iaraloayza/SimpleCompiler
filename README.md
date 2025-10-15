# 🧠 Compiladores – De um Simples Tradutor a um Simples Interpretador

Projeto desenvolvido como **atividade individual** da disciplina de **Compiladores**, seguindo o tutorial **“Tradução Dirigida por Sintaxe”** até a **Parte 8 – Um Simples Interpretador**.

---

## 📘 Descrição da atividade

O objetivo deste projeto é implementar, passo a passo, um **analisador léxico e sintático simples**, evoluindo progressivamente até construir um **interpretador funcional** capaz de executar expressões aritméticas e comandos básicos escritos em uma mini-linguagem.

Cada etapa representa um avanço no funcionamento do compilador, desde a leitura de caracteres até a execução de instruções.

👩‍💻 **Autora:** Ana Iara Loayza Costa

---

## ⚙️ Estrutura do projeto

```
SimpleCompiler/
└── src/
    ├── App.java
    ├── Parser.java
    ├── Scanner.java
    ├── Token.java
    ├── TokenType.java
    └── Interpretador.java
```

- **`Scanner.java`** → Responsável pela análise léxica (gera tokens).  
- **`Parser.java`** → Responsável pela análise sintática e tradução para comandos intermediários.  
- **`Token.java` / `TokenType.java`** → Representam os tipos de tokens e seus valores.  
- **`Interpretador.java`** → Executa o código intermediário gerado pelo parser.  
- **`App.java`** → Ponto de entrada do programa (testa o compilador e o interpretador).

---

## 🧩 Etapas Implementadas

1. **Reconhecimento de dígitos e operadores simples (+, -)**  
2. **Tratamento de espaços em branco**  
3. **Tokens com múltiplos caracteres (números inteiros)**  
4. **Estrutura de tokens com tipo e lexema (`Token` e `TokenType`)**  
5. **Parser atualizado para reconhecer números inteiros (`number`)**  
6. **Suporte a variáveis (identificadores e palavra reservada `let`)**  
7. **Inclusão do comando de impressão (`print`)**  
8. **Implementação de um interpretador com pilha e variáveis**

Cada etapa foi **comitada separadamente**, demonstrando a evolução do projeto.

---

## ▶️ Como executar

### 1. Clonar o Repositório
```bash
git clone https://github.com/iaraloayza/SimpleCompiler.git
cd SimpleCompiler
```

### 2. Compilar todos os arquivos
```bash
cd src
javac *.java
```

### 3. Executar o programa principal
```bash
java App
```

---

## 🧪 Exemplo de execução

### Entrada:
```bash
let a = 42 + 2;
let b = 15 + 3;
print a + b;
```

### Saída esperada:
```bash
62
```

O interpretador realiza o cálculo internamente da seguinte forma:

1. ```bash a = 44 ```

2. ```bash b = 18 ```

3. ```bash print a + b → imprime 62 ```
