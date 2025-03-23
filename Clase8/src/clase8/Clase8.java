package clase8;
// Archivo: Menu.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Clase8 {

    public static void main(String[] args) {
        Analizador analizador = new Analizador();
        Scanner scanner = new Scanner(System.in);
        int option;

        // Ciclo del menú
        while (true) {
            // Mostrar las opciones del menú
            showMenu();

            // Leer la opción ingresada por el usuario
            option = scanner.nextInt();
            scanner.nextLine(); // Para consumir el salto de línea

            // Ejecutar acción según la opción seleccionada
            switch (option) {
                case 1:
                    try {
                        // Leer el contenido del archivo
                        String fileContent = new String(Files.readAllBytes(Paths.get("entrada.lfp")));
                        analizador.analyze(fileContent);  // Llamar al método analyze con fileContent
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo: " + e.getMessage());
                    }
                    break;
                case 2:
                    analizador.graphAutomata();  // Llamar al método para graficar el autómata
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    // Método para mostrar el menú
    private static void showMenu() {
        System.out.println("===========================");
        System.out.println("       Menu Principal       ");
        System.out.println("===========================");
        System.out.println("1. Analizar");
        System.out.println("2. Graficar Automata");
        System.out.println("3. Salir");
        System.out.println("Seleccione una opcion:");
    }
}
