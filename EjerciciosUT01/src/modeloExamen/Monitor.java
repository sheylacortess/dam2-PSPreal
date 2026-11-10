package modeloExamen;

import java.util.TreeMap;

public class Monitor {
    
    // Mapa ordenado (TreeMap) para guardar la relación: Clave = Número de color, Valor = Cantidad de votos
    private TreeMap <Integer, Integer> tmColores = new TreeMap <Integer, Integer> ();
    private int iNumEncNoPar = 0;        // Contador de encuestados que no han respondido (no dejaron fichero)
    
    // Método sincronizado para incrementar el contador de un color de forma segura ante varios hilos
    public synchronized void IncColor (int iValor) {
        if (tmColores.get(iValor) == null) {
            // Si el color no estaba en el mapa, lo añadimos con valor 1
            tmColores.put(iValor, 1);
        } else {
            // Si ya existía, recuperamos su valor actual, le sumamos 1 y lo volvemos a guardar
            int iValAux = tmColores.get(iValor); 
            iValAux++;
            tmColores.put(iValor, iValAux);
        }
    }
    
    // Método sincronizado para incrementar el número de encuestados que no han participado
    public synchronized void IncNoEnc () {
        iNumEncNoPar++;
    }

    // Getters para consultar los resultados finales desde el Servidor
    public TreeMap <Integer, Integer> getListaColores () {
        return tmColores;
    }

    public int getNumNoPar () {
        return iNumEncNoPar;
    }
}