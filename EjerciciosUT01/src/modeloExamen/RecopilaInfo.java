package modeloExamen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Cada hilo de esta clase se encarga de recuperar el resultado de un encuestado leyendo su fichero
public class RecopilaInfo extends Thread {
    
    public String sNombre;
    Monitor mon; 
    
    // Constructor: Recibe el nombre del encuestado y la referencia al Monitor compartido
    public RecopilaInfo(String sNombre, Monitor mon) {
        this.sNombre = sNombre;
        this.mon = mon;
    }

    public void run () {
        int iValorEncuesta;
        String filePath = sNombre + ".txt";     // Ruta del archivo a leer (ej: 1762156709236.txt)
        
        // Intentamos abrir el fichero del encuestado
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            String line;
            // Leemos el contenido del fichero
            while ((line = bufferedReader.readLine()) != null) {
                iValorEncuesta = Integer.parseInt(line);
                
                // Guardamos el voto de forma segura llamando al método sincronizado del Monitor
                mon.IncColor(iValorEncuesta);
            }
        } catch (IOException e) {
            // Si entra aquí significa que el fichero NO existe (el encuestado no respondió)
            // Incrementamos el contador de no participantes en el Monitor
            mon.IncNoEnc();
        }
    }
}