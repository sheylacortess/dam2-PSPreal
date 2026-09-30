package ejercicioIntro;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ProcBuilPadre {
    public static void main(String[] args) throws Exception {

        // 1. Preparamos el comando que queremos lanzar (ejecutar la clase ProcBuilHija)
        ProcessBuilder pb = new ProcessBuilder("java", "ejercicioIntro.ProcBuilHija");
        pb.directory(new File("./bin"));

        // (Opcional) Cambiar el directorio de trabajo donde se ejecutará
        // pb.directory(new File("/ruta/donde/trabajar"));

        // 2. Iniciamos el proceso hijo
        Process procesoHijo = pb.start();

        // 3. Comprobamos si está vivo
        System.out.println("¿El hijo está activo?: " + procesoHijo.isAlive());

        // 4. Leemos lo que imprime el hijo por pantalla (capturar flujo de salida)
        BufferedReader lector = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()));
        String linea;
        while ((linea = lector.readLine()) != null) {
            System.out.println("El hijo dice: " + linea);
        }

        // 5. Esperamos a que el hijo termine del todo
        int codigoSalida = procesoHijo.waitFor();
        System.out.println("El hijo ha terminado con código: " + codigoSalida);
        System.out.println("¿El hijo sigue activo?: " + procesoHijo.isAlive());
    }
}