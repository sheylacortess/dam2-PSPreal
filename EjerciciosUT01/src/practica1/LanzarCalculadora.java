package practica1;

import java.io.IOException;

public class LanzarCalculadora {
    public static void main(String[] args) throws IOException {
        try {
            // 1 congiguro el proceso 
        // ProcessBuilder pb = new ProcessBuilder("gnome-calculator");
        // 2 arranco el proceso 
        // Process proceso = pb.start();

        Process proceso = Runtime.getRuntime().exec("firefox"); // o un comando de Linux

        System.out.println("Se ha abierto la calculadora con exito!");
        } catch (IOException e) {
            System.err.println("No se ha podido iniciar el proceso: " + e.getMessage());
        }
        
        
    }

}
