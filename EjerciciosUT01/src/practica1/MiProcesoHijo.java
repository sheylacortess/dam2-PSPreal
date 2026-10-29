package practica1;

import java.util.Scanner;

public class MiProcesoHijo {
    public static void main(String[] args) {
        // El hijo usa Scanner para leer lo que le escribe el proceso padre por la entrada estándar
        Scanner scanner = new Scanner(System.in);
        
        if (scanner.hasNextLine()) {
            String mensajeRecibido = scanner.nextLine();
            System.out.println("El hijo ha recibido esto: " + mensajeRecibido);
        }
        scanner.close();
    }
}