/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clase1;

/**
 *
 * @author gerso
 */
public class Persona {
    // Atributos
    String nombre;
    int edad;
    
    // Constructor
    public Persona(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }
    
    // Metodo es para mostrar informacion
    public void mostrarInfo(){
        System.out.println("Nombre es: " + this.nombre + ", la Edad es: " + this.edad);
    }
}
