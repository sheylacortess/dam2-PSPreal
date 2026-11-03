package examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class LanzadorMensaje {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "examen.ProcesoHijo", "Hola desde el padre!");
            pb.redirectError(new File("errores2.log"));
        
            Process proceso = pb.start();

            // ahora quiero leer lo que el hijo muestra por pantalla asi que buffered reader
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            //leo lo que el hijo mande 
            String linea;
            while ((linea = lector.readLine()) !=  null) {
                System.out.println("Padre ha leido del hijo: " + linea);
            }


            int codigo = proceso.waitFor();
            System.out.println("El proceso ha terminado con codigo =  " + codigo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
