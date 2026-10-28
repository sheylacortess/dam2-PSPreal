package guionTeoria;

// Con ps -ef se puede comprobar que los timer son hilos, no procesos. El SO solo activa un proceso
import java.util.Timer;

public class T2Timer {
    public static void main(String[] args) {
        Timer miTimer = new Timer();
         miTimer.schedule(new T2TimerEjemplo(),1000,2000); // Inicia al segundo, se
        // repite cada dos
        // miTimer.schedule(new T2TimerEjemplo(),1000); /b/ Una ejecución despues de 1
        // segundo
        miTimer.schedule(new T2TimerTarea(), 1000, 2000); // Llama a otra tarea
        // Dejamos un tiempo para que haya varias ejecuciones antes del cancel (parada
        // del timer)
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Para el timer. Si no paras el timer, aunque termine main, sigue activo.
        // Comprobar tambien con ps -ef
        miTimer.cancel();
        System.out.println("Parado el timer");
        // Dejamos un tiempo para comprobar que el timer ya no esta activo
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finaliza el programa");
    }
}
