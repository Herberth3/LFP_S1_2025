package clase8;

// Archivo: Analizador.java
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Analizador {

    // Clase para representar los tokens
    static class Token {
        String lexema;
        String tipo;

        Token(String lexema, String tipo) {
            this.lexema = lexema;
            this.tipo = tipo;
        }
    }

    // Método para leer el archivo y devolver su contenido
    public String readFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    // Método para analizar el contenido del archivo
    public void analyze(String fileContent) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder currentLexema = new StringBuilder();
        int state = 0;
        int i = 0;
        char caracter;

        fileContent = fileContent.trim() + "#"; // Agregar carácter de fin de cadena
        int length = fileContent.length();

        while (i < length - 1) {
            caracter = fileContent.charAt(i);
            i++;

            switch (state) {
                case 0:  // Estado inicial
                    if (Character.isLetter(caracter)) {  // Letra [a-z]
                        state = 1;
                        currentLexema.append(caracter);
                    } else if (caracter == '"') {
                        state = 2;
                        currentLexema.append(caracter);
                    } else if (Character.isWhitespace(caracter) || caracter == '\n' || caracter == '\t' || caracter == '\r') {
                        // Ignorar espacios, tabulaciones y saltos de línea
                    } else if (caracter == '#' && i == length - 1) {
                        // Fin del archivo
                    } else {
                        System.out.println("Error lexico: Caracter inesperado: " + caracter);
                    }
                    break;
                case 1:  // Estado para identificadores
                    if (Character.isLetter(caracter)) {
                        currentLexema.append(caracter);
                    } else {
                        state = 0;
                        String lexema = currentLexema.toString().trim();
                        if (!lexema.isEmpty()) {
                            if (lexema.equals("int")) {
                                tokens.add(new Token(lexema, "Reservada"));
                            } else {
                                tokens.add(new Token(lexema, "Identificador"));
                            }
                            currentLexema.setLength(0);
                        }
                        i--;  // Regresar un carácter para seguir evaluando
                    }
                    break;
                case 2:  // Estado para el inicio de una cadena
                    if (caracter != '"') {
                        state = 3;
                        currentLexema.append(caracter);
                    } else {
                        System.out.println("Error lexico: Se esperaba un caracter de cadena");
                    }
                    break;
                case 3:  // Estado para el contenido de la cadena
                    if (caracter != '"') {
                        currentLexema.append(caracter);
                    } else {
                        state = 4;
                        currentLexema.append(caracter);
                    }
                    break;
                case 4:  // Estado final de una cadena
                    state = 0;
                    if (!currentLexema.toString().isEmpty()) {
                        tokens.add(new Token(currentLexema.toString().trim(), "Cadena"));
                        currentLexema.setLength(0);
                    }
                    i--;  // Regresar un carácter
                    break;
            }

            if (caracter == '#' && i == length - 1) {
                System.out.println("Analisis completo. Tokens encontrados:");
                for (Token token : tokens) {
                    System.out.println("Lexema: " + token.lexema + " Tipo: " + token.tipo);
                }
            }
        }
    }

    // Método para la opción "Graficar Autómata"
    public void graphAutomata() {
        String dotFileName = "automata_graph.dot";
        String pngFileName = "automata_graph.png";

        // Usar try-with-resources para asegurar que el FileWriter se cierra correctamente
        try (FileWriter fileWriter = new FileWriter(dotFileName)) {
            StringBuilder dotCode = new StringBuilder();
            int state = 0;
            int rama = 0;

            // 1. Construcción del contenido .dot
            dotCode.append("digraph Automata {\n");
            dotCode.append("rankdir=LR;\n");
            dotCode.append("node [shape = circle, style=filled];\n");

            // Bucle para construir el autómata
            while (true) {
                switch (state) {
                    case 0:
                        if (rama == 0) {
                            state = 1;
                            dotCode.append("S0 -> S1 [label=\"L\"];\n");
                        } else if (rama == 1) {
                            state = 2;
                            dotCode.append("S0 -> S2 [label=\"\\\"\"];\n");
                        }
                        break;
                    case 1:
                        dotCode.append("S1 -> S1 [label=\"L\"];\n");
                        state = 0;
                        rama = 1;  // Verifica si esta reasignación es intencional
                        break;
                    case 2:
                        state = 3;
                        dotCode.append("S2 -> S3 [label=\"C\"];\n");
                        break;
                    case 3:
                        dotCode.append("S3 -> S3 [label=\"C\"];\n");
                        state = 4;
                        dotCode.append("S3 -> S4 [label=\"\\\"\"];\n");
                        break;
                    case 4:
                        dotCode.append("S1 [shape = doublecircle, fillcolor = \"green\"];\n");
                        dotCode.append("S4 [shape = doublecircle, fillcolor = \"green\"];\n");
                        dotCode.append("}");
                        // Salir del bucle al completar el grafo
                        fileWriter.write(dotCode.toString().trim());
                        System.out.println("Automata graficado en " + dotFileName);
                        state = 5;
                        break;
                }
                // Salida del bucle en el estado final
                if (state == 5) break;
            }

            // 2. Ejecutar el comando de Graphviz para generar la imagen .png
            // Ejecutar el comando en el sistema
            ProcessBuilder pb;
            pb = new ProcessBuilder("dot", "-Tpng", "-o", pngFileName, dotFileName);
            pb.redirectErrorStream(true);
            pb.start();

        } catch (Exception e) {
            System.err.println("Error al generar el archivo o ejecutar el comando: " + e.getMessage());
            e.printStackTrace();
        }
    }

}


