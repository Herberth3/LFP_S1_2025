/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clase1;

/**
 *
 * @author gerso
 */
public class Clase1 {

    // FUNCION
    public static int sumar(int a, int b){
        return a + b;
    }
    
    // METODO - PROCEDIMIENTOS
    public void imprimirMensaje(){
        System.out.println("Este es un metodo de JAVA!");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // VARIABLES Y TIPOS
        /*int numero = 10;
        double decimal = 20.5;
        char letra = 'A';
        boolean esVerdad = true;
        
        System.out.println("El numero es: " + numero);
        System.out.println("El decimal es: " + decimal);
        System.out.println("La letra es: " + letra);
        System.out.println("El booleano fue: " + esVerdad);
        
        // STRING
        String nombre = "Lenguajes Formales";
        String saludo = "Bienvenidos a " + nombre + "!";
        int longitudString = saludo.length();
        
        System.out.println(saludo);
        System.out.println("La longitud del saludo es: " + longitudString);
        
        // LLAMADA A FUNCION
        int resultado = sumar(5, 10);
        System.out.println("La suma es: " + resultado);
        
        // LLAMADA A METODO
        Clase1 ejemplo = new Clase1();
        ejemplo.imprimirMensaje();*/
        
        // Instancia de la Clase Persona
        Persona persona1 = new Persona("Herberth", 32);
        Persona persona2 = new Persona("Abisai", 23);
        persona1.mostrarInfo();
        persona2.mostrarInfo();
        
                
    }
    
}
