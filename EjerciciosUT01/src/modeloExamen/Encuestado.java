package modeloExamen;

import java.io.FileWriter;
import java.io.IOException;

// Esta clase representa al proceso hijo (el encuestado).
// El servidor lanzará varios de estos procesos a la vez.
public class Encuestado {

    public static void main(String[] args) {

        // 1. Pequeña espera aleatoria inicial entre 0 y 0.5 segundos
        try {
            Thread.sleep((int) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2. Construimos el nombre único del encuestado usando la marca de tiempo
        // actual (milisegundos)
        String sName = Long.toString(System.currentTimeMillis());

        // IMPRESCINDIBLE: Imprimimos el nombre por pantalla.
        // El proceso padre (Servidor) intercepta esto con el hilo ProcesaCli para
        // enterarse del nombre.
        System.out.print(sName + "\n");

        // 3. Tiempo de reflexión aleatorio entre 1 y 3 segundos simula que el
        // encuestado "piensa" su respuesta
        try {
            Thread.sleep((int) (Math.random() * 2000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4. Generamos un número aleatorio entre 1 y 9 (que representa un color según
        // la tabla del examen)
        int iValorEsc = ((int) (Math.random() * 9) + 1);

        // 5. Almacenamos el resultado en un fichero de texto llamado:
        // [nombre_del_encuestado].txt
        // Por ejemplo: 1762156709236.txt
        try (FileWriter writer = new FileWriter(sName + ".txt")) {
            writer.write(Integer.toString(iValorEsc));
        } catch (IOException e) {
            // Si hay error de E/S, se captura
        }
    }
}