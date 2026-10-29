package practica1;

import java.io.File;

public class RedireccionFichero {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("ls", "-l");
            pb.redirectOutput(new File("resultado.txt"));

            Process proceso = pb.start();
            proceso.waitFor();

            System.out.println("El proceso se ha terminado con éxito.");

        } catch (Exception e) {
            System.err.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

}
