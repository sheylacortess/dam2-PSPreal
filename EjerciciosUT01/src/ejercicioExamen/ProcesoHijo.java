package ejercicioExamen;

/**
 * ProcesoHijo
 * Simulará una tarea pesada haciendo que el hilo principal se duerma durante 3 segundos (Thread.sleep(3000)).
 * Imprimirá un mensaje por su salida estándar confirmando que ha finalizado su tarea correctamente.
 * Terminará su ejecución devolviendo un código de salida normal (0).
 */
public class ProcesoHijo {
    public static void main(String[] args) {
        try {
            System.out.println("El hio ha empezado una tarea... ");
            Thread.sleep(3000);
            System.out.println("Trabajo finalizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
