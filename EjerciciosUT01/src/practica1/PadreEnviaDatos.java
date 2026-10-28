package practica1;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class PadreEnviaDatos {
    public static void main(String[] args) {
        try {
            // 1. Configuramos el proceso hijo que vamos a lanzar
            ProcessBuilder pb = new ProcessBuilder("java", "MiProcesoHijo");
            Process proceso = pb.start();

            // 2. Preparamos el canal para escribir hacia el hijo (getOutputStream)
            BufferedWriter escritorHijo = new BufferedWriter(
                new OutputStreamWriter(proceso.getOutputStream())
            );

            // 3. Enviamos un dato o comando al hijo
            escritorHijo.write("Hola hijo, aquí tienes tu primera orden");
            escritorHijo.newLine(); // Importante para que el hijo detecte el salto de línea (readLine)
            escritorHijo.flush();   // Fuerza a que los datos se envíen físicamente al proceso hijo
            
            // Cerramos el flujo para indicar que hemos terminado de enviar datos
            escritorHijo.close();

            // 4. Esperamos a que el hijo termine
            int codigo = proceso.waitFor();
            System.out.println("El hijo terminó con código: " + codigo);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}