package ejercicioIntro;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunTimePadre {
    public static void main(String[] args) throws Exception {

        // 1. Obtenemos Runtime y ejecutamos el comando directamente
        Runtime runtime = Runtime.getRuntime();
        Process procesoHijo = runtime.exec("java ejercicioIntro.ProcBuilHija");

        // 2. Leemos la salida del hijo
        BufferedReader lector = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()));
        String linea;
        while ((linea = lector.readLine()) != null) {
            System.out.println("El hijo dice: " + linea);
        }

        // 3. Esperamos a que termine
        procesoHijo.waitFor();
        System.out.println("Proceso finalizado.");
    }
}