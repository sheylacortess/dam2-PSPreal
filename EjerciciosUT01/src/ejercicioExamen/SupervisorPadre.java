package ejercicioExamen;

import java.io.File;
import java.io.IOException;

/**
 * ClasePadre
 * 
 * Debe configurar un ProcessBuilder para ejecutar la clase hija (ProcesoHijo).
 * Debe configurar el directorio de trabajo (directory) apuntando a la carpeta de compilación (./bin).
 * Debe redirigir los errores del hijo automáticamente a un archivo de texto llamado errores.log.
 * Debe arrancar el proceso y mostrar por consola el PID del proceso hijo recién creado.
 * Mientras el proceso hijo esté ejecutándose, el padre debe usar un bucle con el método isAlive() para mostrar un mensaje periódico por pantalla (ej: "El hijo sigue trabajando...") hasta que termine.
 * Una vez que el hijo finalice, el padre debe recoger su código de salida (waitFor()) y mostrar un mensaje indicando si el proceso terminó con éxito o si hubo algún problema.
 * 
 */
public class SupervisorPadre {
    public static void main(String[] args) {
        try {
            // 1. Configuramos ProcessBuilder llamando a java y ejecutando la clase hija
            // Usamos "." en el classpath para que busque en la carpeta actual de bin
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "ejercicioExamen.ProcesoHijo");

            // 2. Redirigimos los errores a un archivo de texto
            pb.redirectError(new File("errores.log"));

            // 3. Arrancamos el proceso y mostramos su PID
            Process proceso = pb.start();
            System.out.println("Proceso hijo lanzado con PID: " + proceso.pid());

            // 4. Bucle para comprobar si sigue vivo mientras trabaja
            while (proceso.isAlive()) {
                System.out.println("El hijo sigue trabajando...");
                Thread.sleep(1000); // Espera 1 segundo entre comprobación y comprobación
            }

            // 5. Recogemos el código de salida cuando termina
            int codigo = proceso.waitFor();
            System.out.println("El proceso hijo ha terminado. Código de salida: " + codigo);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
