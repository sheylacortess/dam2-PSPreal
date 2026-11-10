package modeloExamen;

import java.io.IOException;
import java.util.Scanner;

public class Servidor {

    // Arrays estáticos para guardar los procesos hijos y los hilos que los atienden
    static private Process[] encuestados;
    static private ProcesaCli[] hilosCli;

    public static void main(String[] args) throws IOException {

        // Creamos un Scanner para leer lo que el usuario escribe por teclado
        Scanner miScan = new Scanner(System.in);

        // Pedimos por pantalla cuántos encuestados queremos lanzar
        System.out.print("\n\tIndica el numero de encuestados que participan: ");
        String sAux = miScan.nextLine();
        System.out.println();

        int iNumEnc;
        try {
            // Intentamos convertir el texto introducido a un número entero
            iNumEnc = Integer.parseInt(sAux);
        } catch (Exception e) {
            // Si el usuario escribe letras o algo inválido, asignamos 5 por defecto (como
            // pide el examen)
            iNumEnc = 5;
            System.out.println("Dato no válido. Se usarán 5 encuestados por defecto.");
        }

        // Dimensionamos los arrays con el número total de encuestados
        encuestados = new Process[iNumEnc];
        hilosCli = new ProcesaCli[iNumEnc];

        System.out.println("\t ** Participan " + iNumEnc + " encuestados **");

        // =========================================================================
        // FASE 1: Bucle para lanzar a todos los procesos hijos (los encuestados)
        // =========================================================================
        for (int i = 0; i < iNumEnc; i++) {

            // Configuramos el ProcessBuilder indicando que ejecute la clase "Encuestado"
            // Obtenemos automáticamente el classpath exacto que usa Eclipse
            String classpath = System.getProperty("java.class.path");

            // Configuramos el ProcessBuilder con ese classpath dinámico
            ProcessBuilder mipb = new ProcessBuilder("java", "-cp", classpath, "modeloExamen.Encuestado");
            mipb.redirectErrorStream(true);

            try {
                // Arrancamos el proceso hijo y lo guardamos en el array
                encuestados[i] = mipb.start();

                // Creamos un hilo (ProcesaCli) pasándole el proceso hijo para que escuche su
                // nombre
                hilosCli[i] = new ProcesaCli(encuestados[i]);

                // ¡Arrancamos el hilo en segundo plano!
                hilosCli[i].start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // =========================================================================
        // FASE 2: Obligamos al padre a esperar a que mueran/terminen todos los hijos
        // =========================================================================
        for (int i = 0; i < iNumEnc; i++) {
            try {
                // El método waitFor() bloquea al padre hasta que este proceso hijo finaliza
                encuestados[i].waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Pequeña pausa de seguridad de 1 segundo
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n\t** Todos los encuestados han terminado sus procesos **");

        // =========================================================================
        // FASE 3: Procesar los resultados con hilos concurrentes
        // =========================================================================
        System.out.println("\n\t** Procesando los resultados con hilos **");

        Monitor miMon = new Monitor();
        RecopilaInfo[] hilosLeeFich = new RecopilaInfo[iNumEnc];

        // Lanzamos un hilo RecopilaInfo por cada encuestado para leer su fichero en
        // paralelo
        for (int i = 0; i < iNumEnc; i++) {
            // Usamos el nombre que capturó el hilo ProcesaCli
            hilosLeeFich[i] = new RecopilaInfo(hilosCli[i].sNombre, miMon);
            hilosLeeFich[i].start();
        }

        // Esperamos a que terminen todos los hilos lectores usando join()
        for (int i = 0; i < iNumEnc; i++) {
            try {
                hilosLeeFich[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\t** El resultado de la encuesta es **");
        // Mostramos cuántos no participaron
        System.out.println("\t\t" + miMon.getNumNoPar() + " encuestados no han participado.");
        
        // --- AÑADE ESTE BUCLE PARA LEER Y MOSTRAR LOS COLORES ---
        System.out.println("\t\tDesglose de votos por color:");
        for (java.util.Map.Entry<Integer, Integer> entry : miMon.getListaColores().entrySet()) {
            System.out.println("\t\t - Color " + entry.getKey() + ": " + entry.getValue() + " voto(s)");
        }
        // Cerramos el scanner de teclado
        miScan.close();
    }
}