package modeloExamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Esta clase extiende de "Thread", lo que significa que se ejecuta como un hilo en segundo plano.
// Su única misión es atender a un proceso hijo concreto para recibir y leer su nombre.
public class ProcesaCli extends Thread {

    public String sNombre; // Variable pública para guardar el nombre que emite el encuestado
    Process miProcess; // Referencia al proceso hijo que este hilo va a vigilar y leer

    // Constructor: Recibe por parámetro el proceso hijo que acaba de arrancar el
    // servidor
    public ProcesaCli(Process miProcess) {
        this.miProcess = miProcess;
    }

    // El método run() es el código que se ejecuta automáticamente al hacer .start()
    // en el hilo
    public void run() {
        try {
            // 1. Obtenemos el flujo de entrada (InputStream) del proceso hijo.
            // Esto es lo que el hijo imprime por pantalla (su System.out.print)
            InputStream miIs = miProcess.getInputStream();

            // 2. Preparamos los lectores para poder leer texto plano línea a línea
            // cómodamente
            InputStreamReader miIsr = new InputStreamReader(miIs);
            BufferedReader miIsrb = new BufferedReader(miIsr);

            String sInfo = "";

            // 3. Leemos línea a línea lo que el proceso hijo va escribiendo
            // Mientras el hijo siga emitiendo texto, lo leemos y lo guardamos en sNombre
            while ((sInfo = miIsrb.readLine()) != null) {
                sNombre = sInfo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. Una vez leído, lo mostramos por la consola del servidor formateado
        System.out.println("\t\t " + sNombre);
    }
}