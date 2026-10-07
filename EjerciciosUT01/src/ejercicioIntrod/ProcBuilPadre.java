package ejercicioIntrod;

import java.io.IOException;

public class ProcBuilPadre {
    public static void main(String[] args) {
        try {
            // 1. Lanzar aplicación GUI con SECUENCIA DE STRINGS (Varargs)
            System.out.println("Lanzando calculadora...");
            ProcessBuilder pbApp = new ProcessBuilder("gnome-calculator");
            Process pApp = pbApp.start();

            // 2. Lanzar comando Linux con ARRAY DE STRINGS
            System.out.println("Lanzando comando 'ls -la'...");
            String[] comandoLinux = {"ls", "-la"};
            ProcessBuilder pbCmd = new ProcessBuilder(comandoLinux);
            Process pCmd = pbCmd.start();

            System.out.println("Procesos inicializados correctamente.");

        } catch (IOException e) {
            System.err.println("Error al ejecutar el proceso: " + e.getMessage());
        }
    }
}