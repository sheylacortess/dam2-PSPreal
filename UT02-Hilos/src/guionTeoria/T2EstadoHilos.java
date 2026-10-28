package guionTeoria;

import java.util.ArrayList;

// Los estados del hilo pueden ser Nuevo, Ejecutable, Bloqueado, Esperando, Esperando un tiempo, Finalizado
// En Thread.State aparecen como NEW, RUNNABLE, BLOCKED, WAITING, TIME_WAITING, TERMINATED
public class T2EstadoHilos implements Runnable {
    public static void main(String[] args) {
        T2EstadoHilos miEstHi = new T2EstadoHilos();
        ArrayList<Thread.State> miArrEs = new ArrayList<Thread.State>();
        Thread miEst = new Thread(miEstHi);
        miArrEs.add(miEst.getState()); // Aqui anade el estado NEW
        miEst.start();
        // Estados por los que va pasando antes de terminar
        while (miEst.getState() != Thread.State.TERMINATED) {
            if (!miArrEs.contains(miEst.getState())) {
                miArrEs.add(miEst.getState());
            }
        }
        if (!miArrEs.contains(miEst.getState())) // Aqui pone el estado TERMINATED
        {
            miArrEs.add(miEst.getState());
        }
        for (Thread.State estado : miArrEs) {
            System.out.println(estado);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100); // Ponerlo a dormir anade el estado TIMED_WAITING
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}