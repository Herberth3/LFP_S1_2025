/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase4;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author gerso
 */
public class Clase4 {
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n*** Menú de opciones ***");
            System.out.println("1. Leer archivo 'personajes.lfp'");
            System.out.println("2. Escribir en archivo 'personajes.lfp'");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después del número
            
            switch (opcion) {
                case 1:
                    leerArchivo();
                    break;
                case 2:
                    System.out.print("Escriba el texto que desea agregar al archivo: ");
                    String texto = scanner.nextLine();
                    escribirArchivo(texto);
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        
        scanner.close();
        
    }
    
    // Método para leer el archivo personajes.lfp
    public static void leerArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("personajes.lfp"))) {
            String linea;
            System.out.println("\nContenido del archivo 'personajes.lfp':");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo 'personajes.lfp' no existe.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

    // Método para escribir en el archivo personajes.lfp
    public static void escribirArchivo(String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("personajes.lfp", true))) {
            bw.write(texto);
            bw.newLine();
            System.out.println("Texto agregado al archivo 'personajes.lfp'.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
        }
    }
    
}
